package card;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс - обертка над коллекцией пользовательских карт системы
 */

public class CardArray {
    private final Set<UserCard> cards;

    public CardArray(UserCard... cards) {
        this.cards = Collections.synchronizedSet(new HashSet<>(Arrays.asList(cards)));
    }

    /**
     * Получить карту по ее id
     *
     * @param cardId id карты
     * @return объект карты клиента
     */

    public UserCard getCardById(long cardId) {
        return cards.stream().filter(card -> card.getCardId() == cardId).findFirst().orElse(UserCard.EMPTY_CARD);
    }

    /**
     * Преобразовать коллекцию карт системы в строку
     *
     * @return строковое представление коллекции карт клиентов
     */

    public String cardArrayToConfigString() {
        return cards.stream()
                .sorted(Comparator.comparingLong(UserCard::getCardId))
                .map(UserCard::toConfigString)
                .collect(Collectors.joining("\n"));
    }
}
