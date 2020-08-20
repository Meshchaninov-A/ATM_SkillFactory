package card;

public class ClientSession {
    private UserCard userCard;
    boolean isUserAuthorized = false;

    public ClientSession(UserCard userCard) {
        this.userCard = userCard;
    }

    public boolean authenticate(short pinCode) {
        isUserAuthorized = (userCard.getPinCode() == pinCode);
        return isUserAuthorized;
    }

    public UserCard getCardInfo() {
        return userCard;
    }

    public void closeSession() {
        this.userCard = UserCard.EMPTY_CARD;
        isUserAuthorized = false;
    }

    public boolean isUserAuthorized() {
        return isUserAuthorized;
    }
}

