package nsu.barysheva;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SubstringSearch {

    /**
     * Метод для поиска паттерна в файле с использованием алгоритма Кнута-Морриса-Пратта.
     *
     * @param filePath путь к файлу
     * @param pattern  строка для поиска
     * @return список индексов начала вхождений
     * @throws IOException если возникает ошибка ввода-вывода
     */
    public static ArrayList<Integer> find(String filePath, String pattern) throws IOException {
        ArrayList<Integer> matchIndices = new ArrayList<>();
        byte[] buffer = new byte[4096]; // Размер буфера для чтения файла
        StringBuilder overflow = new StringBuilder(); // Для хранения стыков буферов
        int[] prefixTable = buildPrefixTable(pattern); // Префиксная таблица для КМП
        int offset = 0; // Смещение относительно начала файла

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // Преобразуем байты в строку
                String currentChunk = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);

                // Объединяем текущий буфер с остатком предыдущего
                String combinedChunk = overflow.append(currentChunk).toString();

                // Ищем паттерн в объединённом тексте
                int textIndex = 0;
                int patternIndex = 0;

                while (textIndex < combinedChunk.length()) {
                    if (pattern.charAt(patternIndex) == combinedChunk.charAt(textIndex)) {
                        textIndex++;
                        patternIndex++;
                    }

                    if (patternIndex == pattern.length()) {
                        // Нашли совпадение
                        matchIndices.add(offset + textIndex - patternIndex);
                        patternIndex = prefixTable[patternIndex - 1];
                    } else if (textIndex < combinedChunk.length() && pattern.charAt(patternIndex) != combinedChunk.charAt(textIndex)) {
                        if (patternIndex != 0) {
                            patternIndex = prefixTable[patternIndex - 1];
                        } else {
                            textIndex++;
                        }
                    }
                }

                // Сохраняем остаток строки для следующей итерации
                int lastValidIndex = Math.max(0, combinedChunk.length() - pattern.length() + 1);
                overflow.setLength(0);
                overflow.append(combinedChunk.substring(lastValidIndex));

                // Обновляем смещение
                offset += lastValidIndex;
            }
        }

        return matchIndices;
    }

    /**
     * Алгоритм поиска подстроки с использованием Кнута-Морриса-Пратта.
     */
    private static int searchPattern(String text, String pattern, int[] prefixTable, ArrayList<Integer> matchIndices, int offset) {
        int textIndex = 0;
        int patternIndex = 0;

        while (textIndex < text.length()) {
            if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
                textIndex++;
                patternIndex++;
            }

            if (patternIndex == pattern.length()) {
                // Нашли совпадение
                matchIndices.add(offset + textIndex - patternIndex);
                patternIndex = prefixTable[patternIndex - 1];
            } else if (textIndex < text.length() && pattern.charAt(patternIndex) != text.charAt(textIndex)) {
                // Несоответствие
                if (patternIndex != 0) {
                    patternIndex = prefixTable[patternIndex - 1];
                } else {
                    textIndex++;
                }
            }
        }

        return textIndex; // Возвращаем индекс последнего обработанного символа
    }

    /**
     * Метод для построения префиксной таблицы (LPS-массив).
     */
    private static int[] buildPrefixTable(String pattern) {
        int[] prefixTable = new int[pattern.length()];
        int length = 0;
        prefixTable[0] = 0; // prefixTable[0] всегда 0

        for (int i = 1; i < pattern.length(); i++) {
            while (length > 0 && pattern.charAt(i) != pattern.charAt(length)) {
                length = prefixTable[length - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                prefixTable[i] = length;
            } else {
                prefixTable[i] = 0;
            }
        }
        return prefixTable;
    }
}
