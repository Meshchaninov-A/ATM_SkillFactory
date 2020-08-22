package files;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализующий работу по сохранению и получению данных из файловой базы данных
 */

public final class FileUtil {
    private static final FileUtil fileUtil = new FileUtil();

    private FileUtil() {
    }

    /**
     * Считать файловую базу данных
     *
     * @param file файл в котором хранится база данных
     * @return Список строк в виде List<String>
     */

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

    /**
     * Сохранить данные по карточным счетам в файловую базу данных
     *
     * @param file  файл в котором хранится база данных
     * @param lines строковое представление данных по карточным счетам клиента
     */

    public void writeToFile(File file, String lines) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(lines);
        } catch (IOException e) {
            System.out.println("Не удалось записать данные в файл " + e.getMessage());
        }
    }

    /**
     * Получить ссылку на инстанс FileUtil
     *
     * @return ссылка на инстанс FileUtil
     */

    public static FileUtil getInstance() {
        return fileUtil;
    }
}
