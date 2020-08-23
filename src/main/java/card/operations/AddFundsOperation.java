package card.operations;

import card.ClientSession;
import card.UserCardOperations;
import runner.ScannerWithValidation;

/**
 * Класс, реализующий операцию добавления средств на счет клиента
 */

public class AddFundsOperation implements Operation {

    /**
     * Операция зачисления средств на карту
     *
     * @param session          сессионные данные клиента
     * @param operations       объект для выполнения клиентских операций
     * @param scannerUserInput объект для считывания пользовательских данных
     * @return результат операции в виде ResultOperation
     */

    @Override
    public ResultOperation doOperation(ClientSession session, UserCardOperations operations, ScannerWithValidation scannerUserInput) {
        if (session.isUserAuthorized()) {
            long fundsToAdd = scannerUserInput.getLongFromScanner("Введите количество пополняемых на карту средств:");
            return operations.addFunds(session.getCardInfo().getCardId(), fundsToAdd);
        } else {
            return ResultOperation.PERMISSION_DENIED;
        }
    }
}
