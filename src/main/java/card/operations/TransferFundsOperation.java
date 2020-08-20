package card.operations;

import card.ClientSession;
import card.UserCard;
import card.UserCardOperations;
import runner.ScannerWithValidation;

public class TransferFundsOperation implements Operation {
    @Override
    public int getOperationId() {
        return OperationsEnum.GET_CARD_INFO.getId();
    }

    @Override
    public ResultOperation doOperation(ClientSession session, UserCardOperations operations, ScannerWithValidation scannerUserInput) {
        long idOtherCard = scannerUserInput.getLongFromScanner("Введите id карты на которую вы хотите перевести средства:");
        //operations.transferFunds()sequals(UserCard.EMPTY_CARD))
        System.out.println("Введенной карты нет в ситеме");
        return ResultOperation.SUCCESS;
    }
}
