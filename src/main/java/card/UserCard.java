package card;

import java.util.Objects;

/**
 * Класс, представляющий банковскую карточку пользователя
 *
 * @author Meshchaninov A.A
 */
public class UserCard {
    private final long cardId;
    private final String userName;
    private long funds;
    private final short pinCode;
    public final static UserCard EMPTY_CARD = new UserCard();

    /**
     * Конструктор пустой карты пользователя
     */
    private UserCard() {
        this.cardId = 0;
        this.funds = 0;
        this.userName = "";
        this.pinCode = 0;
    }

    /**
     * Основной конструктор объекта UserCard
     *
     * @param cardId   id карты
     * @param userName имя владельца карты
     * @param funds    количество денежных средств на карте
     * @param pinCode  пинкод от карты
     */
    public UserCard(long cardId, String userName, long funds, short pinCode) {
        this.cardId = cardId;
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
    public long getCardId() {
        return cardId;
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
    public void setFunds(long funds) {
        this.funds = funds;
    }

    /**
     * Получить строковое предстваление объекта UserCard
     *
     * @return строковое предстваление объекта UserCard
     */

    public String toConfigString() {
        return String.format("%s:%s:%s:%s", cardId, userName, funds, pinCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCard card = (UserCard) o;
        return cardId == card.cardId &&
                userName.equals(card.userName);
    }


    @Override
    public int hashCode() {
        return Objects.hash(cardId, userName);
    }
}
