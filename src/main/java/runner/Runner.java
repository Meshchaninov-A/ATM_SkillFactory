package runner;

import card.UserCardOperations;
import files.ProjectFileWorker;

import java.io.File;

public class Runner {

    public static void main(String[] args) throws InterruptedException {
        ProjectFileWorker fileWorker = new ProjectFileWorker(new File("user_base.csv"));
        //File cardBaseFile = new File(args[0]);
        if (fileWorker.validateCardBase()) {
            UserCardOperations operations = new UserCardOperations(fileWorker);
            BankApplicationConsole application = new BankApplicationConsole(operations);
            DraftApplication service = new DraftApplication(operations);
            service.setDaemon(true);
            application.start();
            service.start();
            application.join();
            service.join();
        }
        System.out.println("Программа остановлена");
    }
}
