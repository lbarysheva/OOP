package nsu.barysheva;

/**
 * Главный класс для демонстрации работы с классом GradeBook.
 */
public class Main {
    public static void main(String[] args) {
        GradeBook book = new GradeBook(true);

        book.addRecord("Модели вычислений", "Экзамен", 3, 5);
        book.addRecord("Цифровые платформы", "Экзамен", 2, 4);
        book.addRecord("ООП", "Экзамен", 2, 4);
        book.addRecord("Английский", "Зачет", 3, 4);
        book.addRecord("Философия", "Экзамен", 4, 4);

        System.out.println(book);
    }
}