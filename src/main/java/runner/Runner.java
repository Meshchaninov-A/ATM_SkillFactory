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
            Thread console = new Thread(application);
            console.start();
            DraftApplication service = new DraftApplication();
            Thread web = new Thread(service);
            web.setDaemon(true);
            web.start();
            console.join();
        }
        System.out.println("Программа остановлена");
    }
}
