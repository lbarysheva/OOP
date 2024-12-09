package nsu.barysheva;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        String pattern = "бра";
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 8));
        ArrayList<Integer> actual = SubstringSearch.find("src/test/resources/input.txt", pattern);
        assertEquals(expected, actual);
    }

    @Test
    void testEmptyArr() throws IOException {
        String pattern = " ";
        ArrayList<Integer> expected = new ArrayList<>();
        ArrayList<Integer> actual = SubstringSearch.find("src/test/resources/input.txt", pattern);
        assertEquals(expected, actual);
    }

    @Test
    void testLargeFile() throws IOException {
        String pattern = "Анна Павловна";
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2203, 2355, 3692, 5116));
        ArrayList<Integer> actual = SubstringSearch.find("src/test/resources/input1.txt", pattern);
        assertEquals(expected, actual);
    }

    @Test
    void foreingTest() throws IOException {
        String pattern = "界";
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(7, 17));
        ArrayList<Integer> actual = SubstringSearch.find("src/test/resources/input2.txt", pattern);
        assertEquals(expected, actual);
    }

    @Test
    public void testFindSubstringInBigString() throws IOException {
        try (FileWriter writer = new FileWriter("test.txt", true)) {
            writer.write("абра".repeat(2000000));
        }

        List<Integer> indices = SubstringSearch.find("test.txt", "бра");

        assertEquals(2000000, indices.size());

        File file = new File("test.txt");
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }

    @Test
    void emojiTest() throws IOException {
        String fileName = "src/test/resources/inputEmoji.txt";
        String pattern = "😊"; // Эмодзи для поиска
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(5, 12, 19)); // Ожидаемые позиции эмодзи в файле

        // Создание файла с содержимым, содержащим эмодзи
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName), StandardCharsets.UTF_8)) {
            writer.write("Hello😊world😊again😊!");
        }

        try {
            ArrayList<Integer> actual = SubstringSearch.find(fileName, pattern);

            // Проверка совпадений
            assertEquals(expected, actual, "Результаты поиска эмодзи должны совпадать с ожидаемыми позициями.");
        } finally {
            // Удаление файла после теста
            Files.delete(Paths.get(fileName));
        }
    }
}