package files;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class FileUtil {
    private static final FileUtil fileUtil = new FileUtil();

    private FileUtil() {
    }

    public List<String> readFromFile(File file) {
        List<String> fileLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Файл не найден : " + e.getMessage());
        }
        return fileLines;
    }

    public void writeToFile(File file, String lines) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(lines);
        } catch (IOException e) {
            System.out.println("Не удалось записать данные в файл " + e.getMessage());
        }
    }

    public static FileUtil getInstance() {
        return fileUtil;
    }
}
