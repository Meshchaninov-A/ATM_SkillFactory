package card.operations;

import card.ClientSession;
import card.UserCardOperations;
import runner.ScannerWithValidation;

public class GetFundsInfoOperation implements Operation {

    @Override
    public ResultOperation doOperation(ClientSession session, UserCardOperations operations, ScannerWithValidation scannerUserInput) {
        if (session.isUserAuthorized()) {
            return operations.getBalance(session.getCardInfo().getCardId());
        } else {
            return ResultOperation.PERMISSION_DENIED;
        }
    }
}
