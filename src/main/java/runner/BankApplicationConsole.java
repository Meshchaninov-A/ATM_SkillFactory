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
                    break;
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
    }


    void menu(ClientSession cs) {
        System.out.println("\n" + cs.getCardInfo().getUserName() + ", добро пожаловать в меню банкомата");
        OperationsEnum operationsEnum;
        Operation operation;
        ResultOperation resultOperation = null;
        do {
            showHelpMessage();
            operationsEnum = OperationsEnum.getEnumById(userInputScanner.getIntFromScanner());
            if (operationsEnum.equals(OperationsEnum.ADD_FUNDS)) {
                operation = new AddFundsOperation();
            } else if (operationsEnum.equals(OperationsEnum.WITHDRAW_FUNDS)) {
                operation = new WithdrawFundsOperation();
            } else if (operationsEnum.equals(OperationsEnum.GET_CARD_INFO)) {
                operation = new GetFundsInfoOperation();
            } else if (operationsEnum.equals(OperationsEnum.TRANSFER_FUNDS)) {
                operation = new TransferFundsOperation();
            } else if (operationsEnum.equals(OperationsEnum.EXIT_PROGRAM)) {
                break;
            } else {
                System.out.println(ResultOperation.NOT_SUPPORTED_COMMAND.getOperationResult());
                continue;
            }
            resultOperation = operation.doOperation(userSession, operations, userInputScanner);
            System.out.println(resultOperation.getOperationResult());
        } while (true);
        System.out.println("Выход из программы");
    }


    private void showHelpMessage() {
        System.out.println("\nВыберите нужный пункт меню: ");
        for (OperationsEnum value : OperationsEnum.values()) {
            if (value.getId() > 0) {
                System.out.println(value.getId() + ". " + value.getDescription());
            }
        }
    }

}