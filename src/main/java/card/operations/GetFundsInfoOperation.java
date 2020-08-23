package card.operations;

import card.ClientSession;
import card.UserCardOperations;
import runner.ScannerWithValidation;

/**
 * Класс, реализующий операцию запроса баланса клиента
 */

public class GetFundsInfoOperation implements Operation {

    /**
     * Операция запроса баланса
     *
     * @param session          сессионные данные клиента
     * @param operations       объект для выполнения клиентских операций
     * @param scannerUserInput объект для считывания пользовательских данных
     * @return результат операции в виде ResultOperation
     */

    @Override
    public ResultOperation doOperation(ClientSession session, UserCardOperations operations, ScannerWithValidation scannerUserInput) {
        if (session.isUserAuthorized()) {
            return operations.getBalance(session.getCardInfo().getCardId());
        } else {
            return ResultOperation.PERMISSION_DENIED;
        }
    }
}
