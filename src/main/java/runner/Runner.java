package runner;

import exceptions.CardBaseValidationException;
import utils.FileUtil;

import java.io.File;

public class Runner {
    public static File userCardBaseFile;

    public static void main(String[] args) {
        //File cardBaseFile = new File(args[0]);
        userCardBaseFile = new File("user_base.csv");
        try {
            if (userCardBaseFile.exists() || FileUtil.validateFileUserCardBase(userCardBaseFile)) {
                BankApplicationConsole application = new BankApplicationConsole();
                application.run();
            }
        } catch (CardBaseValidationException e) {
            System.out.println(e.getMessage());
        }

    }
}
