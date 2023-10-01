package utility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtility {
    public static int inputValidInteger() {
        int number;
        do {
            try {
                number = new Scanner(System.in).nextInt();
                break;
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForInteger();
            }
        } while (true);
        return number;
    }

    public static long inputValidLongNumber() {
        long number;
        do {
            try {
                number = new Scanner(System.in).nextLong();
                break;
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForInteger();
            }
        } while (true);
        return number;
    }

    public static String inputValidString() {
        String str;
        do {
            str = new Scanner(System.in).nextLine();
            if (!str.equals("")) {
                break;
            }
            PrintMessageUtility.printErrorMessageForString();
        } while (true);
        return str;
    }

    public static String inputStringInRange(int begin, int end) {
        String str;
        do {
            str = inputValidString();
            if (str.length() >= begin && str.length() <= end) {
                break;
            }
            System.out.printf("Độ dài chuỗi nhập vào phải trong khoảng [%d - %d]. Vui lòng nhập lại.\n", begin, end);
        } while (true);
        return str;
    }

    public static int inputValidNumberInRange(int begin, int end) {
        int number;
        do {
            number = inputValidInteger();
            if (number >= begin && number <= end) {
                break;
            }
            System.out.printf("Giá trị nhập vào phải trong khoảng [%d - %d]. Vui lòng nhập lại.\n", begin, end);
        } while (true);
        return number;
    }

    public static int inputValidNumberInRange(int begin) {
        int number;
        do {
            number = inputValidInteger();
            if (number > begin) {
                break;
            }
            System.out.printf("Giá trị nhập vào phải lớn hơn %d. Vui lòng nhập lại.\n", begin);
        } while (true);
        return number;
    }
}
