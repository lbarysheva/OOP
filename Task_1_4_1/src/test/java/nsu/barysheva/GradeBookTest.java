package nsu.barysheva;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class GradeBookTest {
    @Test
    public void testAddRecordWithValidData() {
        GradeBook record = new GradeBook(true);
        record.addRecord("Математика", "Экзамен", 1, 5);
        record.addRecord("Физика", "Зачет", 2, 4);
        assertEquals(4.5, record.calculateAverageMark());
    }

    /**
     * Проверка добавления записи с некорректным номером семестра.
     */
    @Test
    public void testAddRecordWithInvalidSemester() {
        GradeBook record = new GradeBook(true);
        assertThrows(IllegalArgumentException.class, () -> record.addRecord("Математика", "Экзамен", 9, 4));
    }

    /**
     * Проверка добавления записи с некорректной оценкой.
     */
    @Test
    public void testAddRecordWithInvalidGrade() {
        GradeBook record = new GradeBook(true);
        assertThrows(IllegalArgumentException.class, () -> record.addRecord("Математика", "Экзамен", 1, 6));
    }

    /**
     * Проверка вычисления среднего балла при отсутствии оценок.
     */
    @Test
    public void testCalculateAverageGradeWithEmptyRecords() {
        GradeBook record = new GradeBook(true);
        assertEquals(0.0, record.calculateAverageMark());
    }

    /**
     * Проверка возможности получения повышенной стипендии.
     */
    @Test
    public void testIsEligibleForIncreasedScholarship() {
        GradeBook record = new GradeBook(true);
        record.addRecord("Математика", "Экзамен", 1, 5);
        record.addRecord("Физика", "Экзамен", 2, 4);

        assertTrue(record.isEligibleForIncreasedScholarship());

        record.addRecord("Химия", "Экзамен", 2, 3);

        assertFalse(record.isEligibleForIncreasedScholarship());
    }

    @Test
    public void testCheckBudgetEligibility() {
        GradeBook gradeBook = new GradeBook(true);
        gradeBook.addRecord("Математика", "Экзамен", 2, 5);
        gradeBook.addRecord("Физика", "Экзамен", 3, 4);

        // Студент может перевестись на бюджет, так как оба экзамена на "хорошо" и выше
        assertEquals("Студент может перевестись на бюджетную основу.", gradeBook.checkBudgetEligibility());

        // Добавим оценку ниже 4 для одного из экзаменов
        gradeBook.addRecord("Программирование", "Экзамен", 4, 3);

        // Студент не может перевестись на бюджет из-за плохой оценки
        assertEquals("Студент не соответствует критериям для перевода на бюджет.", gradeBook.checkBudgetEligibility());
    }

    @Test
    public void testIsEligibleForHonorsDiploma() {
        GradeBook gradeBook = new GradeBook(true);

        // Добавляем все оценки на "5" и ВКР с "отлично"
        gradeBook.addRecord("Математика", "Экзамен", 1, 5);
        gradeBook.addRecord("Физика", "Экзамен", 2, 5);
        gradeBook.addRecord("Программирование", "Экзамен", 3, 5);
        gradeBook.addRecord("Зашита ВКР", "ВКР", 8, 5);

        // Студент имеет все оценки на 5 и ВКР на 5, он может получить красный диплом
        assertEquals(true, gradeBook.isEligibleForHonorsDiploma());

        // Добавим оценку ниже 4
        gradeBook.addRecord("Философия", "Экзамен", 4, 3);

        // После добавления оценки ниже 4, красный диплом недоступен
        assertFalse(gradeBook.isEligibleForHonorsDiploma());
    }

    @Test
    public void testToString() {
        // Создаем объект GradeBook и добавляем записи
        GradeBook gradeBook = new GradeBook(true);
        gradeBook.addRecord("Математика", "Экзамен", 1, 5);
        gradeBook.addRecord("Физика", "Зачет", 2, 4);
        gradeBook.addRecord("Программирование", "Экзамен", 3, 5);
        gradeBook.addRecord("Английский", "Зачет", 4, 4);

        // Ожидаемый вывод
        String expectedOutput = "Состояние зачётной книжки студента:\n" +
                "Предмет         Тип контроля         Семестр    Оценка    \n" +
                "-----------------------------------------------------------\n" +
                "Математика      Экзамен              1          5         \n" +
                "Физика          Зачет                2          4         \n" +
                "Программирование Экзамен              3          5         \n" +
                "Английский      Зачет                4          4         \n" +
                "-----------------------------------------------------------\n" +
                "Средний балл: 4,50\n" +
                "Перевод на бюджет: Студент может перевестись на бюджетную основу.\n" +
                "Красный диплом: Нет\n" +
                "Повышенная стипендия: Да\n";

        // Сравниваем реальный вывод с ожидаемым
        assertEquals(expectedOutput.trim(), gradeBook.toString().trim());
    }
}