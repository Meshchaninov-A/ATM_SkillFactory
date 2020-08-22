package card;

/**
 * Сессия пользователя по работе с банкоматом
 */
public class ClientSession {
    private UserCard userCard;
    boolean isUserAuthorized = false;

    public ClientSession(UserCard userCard) {
        this.userCard = userCard;
    }

    /**
     * Аутентефикация пользователя
     *
     * @param pinCode пинкод, введенный пользователем
     * @return true при успехе
     */
    public boolean authenticate(short pinCode) {
        isUserAuthorized = (userCard.getPinCode() == pinCode);
        return isUserAuthorized;
    }

    /**
     * Получить информацию о банковской карте
     *
     * @return
     */
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

