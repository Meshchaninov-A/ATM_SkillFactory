package files;

import card.CardArray;
import card.UserCard;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectFileWorker {
    private final File config;

    public ProjectFileWorker(File config) {
        this.config = config;
    }

    /**
     * Получение всех банковских карточек UserCard из файла с базой
     * * @return экземпляр объекта CardArray. Пустая карточка, в случае отсутствия нужного id в базе
     */
    public CardArray readCards() {
        List<UserCard> cards = new ArrayList<>();
        List<String> lines;
        synchronized (config) {
            lines = FileUtil.getInstance().readFromFile(config);
        }
        for (String line : lines) {
            String[] cardInfo = line.trim().split(":");
            int cardIdCurrent = Integer.parseInt(cardInfo[0]);
            String userName = cardInfo[1];
            long funds = Long.parseLong(cardInfo[2]);
            short pinCode = Short.parseShort(cardInfo[3]);
            cards.add(new UserCard(cardIdCurrent, userName, funds, pinCode));
        }
        UserCard[] userCardsArray = new UserCard[cards.size()];
        return new CardArray(cards.toArray(userCardsArray));
    }

    /**
     * Метод по записи всех банковских карточек в виде CardArray в файл
     *
     * @param cards Экземпляр CardArray
     */
    public void writeCards(CardArray cards) {
        FileUtil.getInstance().writeToFile(config, cards.cardArrayToConfigString());
    }

    /**
     * Метод по валидации базы карт на корректность id карточек.
     *
     * @return true- если все id карт разные, false- если имеются две карточки с одним id
     */
    public boolean validateCardBase() {
        Set<Integer> idUserCards = new HashSet<>();
        List<String> lines;
        synchronized (config) {
            lines = FileUtil.getInstance().readFromFile(config);
        }
        for (String line : lines) {
            int cardId = Integer.parseInt(line.split(":")[0]);
            idUserCards.add(cardId);
        }
        if (lines.isEmpty()) {
            System.out.println("Не удалось прочесть файл с базой. Возможно он пуст");
            return false;
        } else if (idUserCards.size() != lines.size()) {
            System.out.println("Валидация базы карт неуспешна - в базе есть одинаковые ID");
            return false;
        } else return true;
    }
}
