//package runner;
//
//import card.CardArray;
//import card.ClientSession;
//import card.UserCard;
//import card.UserCardOperations;
//import card.operations.OperationsEnum;
//import exceptions.NotSuccessLoginException;
//import utils.FileUtil;
//
//import java.io.IOException;
//
//import static runner.Runner.userCardBaseFile;
//
//
//public class BankApplicationConsole1 {
//    private final ScannerWithValidation userInputScanner = new ScannerWithValidation();
//    private ClientSession userSession;
//    private UserCardOperations operations;
//    private CardArray userCards;
//
//
//    void run() {
//        try {
//            userCards = FileUtil.readCardFromBaseFile(userCardBaseFile);
//            for (int i = 0; ; i++) {
//                if (authorization()) {
//                    menu();
//                    break;
//                } else if (2 - i > 0) {
//                    System.out.println("Логин не успешен, осталось попыток: " + (2 - i));
//                } else {
//                    throw new NotSuccessLoginException("Попытки входа в систему закончились");
//                }
//            }
//        } catch (NotSuccessLoginException e) {
//            System.out.println("Программа завершилась с ошибкой: " + e.getMessage());
//        } catch (IOException e) {
//            System.out.println("Проблема с доступом к файлу: " + e.getMessage());
//        }
//    }
//
//    private boolean authorization() {
//        long id = userInputScanner.getLongFromScanner("Введите ваш id: ");
//        short pinCode = userInputScanner.getShortFromScanner("Введите пин-код: ");
//        userSession = new ClientSession(userCards.getCardById(id));
//        if (userSession.getCardInfo().equals(UserCard.EMPTY_CARD)) {
//            return false;
//        }
//        return userSession.authenticate(pinCode);
//    }
//
//
//    void menu() throws IOException {
//        System.out.println("Добро пожаловать в меню банкомата.");
//        int i;
//        boolean exitFlag = false;
//        do {
//            showHelpMessage();
//            i = userInputScanner.getIntFromScanner();
//            switch (i) {
//                case 1:
//                    System.out.println("Нажат пункт 1, возвращаемся к меню");
//                    FileUtil.writeCardArrayToFile(userCardBaseFile, userCards);
//                    break;
//                case 2:
//                    System.out.println("Нажат пункт 2, возвращаемся в меню");
//                    break;
//                default:
//                    exitFlag = true;
//                    System.out.println("Выход из программы");
//            }
//        } while (!exitFlag);
//    }
//
//}
