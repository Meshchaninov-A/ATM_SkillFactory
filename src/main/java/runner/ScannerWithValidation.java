package runner;

import java.util.Scanner;

/**
 * Класс, отвечающий за считываение пользовательских данных с консоли
 */

public class ScannerWithValidation {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Считать пользовательские данные с типом long с консоли
     *
     * @param message сообщение для отображения клиенту
     * @return значение введенное пользователем
     */

    public long getLongFromScanner(String message) throws NumberFormatException {
        System.out.println(message);
        return getLongFromScanner();
    }

    /**
     * Считать пользовательские данные с типом long с консоли
     *
     * @return значение введенное пользователем
     */

    public long getLongFromScanner() throws NumberFormatException {
        if (SCANNER.hasNextLong()) {
            return SCANNER.nextLong();
        } else {
            String actualValue = SCANNER.next();
            throw new NumberFormatException(actualValue + " is not numeric value or it's not in [" + Long.MIN_VALUE + "," + Long.MAX_VALUE + "]");
        }
    }

    /**
     * Считать пользовательские данные с типом int с консоли
     *
     * @return значение введенное пользователем
     */

    public int getIntFromScanner() throws NumberFormatException {
        if (SCANNER.hasNextInt()) {
            return SCANNER.nextInt();
        } else {
            String actualValue = SCANNER.next();
            throw new NumberFormatException(actualValue + " is not numeric value or it's not in [" + Integer.MIN_VALUE + "," + Integer.MAX_VALUE + "]");
        }
    }

    /**
     * Считать пользовательские данные с типом short с консоли
     *
     * @param message сообщение для отображения клиенту
     * @return значение введенное пользователем
     */

    public short getShortFromScanner(String message) throws NumberFormatException {
        System.out.println(message);
        if (SCANNER.hasNextShort()) {
            return SCANNER.nextShort();
        } else {
            String actualValue = SCANNER.next();
            throw new NumberFormatException(actualValue + " is not numeric value or it's not in [" + Short.MIN_VALUE + "," + Short.MAX_VALUE + "]");
        }
    }

    /**
     * Считать пользовательские данные с типом String с консоли
     *
     * @param message сообщение для отображения клиенту
     * @return значение введенное пользователем
     */

    public String getStringFromScanner(String message) {
        System.out.println(message);
        return SCANNER.nextLine();
    }
}
