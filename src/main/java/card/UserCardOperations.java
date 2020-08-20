package card;

//import sun.tools.jconsole.inspector.XOperations;

import card.operations.ResultOperation;
import utils.FileUtil;

import static runner.Runner.userCardBaseFile;

public class UserCardOperations {
    private final CardArray userCards;

    public UserCardOperations(CardArray userCards) {
        this.userCards = userCards;
    }


    public ResultOperation addFunds(long cardId, long amountOfMoneyToAdd) {
        UserCard card = userCards.getCardById(cardId);
        if (card.equals(UserCard.EMPTY_CARD)) {
            return ResultOperation.CARD_NOT_FOUND;
        } else {
            card.setFunds(card.getFunds() + amountOfMoneyToAdd);
            FileUtil.writeCardArrayToFile(userCardBaseFile, userCards);
            return ResultOperation.SUCCESS;
        }
    }

    public boolean giveOutFunds(long amountOfMoneyToGiveOut) {
//        if (userCards.getFunds() - amountOfMoneyToGiveOut < 0) {
//            System.out.println("У Вас не достаточно средств");
//            return false;
//        } else {
//            userCards.setFunds(userCards.getFunds() - amountOfMoneyToGiveOut);
//            return true;
//        }
        return true;
    }
//    }

    public ResultOperation getBalance(long cardId) {
        UserCard card = userCards.getCardById(cardId);
        if (card.equals(UserCard.EMPTY_CARD)) {
            return ResultOperation.CARD_NOT_FOUND;
        } else {
            System.out.println(card.getFunds());
            return ResultOperation.SUCCESS;
        }
    }

    public boolean transferFunds(UserCard anotherCard, long funds) {
//        if (giveOutFunds(funds)) {
//            UserCardOperations operations = new UserCardOperations(anotherCard);
//            operations.addFunds(funds);
//            return true;
//        } else return false;
        return true;
    }
}
