package runner;

import utils.FileUtil;

import java.io.File;

public class Runner {
    public static File userCardBaseFile;

    public static void main(String[] args) throws InterruptedException {
        //File cardBaseFile = new File(args[0]);
        userCardBaseFile = new File("user_base.csv");
        if (FileUtil.validateFileUserCardBase(userCardBaseFile)) {
            BankApplicationConsole application = new BankApplicationConsole();
            application.start();
            DraftApplication service = new DraftApplication();
            service.setDaemon(true);
            service.start();
            service.join();
        }
        System.out.println("Программа остановлена");
    }
}
