import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SubstringFinder {

    public static List<Integer> find(String fileName, String searchString) {
        List<Integer> indices = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))) {
            int lineNumber = 0;
            String line;

            // Читаем файл построчно
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                currentLine.append(line).append("\n");
                int currentIndex = 0;

                // Искать подстроку в текущей строке
                while ((currentIndex = currentLine.indexOf(searchString, currentIndex)) != -1) {
                    // Вычисляем глобальный индекс
                    int globalIndex = currentIndex + (lineNumber - 1) * (line.length() + 1);
                    indices.add(globalIndex);
                    currentIndex++;
                }

                // Убираем строку из StringBuilder, чтобы не сохранять ненужные данные
                currentLine.setLength(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return indices;
    }

    public static void main(String[] args) {
        String fileName = "input.txt";
        String searchString = "бра";
        List<Integer> indices = find(fileName, searchString);
        System.out.println(indices);
    }
}