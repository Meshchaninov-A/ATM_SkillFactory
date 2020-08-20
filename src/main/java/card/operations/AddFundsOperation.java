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
        return ResultOperation.SUCCESS;
    }
}
