package runner;

import card.ClientSession;
import card.UserCard;
import card.UserCardOperations;
import card.operations.*;


public class BankApplicationConsole extends Thread {
    private final ScannerWithValidation userInputScanner = new ScannerWithValidation();
    private ClientSession userSession;
    private final UserCardOperations operations;

    public BankApplicationConsole(UserCardOperations cardOperations) {
        this.operations = cardOperations;
    }

    public void run() {
        for (int i = 0; ; i++) {
            if (authorization()) {
                menu();
                userSession.closeSession();
                break;
            } else if (2 - i > 0) {
                System.out.println("Логин не успешен, осталось попыток: " + (2 - i));
            } else {
                System.out.println("Попытки входа в систему закончились");
                break;
            }
        }
    }

    private boolean authorization() {
        try {
            long id = userInputScanner.getLongFromScanner("Введите ваш id: ");
            short pinCode = userInputScanner.getShortFromScanner("Введите пин-код: ");
            userSession = new ClientSession(operations.getUserCards().getCardById(id));
            if (userSession.getCardInfo().equals(UserCard.EMPTY_CARD)) {
                return false;
            }
            return userSession.authenticate(pinCode);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    void menu() {
        System.out.println("\n" + userSession.getCardInfo().getUserName() + ", добро пожаловать в меню банкомата");
        OperationsEnum operationsEnum;
        Operation operation;
        ResultOperation resultOperation;
        do {
            try {
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
                    System.out.println(ResultOperation.NOT_SUPPORTED_OPERATION.getOperationResult());
                    continue;
                }
                resultOperation = operation.doOperation(userSession, operations, userInputScanner);
                System.out.println(resultOperation.getOperationResult());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
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