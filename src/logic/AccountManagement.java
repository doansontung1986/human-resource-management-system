package logic;

import entity.Account;
import utility.FileUtility;
import utility.ScannerUtility;

import java.util.ArrayList;
import java.util.List;

public class AccountManagement {
    private static AccountManagement accountManagement;
    private List<Account> accountList;
    private List<Account> resetPasswordList;
    public static final String ACCOUNT_DATA_FILE = "account-list.dat";
    public static final String RESET_PASSWORD_ACCOUNT_DATA_FILE = "reset-password-account-list.dat";

    private AccountManagement() {

    }

    private AccountManagement(List<Account> accountList, List<Account> resetPasswordList) {
        this.accountList = new ArrayList<>(accountList);
        this.resetPasswordList = new ArrayList<>(resetPasswordList);
    }

    public static AccountManagement getInstance() {
        if (accountManagement == null) {
            accountManagement = new AccountManagement();
        }

        return accountManagement;
    }

    public static AccountManagement getInstance(List<Account> accountList, List<Account> resetPasswordList) {
        if (accountManagement == null) {
            accountManagement = new AccountManagement(accountList, resetPasswordList);
        }

        return accountManagement;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void displayAccountList() {
        System.out.printf("%-21s | %-16s | %-16s |\n", "Tên tài khoản", "Loại tài khoản", "Mật khẩu");
        for (Account account : accountList) {
            account.displayAccountInfo();
        }
    }

    public void saveAccount(Account account) {
        accountList.add(account);
        saveAccountListToFile();
    }

    public boolean checkAccountExist(String userName) {
        for (Account account : accountList) {
            if (account.getUserName().equals(userName)) {
                return true;
            }
        }

        return false;
    }

    public void saveAccountListToFile() {
        FileUtility.getInstance().writeDataToFile(accountList, ACCOUNT_DATA_FILE);
    }

    public void saveResetPasswordAccountListToFile() {
        FileUtility.getInstance().writeDataToFile(resetPasswordList, RESET_PASSWORD_ACCOUNT_DATA_FILE);
    }

    public void unlockAccount() {
        System.out.print("Nhập tên tài khoản: ");
        String username = ScannerUtility.inputValidString();

        Account account = checkExistAccount(username);

        if (account != null) {
            account.setLocked(false);
        } else {
            System.out.println("Tài khoản không tồn tại.");
        }
    }

    public void resetPassword() {
        System.out.print("Nhập tên tài khoản: ");
        String username = ScannerUtility.inputValidString();

        Account account = checkExistAccount(username);

        if (account != null) {
            String password;
            String repeatPassword;
            do {
                System.out.print("Nhập mật khẩu: ");
                password = ScannerUtility.inputValidString();

                System.out.print("Nhập mật khẩu xác nhận: ");
                repeatPassword = ScannerUtility.inputValidString();

                if (password.equals(repeatPassword)) {
                    break;
                }
                System.out.println("Mật khẩu xác nhân phải giống mật khẩu đã nhập");
            } while (true);

            account.setPassword(password);

        } else {
            System.out.println("Tài khoản không tồn tại.");
        }
    }

    public void changePassword(Account account) {
        String password;
        String repeatPassword;
        do {
            System.out.print("Nhập mật khẩu: ");
            password = ScannerUtility.inputValidString();

            System.out.print("Nhập mật khẩu xác nhận: ");
            repeatPassword = ScannerUtility.inputValidString();

            if (password.equals(repeatPassword)) {
                break;
            }
            System.out.println("Mật khẩu xác nhân phải giống mật khẩu đã nhập");
        } while (true);

        account.setPassword(password);
    }

    public void requestToResetPassword(Account account) {
        this.resetPasswordList.add(account);
        saveResetPasswordAccountListToFile();
    }

    public void displayResetPasswordList() {
        if (this.resetPasswordList.isEmpty()) {
            System.out.println("Danh sách tài khoản yêu cầu đổi mật khẩu rỗng");
            return;
        }
        for (Account account : this.resetPasswordList) {
            account.displayAccountInfo();
        }
    }

    public void displayLockedAccounts() {
        System.out.printf("%-21s | %-16s | %-16s |\n", "Tên tài khoản", "Chức năng", "Tình trạng");
        for (Account account : this.accountList) {
            if (account.isLocked()) {
                account.displayAccountInfo();
            }
        }
    }

    private Account checkExistAccount(String username) {
        for (Account account : this.accountList) {
            if (account.getUserName().equals(username)) {
                return account;
            }
        }

        return null;
    }
}
