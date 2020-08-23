package card.operations;

import card.ClientSession;
import card.UserCardOperations;
import runner.ScannerWithValidation;

/**
 * Класс, реализующий операцию снятия средств
 */

public class WithdrawFundsOperation implements Operation {

    /**
     * Операция снятия средств
     *
     * @param session          сессионные данные клиента
     * @param operations       объект для выполнения клиентских операций
     * @param scannerUserInput объект для считывания пользовательских данных
     * @return результат операции в виде ResultOperation
     */

    @Override
    public ResultOperation doOperation(ClientSession session, UserCardOperations operations, ScannerWithValidation scannerUserInput) {
        if (session.isUserAuthorized()) {
            long fundsToGiveOut = scannerUserInput.getLongFromScanner("Введите количество снимаемых с карты средств:");
            return operations.withdrawFunds(session.getCardInfo().getCardId(), fundsToGiveOut);
        }
        return ResultOperation.PERMISSION_DENIED;
    }
}
