package runner;

import card.CardArray;
import card.ClientSession;
import card.UserCard;
import card.UserCardOperations;
import card.operations.AddFundsOperation;
import card.operations.Operation;
import utils.FileUtil;

import java.io.IOException;

import static runner.Runner.userCardBaseFile;

public class DraftApplication extends Thread {
    private final ScannerWithValidation userInputScanner = new ScannerWithValidation();
    private ClientSession userSession;
    private UserCardOperations operations;
    private CardArray userCards;

    public void run() {
        System.out.println("START");
        try {
            userCards = FileUtil.readCardFromBaseFile(userCardBaseFile);
            //do {
                UserCard card = userCards.getCardById(1);
                operations = new UserCardOperations(userCards);
                userSession = new ClientSession(card);
                userSession.authenticate((short) 1234);
                for (int i = 0; i < 10000; i++) {
                    operations.addFunds(card.getCardId(), 10L);
                }

//                System.out.println("Транзакция успешна");
          //  } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
