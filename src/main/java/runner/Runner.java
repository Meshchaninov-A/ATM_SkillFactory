package runner;

import card.UserCardOperations;
import files.ProjectFileWorker;

import java.io.File;

/**
 * Класс, отвечающий за запуск работы приложения
 */

public class Runner {

    public static void main(String[] args) throws InterruptedException {
        File baseFile;
        if (args.length == 1) {
            baseFile = new File(args[0]);
        } else {
            baseFile = new File("user_base.csv");
        }
        ProjectFileWorker fileWorker = new ProjectFileWorker(baseFile);
        if (fileWorker.validateCardBase()) {
            UserCardOperations operations = new UserCardOperations(fileWorker);
            BankApplicationConsole application = new BankApplicationConsole(operations);
            application.start();
            application.join();
        }
        System.out.println("Программа остановлена");
    }
}
