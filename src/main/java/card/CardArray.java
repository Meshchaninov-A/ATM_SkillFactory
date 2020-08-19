package card;

import java.util.*;
import java.util.stream.Collectors;

public class CardArray {
    private HashSet<UserCard> cards;

    public CardArray(UserCard... cards) {
        this.cards = new HashSet<>(Arrays.asList(cards));
    }

    public UserCard getCardById(long cardId) {
        return cards.stream().filter(card -> card.getCardId() == cardId).findFirst().orElse(UserCard.EMPTY_CARD);
    }

    public String cardArrayToConfigString() {
        return cards.stream()
                .sorted(Comparator.comparingLong(UserCard::getCardId))
                .map(UserCard::toConfigString)
                .collect(Collectors.joining("\n"));
    }
}
