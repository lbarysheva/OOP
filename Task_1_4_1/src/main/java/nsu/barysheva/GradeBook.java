package nsu.barysheva;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс GradeBook моделирует зачётную книжку студента
 */
public class GradeBook {
    private final List<RecordEntry> records; // Хранение записей об оценках
    private final boolean isFeeBased; // Форма обучения: true - платная, false - бюджетная

    /**
     * Конструктор, создающий объект зачётной книжки.
     *
     * @param isFeeBased - true, если студент на данный момент обучается на платной основе
     */

    public GradeBook(boolean isFeeBased) {
        this.records = new ArrayList<>();
        this.isFeeBased = isFeeBased;
    }

    /**
     * Метод для добавления оценки в зачетную книжку.
     *
     */
    public void addRecord(String subject, String controlType, int semester, int mark) {
        if (semester < 1 || semester > 8) {
            throw new IllegalArgumentException("Семестр должен быть в диапазоне от 1 до 8.");
        }
        if (mark < 2 || mark > 5) {
            throw new IllegalArgumentException("Оценка должна быть в диапазоне от 2 до 5.");
        }
        records.add(new RecordEntry(subject, controlType, semester, mark));
    }

    /**
     * Вычисляет средний балл за весь период обучения.
     *
     * @return Средний балл, округлённый до двух знаков после запятой.
     */
    public double calculateAverageMark() {
        if (records.isEmpty()) return 0;
        double sum = records.stream().mapToInt(RecordEntry::getMark).sum();
        return sum / records.size();
    }

    /**
     * Проверяет, может ли студент перейти на бюджетную форму обучения.
     *
     */
    public String checkBudgetEligibility() {
        if (!isFeeBased) {
            return "Студент уже учится на бюджетной основе.";
        }

        int currentSemester = getCurrentSemester();

        // Если данных за последние два семестра недостаточно
        long relevantRecordsCount = records.stream()
                .filter(record -> record.getSemester() >= currentSemester - 1)
                .count();

        if (relevantRecordsCount == 0) {
            return "Недостаточно данных об оценках за последние два семестра.";
        }

        // Проверяем последние 2 семестра
        boolean eligible = records.stream()
                .filter(record -> record.getSemester() >= currentSemester - 1)
                .allMatch(record -> {
                    if (record.getControlType().equals("Экзамен")) {
                        return record.getMark() >= 4; // Экзамены не ниже "хорошо"
                    }
                    return record.getMark() >= 3; // Зачёты и дифф. зачёты >= "удовлетворительно"
                });

        return eligible ? "Студент может перевестись на бюджетную основу." : "Студент не соответствует критериям для перевода на бюджет.";
    }

    /**
     * Проверяет возможность получения диплома с отличием.
     *
     */
    public boolean isEligibleForHonorsDiploma() {
        boolean noUnsatisfactoryMarks = records.stream().allMatch(record -> record.getMark() >= 4);
        long excellentMarksCount = records.stream().filter(record -> record.getMark() == 5).count();
        boolean thesisExcellent = records.stream()
                .anyMatch(record -> record.getControlType().equals("ВКР") && record.getMark() == 5);

        // Проверяем, что 75% оценок "отлично" и ВКР на "отлично"
        return noUnsatisfactoryMarks &&
                excellentMarksCount >= records.size() * 0.75 &&
                thesisExcellent;
    }

    /**
     * Проверяет, может ли студент получать повышенную стипендию.
     *
     */
    public boolean isEligibleForIncreasedScholarship() {
        int currentSemester = getCurrentSemester();
        return records.stream()
                .filter(record -> record.getSemester() == currentSemester)
                .allMatch(record -> record.getMark() >= 4);
    }

    /**
     * Определяет текущий семестр на основании внесённых оценок.
     *
     */
    private int getCurrentSemester() {
        return records.stream()
                .mapToInt(RecordEntry::getSemester)
                .max()
                .orElse(1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Состояние зачётной книжки студента:\n");
        builder.append(String.format("%-15s %-20s %-10s %-10s%n", "Предмет", "Тип контроля", "Семестр", "Оценка"));
        builder.append("-----------------------------------------------------------\n");
        for (RecordEntry entry : records) {
            builder.append(String.format("%-15s %-20s %-10d %-10d%n",
                    entry.getSubject(), entry.getControlType(), entry.getSemester(), entry.getMark()));
        }
        builder.append("-----------------------------------------------------------\n");
        builder.append(String.format("Средний балл: %.2f%n", calculateAverageMark()));
        builder.append("Перевод на бюджет: ").append(checkBudgetEligibility()).append("\n");
        builder.append("Красный диплом: ").append(isEligibleForHonorsDiploma() ? "Да" : "Нет").append("\n");
        builder.append("Повышенная стипендия: ").append(isEligibleForIncreasedScholarship() ? "Да" : "Нет").append("\n");
        return builder.toString();
    }

    /**
     * Вложенный класс для хранения данных об отдельной записи.
     */
    private static class RecordEntry {
        private final String subject;
        private final String controlType;
        private final int semester;
        private final int mark;

        public RecordEntry(String subject, String controlType, int semester, int mark) {
            this.subject = subject;
            this.controlType = controlType;
            this.semester = semester;
            this.mark = mark;
        }

        public String getSubject() {
            return subject;
        }

        public String getControlType() {
            return controlType;
        }

        public int getSemester() {
            return semester;
        }

        public int getMark() {
            return mark;
        }
    }
}