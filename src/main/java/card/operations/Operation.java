package card.operations;

import card.ClientSession;
import card.UserCardOperations;
import runner.ScannerWithValidation;

public interface Operation {
    ResultOperation doOperation(ClientSession session, UserCardOperations operations, ScannerWithValidation scannerUserInput);
}
