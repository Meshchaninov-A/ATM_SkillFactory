package card;

public class UserCardOperations {
    private final UserCard userCard;

    public UserCardOperations(UserCard userCard) {
        this.userCard = userCard;
    }


    public boolean authenticate(short enteredPinCode) {
        return userCard.getPinCode() == enteredPinCode;
    }


    public boolean addFunds(long amountOfMoneyToAdd) {
        return false;
    }


    public boolean giveOutFunds(long amountOfMoneyToGiveOut) {
        if (userCard.getFunds() - amountOfMoneyToGiveOut < 0) {
            return false;
        } else {
            userCard.setFunds(userCard.getFunds() - amountOfMoneyToGiveOut);
            return true;
        }
    }


    public boolean transferFunds(UserCard anotherCard, long funds) {
        return false;
    }

}
