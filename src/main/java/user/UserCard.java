package user;

/**
 * Класс, представляющий банковскую карточку пользователя
 *
 * @author Meshchaninov A.A
 */
public class UserCard {
    private final long userId;
    private final String userName;
    private long funds;
    private final short pinCode;

    public UserCard(long userId, String userName, long funds, short pinCode) {
        this.userId = userId;
        this.userName = userName;
        this.funds = funds;
        this.pinCode = pinCode;
    }

    /**
     * Получение пинкода пользователя
     *
     * @return пинкод в виде short
     */
    short getPinCode() {
        return pinCode;
    }

    /**
     * Получить id пользователя
     *
     * @return user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Получить имя пользователя
     *
     * @return имя пользователя в виде строки
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Получить средства на карточке пользователя
     *
     * @return сумма денег на карте пользователя
     */
    public long getFunds() {
        return funds;
    }


    /**
     * Добавление средств на счет пользователя
     *
     * @param funds добавляемая сумма средств
     */
    public void addFunds(long funds) {
        this.funds += funds;
    }

}
