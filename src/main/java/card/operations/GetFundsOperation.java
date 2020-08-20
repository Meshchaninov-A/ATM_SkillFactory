package card.operations;

import card.ClientSession;
import card.UserCardOperations;
import runner.ScannerWithValidation;

public class GetFundsOperation implements Operation {
    @Override
    public int getOperationId() {
        return OperationsEnum.GET_FUNDS.getId();
    }

    @Override
    public ResultOperation doOperation(ClientSession session, UserCardOperations operations, ScannerWithValidation scannerUserInput) {
        return ResultOperation.SUCCESS;
    }
}
