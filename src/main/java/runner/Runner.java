package runner;

import java.io.File;

public class Runner {
    public static void main(String[] args) {
        //File cardBaseFile = new File(args[0]);
        File cardBaseFile = new File("user_base.csv");
        BankApplication application = new BankApplication(cardBaseFile);
        application.run();
    }
}
