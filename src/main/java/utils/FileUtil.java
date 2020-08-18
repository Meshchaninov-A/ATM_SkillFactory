package utils;

import java.io.*;

public class FileUtil {
    private final static String userBaseFileName = "/user_base.csv";

    public static void readUserBaseFile() {
        try (InputStream inputStream = FileUtil.class.getResourceAsStream(userBaseFileName)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
