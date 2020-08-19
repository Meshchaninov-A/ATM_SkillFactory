package runner;

import card.CardArray;
import card.UserCard;
import card.UserCardOperations;
import exceptions.CardBaseValidationException;
import exceptions.CardNotFoundException;
import utils.FileUtil;

import java.io.File;
import java.io.IOException;


public class BankApplication {
    private final File userCardBaseFile;
    private final ScannerWithValidation userInputScanner = new ScannerWithValidation();
    private UserCardOperations operations;
    private CardArray userCards;


    BankApplication(File userCardBaseFile) {
        this.userCardBaseFile = userCardBaseFile;
    }

    void run() {
        try {
            if (!userCardBaseFile.exists() || !FileUtil.validateFileUserCardBase(userCardBaseFile)) {
                throw new CardBaseValidationException("Валидация базы карт неуспешна");
            }
            if (authorization()) {
                menu();
            } else {
                throw new SecurityException("Неверный id или пароль");
            }
        } catch (CardBaseValidationException | CardNotFoundException e) {
            System.out.println("Программа завершилась с ошибкой: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Проблема с доступом к файлу: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean authorization() throws IOException, CardNotFoundException {
        System.out.println("Введите ваш id: ");
        long id = userInputScanner.getLongFromScanner();
        System.out.println("Введите пин-код: ");
        short pinCode = userInputScanner.getShortFromScanner();
        userCards = FileUtil.readCardFromBaseFile(userCardBaseFile);
        operations = new UserCardOperations(userCards.getCardById(id));
        if (userCards.getCardById(id).equals(UserCard.EMPTY_CARD)) {
            throw new CardNotFoundException("Не найден счет с id " + id);
        }
        return operations.authenticate(pinCode);
    }


    void menu() {
        System.out.println("Добро пожаловать в меню банкомата.");
        int i;
        boolean exitFlag = false;
        do {
            System.out.println("Выберите нужный пункт меню: ");
            i = userInputScanner.getIntFromScanner();
            switch (i) {
                case 1:
                    System.out.println("Нажат пункт 1, возвращаемся к меню");
                    break;
                case 2:
                    System.out.println("Нажат пункт 2, возвращаемся в меню");
                    break;
                default:
                    exitFlag = true;
                    System.out.println("Выход из программы");
            }
        } while (!exitFlag);
    }

}
