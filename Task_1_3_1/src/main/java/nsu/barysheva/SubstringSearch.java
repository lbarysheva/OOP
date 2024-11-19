package nsu.barysheva;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SubstringSearch {

    private final String pattern;

    /**
     * Constructor to initialize the pattern to search for.
     */
    public SubstringSearch(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Method for searching the pattern in the file using the Knuth-Morris-Pratt algorithm.
     */
    public ArrayList<Integer> find(String filePath) throws IOException {
        ArrayList<Integer> matchIndices = new ArrayList<>();
        StringBuilder content = new StringBuilder();

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                content.append(new String(buffer, 0, bytesRead, StandardCharsets.UTF_8));
            }
        }

        searchPattern(content.toString(), matchIndices);
        return matchIndices;
    }

    /**
     * Knuth-Morris-Pratt pattern matching algorithm.
     */
    private void searchPattern(String text, ArrayList<Integer> matchIndices) {
        int[] prefixTable = buildPrefixTable(pattern);
        int textIndex = 0;
        int patternIndex = 0;

        while (textIndex < text.length()) {
            if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
                textIndex++;
                patternIndex++;
            }

            if (patternIndex == pattern.length()) {
                matchIndices.add(textIndex - patternIndex); // Match found, add index
                patternIndex = prefixTable[patternIndex - 1];
            } else if (textIndex < text.length() && pattern.charAt(patternIndex) != text.charAt(textIndex)) {
                if (patternIndex != 0) {
                    patternIndex = prefixTable[patternIndex - 1];
                } else {
                    textIndex++;
                }
            }
        }
    }

    /**
     * Method to construct the prefix table (also known as LPS array).
     */
    private int[] buildPrefixTable(String pattern) {
        int[] prefixTable = new int[pattern.length()];
        int length = 0;
        prefixTable[0] = 0; // prefixTable[0] is always 0

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
