package card;

import java.util.Arrays;
import java.util.List;

public class CardArray {
    private List<UserCard> cards;

   public CardArray(UserCard... cards) {
        this.cards = Arrays.asList(cards);
    }

    public UserCard getCardById(long cardId) {
        return cards.stream().filter(card -> card.getCardId() == cardId).findFirst().orElse(UserCard.EMPTY_CARD);
    }


}
