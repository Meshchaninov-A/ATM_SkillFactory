package card;

//import sun.tools.jconsole.inspector.XOperations;

public class UserCardOperations {
    private final UserCard userCard;

    public UserCardOperations(UserCard userCard) {
        this.userCard = userCard;
    }


    public boolean authenticate(short enteredPinCode) {
        return userCard.getPinCode() == enteredPinCode;
    }


    public void addFunds(long amountOfMoneyToAdd) {
        userCard.setFunds(userCard.getFunds() + amountOfMoneyToAdd);
    }

    public boolean giveOutFunds(long amountOfMoneyToGiveOut) {
        if (userCard.getFunds() - amountOfMoneyToGiveOut < 0) {
            System.out.println("У Вас не достаточно средств");
            return false;
        } else {
            userCard.setFunds(userCard.getFunds() - amountOfMoneyToGiveOut);
            return true;
        }
    }

    public boolean transferFunds(UserCard anotherCard, long funds) {
        if (giveOutFunds(funds)) {
            UserCardOperations operations = new UserCardOperations(anotherCard);
            operations.addFunds(funds);
            return true;
        } else return false;
    }
}
