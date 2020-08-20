package card.operations;

public enum ResultOperation {
    SUCCESS("Операция успешна"),
    PERMISSION_DENIED("Доступ запрещен"),
    INSUFFICIENT_FUNDS("На счету недостаточно средств"),
    CARD_NOT_FOUND("Карта получателя не найдена");

    private final String operationResult;

    ResultOperation(String s) {
        this.operationResult = s;
    }

    public String getOperationResult() {
        return operationResult;
    }
}
