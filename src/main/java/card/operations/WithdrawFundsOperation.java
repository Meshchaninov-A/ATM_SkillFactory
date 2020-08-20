package card.operations;

import card.ClientSession;
import card.UserCardOperations;
import runner.ScannerWithValidation;

public class WithdrawFundsOperation implements Operation {
    @Override
    public int getOperationId() {
        return OperationsEnum.WITHDRAW_FUNDS.getId();
    }

    @Override
    public ResultOperation doOperation(ClientSession session, UserCardOperations operations, ScannerWithValidation scannerUserInput) {
        if (session.isUserAuthorized()) {
            long fundsToGiveOut = scannerUserInput.getLongFromScanner("Введите количество снимаемых с карты средств");
            return operations.withdrawFunds(session.getCardInfo().getCardId(), fundsToGiveOut);
        }
        return ResultOperation.PERMISSION_DENIED;
    }
}
