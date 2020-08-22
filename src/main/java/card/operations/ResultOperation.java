package card.operations;

/**
 * enum, описывающий результат клиентской операции
 */

public enum ResultOperation {
    SUCCESS("Операция успешна"),
    PERMISSION_DENIED("Доступ запрещен"),
    INSUFFICIENT_FUNDS("На счету недостаточно средств"),
    CARD_NOT_FOUND("Введенной карты нет в системе"),
    NOT_SUPPORTED_OPERATION("Операция не поддерживается");

    private final String operationResult;

    ResultOperation(String s) {
        this.operationResult = s;
    }

    /**
     * Получить строковое представление результата операции
     *
     * @return строковое представление результата операции
     */

    public String getOperationResult() {
        return operationResult;
    }
}
