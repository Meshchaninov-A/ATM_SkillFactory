package utils;

import card.CardArray;
import card.UserCard;

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
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        UserCard[] userCardsArray = new UserCard[cards.size()];
        return new CardArray(cards.toArray(userCardsArray));
    }


    public synchronized static void writeCardArrayToFile(File baseFile, CardArray cards) throws IOException {
        try (FileWriter writer = new FileWriter(baseFile)) {
            writer.write(cards.cardArrayToConfigString());
        }
    }

    /**
     * Метод по валидации базы карт на корректность id карточек.
     *
     * @param baseFile файл, в котором хранится база карточек.
     * @return true- если все id карт разные, false- если имеются две карточки с одним id
     * @throws IOException ошибка ввода/вывода при чтении из файла
     */
    public static boolean validateFileUserCardBase(File baseFile) throws IOException {
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
            throw new IOException(e.getMessage());
        }
        return idUserCards.size() == countOfCards;
    }
}
