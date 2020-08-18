package user;

/**
 * Класс для аутентефикации пользователя
 *
 * @author Meshchaninov A.A.
 */
public class Authentication {
    private final UserCard userCard;

    public Authentication(UserCard userCard) {
        this.userCard = userCard;
    }

    /**
     * Метод по проверке введенного пинкода пользователем
     *
     * @param enteredPinCode введеный в консоль пинкод
     * @return true если пинкод введен верно
     */
    public boolean authenticate(short enteredPinCode) {
        return userCard.getPinCode() == enteredPinCode;
    }
}
