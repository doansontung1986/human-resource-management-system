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

    public static String inputValidName() {
        String name;
        do {
            name = ScannerUtility.inputStringInRange(6, 35);

            if (ValidateUserInput.checkValidName(name)) {
                break;
            }

            System.out.print("Tên nhân viên phải có độ dài 6 - 35 ký tự. Nhập lại tên nhân viên: ");
        } while (true);

        return name;
    }

    public static String inputValidAddress() {
        String address;
        do {
            address = ScannerUtility.inputStringInRange(6, 35);

            if (ValidateUserInput.checkValidAddress(address)) {
                break;
            }

            System.out.print("Địa chỉ phải có độ dài 6 - 35 ký tự. Nhập lại địa chỉ: ");
        } while (true);

        return address;
    }

    public static String inputValidPhoneNumber() {
        long phoneNumber;
        String phoneNumberStr;
        do {
            phoneNumber = ScannerUtility.inputValidLongNumber();
            phoneNumberStr = "0" + phoneNumber;

            if (ValidateUserInput.checkValidPhoneNumber(phoneNumberStr)) {
                break;
            }

            System.out.println("Số điện thoại phải có độ dài 10 hoặc 12 ký tự.");
            System.out.println("Bắt đầu bằng số 0 và có các số là 9, 8, 1, 7, 3, 5 phía sau. Ví dụ: 0912345678");
            System.out.println("Vui lòng nhập lại số điện thoại: ");
        } while (true);

        return phoneNumberStr;
    }

    public static String inputValidIdentityId() {
        long identityId;
        do {
            identityId = ScannerUtility.inputValidLongNumber();

            if (ValidateUserInput.checkValidIdentityId(String.valueOf(identityId))) {
                break;
            }

            System.out.println("Căn cước công dân hoặc CMND phải có độ dài 9 hoặc 12 ký tự. Vui lòng nhập lại: ");
        } while (true);

        return String.valueOf(identityId);
    }
}
