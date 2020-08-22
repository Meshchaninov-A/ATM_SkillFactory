package card;

import card.operations.ResultOperation;
import files.ProjectFileWorker;

public class UserCardOperations {
    private static CardArray userCards = new CardArray();
    private static ProjectFileWorker baseWorker;

    public UserCardOperations(ProjectFileWorker baseWorker) {
        UserCardOperations.baseWorker = baseWorker;
        UserCardOperations.userCards = baseWorker.readCards();
    }

    public synchronized ResultOperation addFunds(long cardId, long amountOfMoneyToAdd) {
        UserCard card = userCards.getCardById(cardId);
        if (card.equals(UserCard.EMPTY_CARD)) {
            return ResultOperation.CARD_NOT_FOUND;
        } else {
            card.setFunds(card.getFunds() + amountOfMoneyToAdd);
            baseWorker.writeCards(userCards);
            return ResultOperation.SUCCESS;
        }
    }

    public synchronized ResultOperation withdrawFunds(long cardId, long amountOfMoneyToGiveOut) {
        UserCard card = userCards.getCardById(cardId);
        if (card.equals(UserCard.EMPTY_CARD)) {
            return ResultOperation.CARD_NOT_FOUND;
        } else if (card.getFunds() - amountOfMoneyToGiveOut < 0) {
            return ResultOperation.INSUFFICIENT_FUNDS;
        } else {
            card.setFunds(card.getFunds() - amountOfMoneyToGiveOut);
            baseWorker.writeCards(userCards);
            return ResultOperation.SUCCESS;
        }
    }

    public synchronized ResultOperation getBalance(long cardId) {
        UserCard card = userCards.getCardById(cardId);
        if (card.equals(UserCard.EMPTY_CARD)) {
            return ResultOperation.CARD_NOT_FOUND;
        } else {
            System.out.println(card.getFunds());
            return ResultOperation.SUCCESS;
        }
    }

    public synchronized ResultOperation transferFunds(long cardId, long anotherCardId, long fundsToTransfer) {
        UserCard card = userCards.getCardById(cardId);
        UserCard anotherCard = userCards.getCardById(anotherCardId);
        if (card.equals(UserCard.EMPTY_CARD) || anotherCard.equals(UserCard.EMPTY_CARD)) {
            return ResultOperation.CARD_NOT_FOUND;
        } else if (card.equals(anotherCard)) {
            return ResultOperation.NOT_SUPPORTED_OPERATION;
        } else if (card.getFunds() - fundsToTransfer < 0) {
            return ResultOperation.INSUFFICIENT_FUNDS;
        } else {
            card.setFunds(card.getFunds() - fundsToTransfer);
            anotherCard.setFunds(anotherCard.getFunds() + fundsToTransfer);
            baseWorker.writeCards(userCards);
            return ResultOperation.SUCCESS;
        }
    }

    public CardArray getUserCards() {
        return UserCardOperations.userCards;
    }
}
