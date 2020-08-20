package runner;

import card.CardArray;
import card.ClientSession;
import card.UserCard;
import card.UserCardOperations;
import card.operations.*;
import utils.FileUtil;

import java.io.IOException;

import static runner.Runner.userCardBaseFile;


public class BankApplicationConsole {
    private final ScannerWithValidation userInputScanner = new ScannerWithValidation();
    private ClientSession userSession;
    private UserCardOperations operations;
    private CardArray userCards;


    void run() {
        try {
            userCards = FileUtil.readCardFromBaseFile(userCardBaseFile);
            operations = new UserCardOperations(userCards);
            for (int i = 0; ; i++) {
                if (authorization()) {
                    menu(userSession);
                    break;
                } else if (2 - i > 0) {
                    System.out.println("Логин не успешен, осталось попыток: " + (2 - i));
                } else {
                    System.out.println("Попытки входа в систему закончились");
                }
            }
        } catch (IOException e) {
            System.out.println("Проблема с доступом к файлу: " + e.getMessage());
        }
    }

    private boolean authorization() {
        long id = userInputScanner.getLongFromScanner("Введите ваш id: ");
        short pinCode = userInputScanner.getShortFromScanner("Введите пин-код: ");
        userSession = new ClientSession(userCards.getCardById(id));
        if (userSession.getCardInfo().equals(UserCard.EMPTY_CARD)) {
            return false;
        }
        return userSession.authenticate(pinCode);
//        System.out.println("Введите id вашей карты: ");
//        long id = userInputScanner.getLongFromScanner();
//        System.out.println("Введите пин-код: ");
//        short pinCode = userInputScanner.getShortFromScanner();
//        userCards = FileUtil.readCardFromBaseFile(userCardBaseFile);
//        operations = new UserCardOperations(userCards.getCardById(id));
//
//        UserCard uc = userCards.getCardById(id);
//
//        if (uc.equals(UserCard.EMPTY_CARD)) {
//            throw new CardNotFoundException("Не найден счет с id " + id);
//        }
//
//        RequestContext rq;
//
//        if (operations.authenticate(pinCode))
//            rq = new RequestContext(true, uc);
//        else rq = new RequestContext(false, UserCard.EMPTY_CARD);
//
//        return rq;
    }


    void menu(ClientSession cs) throws IOException {
        System.out.println("\n" + cs.getCardInfo().getUserName() + ", добро пожаловать в меню банкомата");
        OperationsEnum operationsEnum;
        Operation operation;
        ResultOperation resultOperation = null;
        boolean exitFlag = false;
        do {
            showHelpMessage();
            operationsEnum = OperationsEnum.getEnumById(userInputScanner.getIntFromScanner());
            switch (operationsEnum) {
                case ADD_FUNDS:
                    operation = new AddFundsOperation();
                    resultOperation = operation.doOperation(userSession, operations, userInputScanner);
                    break;
                case WITHDRAW_FUNDS:
                    operation = new WithdrawFundsOperation();
                    resultOperation = operation.doOperation(userSession, operations, userInputScanner);
                    break;
                case GET_CARD_INFO:
                    operation=new GetFundsInfoOperation();
                    resultOperation=operation.doOperation(userSession,operations,userInputScanner);
                    break;
                case TRANSFER_FUNDS:
                    transferFromCardToCard(cs);
                    break;
                case EXIT_PROGRAM:
                    exitFlag = true;
                    break;
                case UNKNOWN_OPERATION:
                    System.out.println("Неизвестная операция");
                    break;
            }
        } while (!exitFlag);
        System.out.println("Выход из программы");
    }

    private boolean transferFromCardToCard(ClientSession rq) throws IOException {
        System.out.println("Введите id карты на которую вы хотите перевести средства:");
        Long id = userInputScanner.getLongFromScanner();
        UserCard uc = userCards.getCardById(id);
        if (uc.equals(UserCard.EMPTY_CARD))
            System.out.println("Введенной карты нет в ситеме");
        else {
            System.out.println("Введите количество средств, которое вы хотите перевести:");
            Long sum = userInputScanner.getLongFromScanner();
            if (operations.transferFunds(uc, sum))
                FileUtil.writeCardArrayToFile(userCardBaseFile, userCards);
        }
        return true;
    }

//    private void depositFunds(ClientSession rq) throws IOException {
//        System.out.println("Введите количество пополняемых на карту средств");
//        Long sum = userInputScanner.getLongFromScanner();
//        operations.addFunds(sum);
//        FileUtil.writeCardArrayToFile(userCardBaseFile, userCards);
//    }

//    private void withdrawFunds(ClientSession rq) throws IOException {
//        System.out.println("Введите количество снимаемых с карты средств");
//        Long sum = userInputScanner.getLongFromScanner();
//        if (operations.giveOutFunds(sum))
//            FileUtil.writeCardArrayToFile(userCardBaseFile, userCards);
//    }

    private void showHelpMessage() {
        System.out.println("\nВыберите нужный пункт меню: ");
        for (OperationsEnum value : OperationsEnum.values()) {
            if (value.getId() > 0) {
                System.out.println(value.getId() + ". " + value.getDescription());
            }
        }
    }

    private void getCardBalance(ClientSession rq) {
        System.out.println("Баланс Вашей карты: " + rq.getCardInfo().getFunds());
    }
}