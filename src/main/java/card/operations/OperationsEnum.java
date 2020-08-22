package card.operations;

import java.util.Arrays;

public enum OperationsEnum {
    ADD_FUNDS(1, "Добавить средства на счет"),
    WITHDRAW_FUNDS(2, "Снять средства со счета"),
    GET_CARD_INFO(3, "Получить информацию о балансе"),
    TRANSFER_FUNDS(4, "Сделать перевод другому клиенту"),
    EXIT_PROGRAM(5, "Закончить сессию"),
    UNKNOWN_OPERATION(-999, "Неизвестная операция");

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

    public static OperationsEnum getEnumById(int id) {
        return Arrays.stream(OperationsEnum.values())
                .filter(operationsEnum -> operationsEnum.getId() == id)
                .findFirst()
                .orElse(UNKNOWN_OPERATION);
    }
}
