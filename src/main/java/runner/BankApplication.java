package runner;

import card.CardArray;
import card.RequestContext;
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
            RequestContext rq = authorization();
            if (rq.isAuthorized()) {
                menu(rq);
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

    private RequestContext authorization() throws IOException, CardNotFoundException {
        System.out.println("Введите id вашей карты: ");
        long id = userInputScanner.getLongFromScanner();
        System.out.println("Введите пин-код: ");
        short pinCode = userInputScanner.getShortFromScanner();
        userCards = FileUtil.readCardFromBaseFile(userCardBaseFile);
        operations = new UserCardOperations(userCards.getCardById(id));

        UserCard uc = userCards.getCardById(id);

        if (uc.equals(UserCard.EMPTY_CARD)) {
            throw new CardNotFoundException("Не найден счет с id " + id);
        }

        RequestContext rq;

        if (operations.authenticate(pinCode))
            rq = new RequestContext(true, uc);
        else rq = new RequestContext(false, UserCard.EMPTY_CARD);

        return rq;
    }


    void menu(RequestContext rq) throws IOException {
        System.out.println("\n" + rq.getCard().getUserName() + ", добро пожаловать в меню банкомата\n");

        System.out.println("Вам доступны следующие операции:\n" +
                "1. Запросить баланс карты\n" +
                "2. Перевести с карты на карту другого клиента\n" +
                "3. Снять средства с карты\n" +
                "4. Пополнение баланса\n" +
                "5. Выход\n");

        int i;
        boolean exitFlag = false;
        do {
            System.out.println("Выберите нужный пункт меню: ");
            i = userInputScanner.getIntFromScanner();
            switch (i) {
                case 1:
                    getCardBalance(rq);
                    break;
                case 2:
                    System.out.println("Нажат пункт 2, возвращаемся в меню");
                    FileUtil.writeCardArrayToFile(userCardBaseFile, userCards);
                    break;
                case 3:
                    withdrawFunds(rq);
                    break;
                case 4:
                    depositFunds(rq);
                    break;
                case 5:
                    exitFlag = true;
                    System.out.println("Выход из программы");
                    break;
            }
        } while (!exitFlag);
    }

    private void depositFunds(RequestContext rq) throws IOException {
        System.out.println("Введите количество пополняемых на карту средств");
        Long sum = userInputScanner.getLongFromScanner();
        operations.addFunds(sum);
        FileUtil.writeCardArrayToFile(userCardBaseFile, userCards);
    }

    private void withdrawFunds(RequestContext rq) throws IOException {
        System.out.println("Введите количество снимаемых с карты средств");
        Long sum = userInputScanner.getLongFromScanner();
        if (!operations.giveOutFunds(sum)) System.out.println("Недостаточно средств");
        FileUtil.writeCardArrayToFile(userCardBaseFile, userCards);
    }

    private void getCardBalance(RequestContext rq) {
        System.out.println("Баланс Вашей карты: " + rq.getCard().getFunds());
    }

}
