package runner;

import java.util.Scanner;

public class ScannerWithValidation {
    private static final Scanner SCANNER = new Scanner(System.in);

    public long getLongFromScanner(String message) throws NumberFormatException {
        System.out.println(message);
        return getLongFromScanner();
    }

    public long getLongFromScanner() throws NumberFormatException {
        if (SCANNER.hasNextLong()) {
            return SCANNER.nextLong();
        } else {
            String actualValue = SCANNER.next();
            throw new NumberFormatException(actualValue + "is not numeric value or it's not in [" + Long.MIN_VALUE + "," + Long.MAX_VALUE + "]");
        }
    }

    public int getIntFromScanner() throws NumberFormatException {
        if (SCANNER.hasNextInt()) {
            return SCANNER.nextInt();
        } else {
            String actualValue = SCANNER.next();
            throw new NumberFormatException(actualValue + "is not numeric value or it's not in [" + Integer.MIN_VALUE + "," + Integer.MAX_VALUE + "]");
        }
    }

    public int getIntFromScanner(String message) throws NumberFormatException {
        System.out.println(message);
        return getIntFromScanner();
    }

    public short getShortFromScanner(String message) throws NumberFormatException {
        System.out.println(message);
        if (SCANNER.hasNextShort()) {
            return SCANNER.nextShort();
        } else {
            String actualValue = SCANNER.next();
            throw new NumberFormatException(actualValue + "is not numeric value or it's not in [" + Short.MIN_VALUE + "," + Short.MAX_VALUE + "]");
        }
    }

    public String getStringFromScanner(String message) {
        System.out.println(message);
        return SCANNER.nextLine();
    }
}
