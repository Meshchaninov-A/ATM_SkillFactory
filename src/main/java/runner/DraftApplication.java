package runner;

import card.ClientSession;
import card.UserCard;
import card.UserCardOperations;

public class DraftApplication extends Thread {
    private final ScannerWithValidation userInputScanner = new ScannerWithValidation();
    private ClientSession userSession;
    private UserCardOperations operations;

    public DraftApplication(UserCardOperations cardOperations) {
        operations = cardOperations;
    }

    public void run() {
        System.out.println("START");
        UserCard card = operations.getUserCards().getCardById(1);
        userSession = new ClientSession(card);
        userSession.authenticate((short) 1234);
        for (int i = 0; i < 100000; i++) {
            //operations.addFunds(card.getCardId(), 10L);
            //operations.withdrawFunds(card.getCardId(), 5L);
            operations.transferFunds(2, 1, 10);
            operations.transferFunds(0, 1, 10);
            operations.withdrawFunds(1,1);
            operations.addFunds(1,1);
        }
        System.out.println("Транзакция успешна");
    }
}