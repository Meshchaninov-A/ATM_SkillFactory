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

            System.out.println("Баланс до теста: " + operations.getUserCards().getCardById(1).getFunds());

            //BankApplicationConsole application = new BankApplicationConsole(operations);
            DraftApplication service = new DraftApplication(operations);
            service.setDaemon(true);

/*            DraftApplication service2 = new DraftApplication(operations);
            service2.setDaemon(true);

            DraftApplication service3 = new DraftApplication(operations);
            service3.setDaemon(true);*/

            //application.start();
            service.start();
/*
            service2.start();
            service3.start();
*/

            //application.join();

            service.join();
/*
            service2.join();
            service3.join();
*/

            System.out.println("Баланс баланс после теста: " + operations.getUserCards().getCardById(1).getFunds());
        }
        System.out.println("Программа остановлена");
    }
}
