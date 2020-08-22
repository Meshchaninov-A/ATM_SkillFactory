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

            BankApplicationConsole application = new BankApplicationConsole(operations);

            BankApplicationConsole application2 = new BankApplicationConsole(operations);

            BankApplicationConsole application3 = new BankApplicationConsole(operations);

            BankApplicationConsole application4 = new BankApplicationConsole(operations);

            DraftApplication service = new DraftApplication(operations);

            DraftApplication service2 = new DraftApplication(operations);

            DraftApplication service3 = new DraftApplication(operations);
            //service3.setDaemon(true);*//*

            DraftApplication service4 = new DraftApplication(operations);
            //service4.setDaemon(true);

            /*DraftApplication service5 = new DraftApplication(operations);
            service5.setDaemon(true);*/

            application.start();
            application2.start();
            application3.start();
            application4.start();

            service.start();
            service2.start();
            service3.start();
            service4.start();
/*            service3.start();
            service4.start();
            service5.start();*/

            //application.join();
            application.join();
            application2.join();
            application3.join();
            application4.join();

            service.join();
            service2.join();
            service3.join();
            service4.join();

/*            service3.join();
            service4.join();
            service5.join();*/

            System.out.println("Баланс баланс после теста: " + operations.getUserCards().getCardById(1).getFunds());
        }
        System.out.println("Программа остановлена");
    }
}
