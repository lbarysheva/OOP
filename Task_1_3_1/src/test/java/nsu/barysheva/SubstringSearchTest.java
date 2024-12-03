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
    void testLargeGeneratedFile() throws IOException {
        String fileName = "src/test/resources/largeTestFile.txt";
        String pattern = "abracadabra";

        // Создание большого файла
        StringBuilder largeContent = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            largeContent.append("abracadabra");
        }
        Files.write(Paths.get(fileName), largeContent.toString().getBytes());

        try {
            ArrayList<Integer> matches = SubstringSearch.find(fileName, pattern);

            assertTrue(matches.size() > 0, "Должно быть хотя бы одно совпадение");
            assertEquals(1000000, matches.size(), "Количество совпадений должно быть равно количеству повторений паттерна");
            assertEquals(0, matches.get(0), "Первое совпадение должно начинаться с позиции 0");
            assertEquals(11, matches.get(1), "Второе совпадение должно начинаться с позиции 11");
        }
        finally {
            Files.delete(Paths.get(fileName));
        }
    }

    @Test
    void foreingTest() throws IOException {
        String pattern = "界";
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(7, 17));
        ArrayList<Integer> actual = SubstringSearch.find("src/test/resources/input2.txt", pattern);
        assertEquals(expected, actual);
    }

}