package utils;

import card.UserCard;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс по работе с файлом базы карт пользователей
 *
 * @author Meshchaninov A.A.
 */
public class FileUtil {


    /**
     * Получение объекта UserCard из файла с базой по id
     *
     * @param baseFile     файл, в котором хранится база карточек.
     * @param cardIdNeeded id карточки пользователя, которую необходимо найти
     * @return экземпляр объекта UserCard. Пустая карточка, в случае отсутствия нужного id в базе
     * @throws IOException ошибка ввода/вывода при чтении из файла
     */
    public static UserCard readCardFromBaseFile(File baseFile, long cardIdNeeded) throws IOException {
        UserCard card = UserCard.EMPTY_CARD;
        try (BufferedReader reader = new BufferedReader(new FileReader(baseFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cardInfo = line.trim().split(":");
                int cardIdCurrent = Integer.parseInt(cardInfo[0]);
                if (cardIdCurrent == cardIdNeeded) {
                    String userName = cardInfo[1];
                    long funds = Long.parseLong(cardInfo[2]);
                    short pinCode = Short.parseShort(cardInfo[3]);
                    card = new UserCard(cardIdCurrent, userName, funds, pinCode);
                    break;
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        return card;
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
