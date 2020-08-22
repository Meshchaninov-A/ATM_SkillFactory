package card.operations;

import card.ClientSession;
import card.UserCard;
import card.UserCardOperations;
import runner.ScannerWithValidation;

/**
 * Класс, реализующий операцию перечисления средств
 */

public class TransferFundsOperation implements Operation {

    /**
     * Операция перечисления средств
     *
     * @param session          сессионные данные клиента
     * @param operations       объект для выполнения клиентских операций
     * @param scannerUserInput объект для считывания пользовательских данных
     * @return результат операции в виде ResultOperation
     */

    @Override
    public ResultOperation doOperation(ClientSession session, UserCardOperations operations, ScannerWithValidation scannerUserInput) {
        if (session.isUserAuthorized()) {
            long idOtherCard = scannerUserInput.getLongFromScanner("Введите id карты на которую вы хотите перевести средства:");
            long fundsToTransfer = scannerUserInput.getLongFromScanner("Введите сумму для перевода:");
            return operations.transferFunds(session.getCardInfo().getCardId(), idOtherCard, fundsToTransfer);
        } else {
            return ResultOperation.PERMISSION_DENIED;
        }
    }
}
