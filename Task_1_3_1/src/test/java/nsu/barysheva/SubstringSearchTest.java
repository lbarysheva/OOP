package nsu.barysheva;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubstringSearchTest {
    @Test
    void startTest() throws IOException {
        SubstringSearch actual = new SubstringSearch("бра");
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 8));
        assertEquals(expected, actual.find("src/test/resources/input.txt"));
    }

    @Test
    void testEmptyArr() throws IOException {
        SubstringSearch actual = new SubstringSearch(" ");
        ArrayList<Integer> expected = new ArrayList<>();
        assertEquals(expected, actual.find("src/test/resources/input.txt"));
    }

    @Test
    void testLargeFile() throws IOException {
        SubstringSearch actual = new SubstringSearch("Анна Павловна");
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2203, 2355, 3692, 5116));
        assertEquals(expected, actual.find("src/test/resources/input1.txt"));
    }

    @Test
    void testLargeGeneratedFile() throws IOException {
        // Генерация большого файла
        String fileName = "src/test/resources/largeTestFile.txt";
        String pattern = "abracadabra";

        // Создадим строку, которая будет содержать 10 миллионов символов
        StringBuilder largeContent = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            largeContent.append("abracadabra");  // Добавляем паттерн, чтобы получить много вхождений
        }

        // Запишем содержимое в файл
        Files.write(Paths.get(fileName), largeContent.toString().getBytes());

        // Создаем объект для поиска
        SubstringSearch actual = new SubstringSearch(pattern);

        // Выполняем поиск
        ArrayList<Integer> matches = actual.find(fileName);

        // Проверяем, что нашли вхождения
        assertTrue(matches.size() > 0, "Должно быть хотя бы одно совпадение");
        assertEquals(1000000, matches.size(), "Количество совпадений должно быть равно количеству повторений паттерна");

        // Проверяем, что совпадения начинаются с правильных позиций
        assertEquals(0, matches.get(0), "Первое совпадение должно начинаться с позиции 0");
        assertEquals(11, matches.get(1), "Второе совпадение должно начинаться с позиции 11");

        // Удаляем тестовый файл после завершения теста
        Files.delete(Paths.get(fileName));

    }

}