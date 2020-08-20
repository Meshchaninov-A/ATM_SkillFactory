package utils;

import card.CardArray;
import card.UserCard;
import exceptions.CardBaseValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс по работе с файлом базы карт пользователей
 *
 * @author Meshchaninov A.A.
 */
public final class FileUtil {

    private FileUtil() {
    }

    /**
     * Получение всех банковских карточек UserCard из файла с базой
     *
     * @param baseFile файл, в котором хранится база карточек.
     * @return экземпляр объекта CardArray. Пустая карточка, в случае отсутствия нужного id в базе
     * @throws IOException ошибка ввода/вывода при чтении из файла
     */
    public synchronized static CardArray readCardFromBaseFile(File baseFile) throws IOException {
        List<UserCard> cards = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(baseFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cardInfo = line.trim().split(":");
                int cardIdCurrent = Integer.parseInt(cardInfo[0]);
                String userName = cardInfo[1];
                long funds = Long.parseLong(cardInfo[2]);
                short pinCode = Short.parseShort(cardInfo[3]);
                cards.add(new UserCard(cardIdCurrent, userName, funds, pinCode));
            }
        }
        UserCard[] userCardsArray = new UserCard[cards.size()];
        return new CardArray(cards.toArray(userCardsArray));
    }


    /**
     * Метод по записи всех банковских карточек в виде CardArray в файл
     *
     * @param baseFile файл, в котором хранится информация о банковских картах
     * @param cards    Экземпляр CardArray
     * @throws IOException ошибка ввода/вывода при записи в файл
     */
    public synchronized static void writeCardArrayToFile(File baseFile, CardArray cards) {
        try (FileWriter writer = new FileWriter(baseFile)) {
            writer.write(cards.cardArrayToConfigString());
        } catch (IOException e) {
            System.out.println("Не удалось записать данные в базу " + e.getMessage());
        }
    }

    /**
     * Метод по валидации базы карт на корректность id карточек.
     *
     * @param baseFile файл, в котором хранится база карточек.
     * @return true- если все id карт разные, false- если имеются две карточки с одним id
     * @throws CardBaseValidationException ошибка ввода/вывода при чтении из файла
     */
    public static boolean validateFileUserCardBase(File baseFile) throws CardBaseValidationException {
        Set<Integer> idUserCards = new HashSet<>();
        int countOfCards = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(baseFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int cardId = Integer.parseInt(line.split(":")[0]);
                idUserCards.add(cardId);
                countOfCards++;
            }
        } catch (IOException e) {
            throw new CardBaseValidationException("Валидация базы карт неуспешна -" + e.getMessage());
        }
        return idUserCards.size() == countOfCards;
    }
}
