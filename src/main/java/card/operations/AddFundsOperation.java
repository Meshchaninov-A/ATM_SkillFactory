package card.operations;

import card.ClientSession;
import card.UserCardOperations;
import runner.ScannerWithValidation;

public class AddFundsOperation implements Operation {

    @Override
    public int getOperationId() {
        return OperationsEnum.ADD_FUNDS.getId();
    }

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
