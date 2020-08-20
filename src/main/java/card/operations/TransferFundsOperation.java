package card.operations;

import card.ClientSession;
import card.UserCardOperations;
import runner.ScannerWithValidation;

public class TransferFundsOperation implements Operation {
    @Override
    public int getOperationId() {
        return OperationsEnum.GET_CARD_INFO.getId();
    }

    @Override
    public ResultOperation doOperation(ClientSession session, UserCardOperations operations, ScannerWithValidation scannerUserInput) {
        return ResultOperation.SUCCESS;
    }
}
