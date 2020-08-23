package card.operations;

import card.ClientSession;
import card.UserCardOperations;
import runner.ScannerWithValidation;

/**
 * Интерфейс клиентской операции
 */

public interface Operation {

    /**
     * Выполнить клиентскую операцию
     *
     * @param session          сессионные данные клиента
     * @param operations       объект для выполнения клиентских операций
     * @param scannerUserInput объект для считывания пользовательских данных
     * @return результат операции в виде ResultOperation
     */

    ResultOperation doOperation(ClientSession session, UserCardOperations operations, ScannerWithValidation scannerUserInput);
}
