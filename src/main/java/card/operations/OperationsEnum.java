package card.operations;

public enum OperationsEnum {
    ADD_FUNDS(1, "Добавить средства на счет"),
    GET_FUNDS(2, "Снять средства со счета"),
    GET_CARD_INFO(3, "Получить информацию о балансе"),
    TRANSFER_FUNDS(4, "Сделать перевод другому клиенту");

    private final int id;
    private final String description;


    OperationsEnum(int i, String s) {
        this.id = i;
        this.description = s;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
