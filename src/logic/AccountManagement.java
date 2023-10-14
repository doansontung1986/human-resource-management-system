package logic;

import entity.Account;
import utility.FileUtility;
import utility.ScannerUtility;

import java.util.ArrayList;
import java.util.List;

public class AccountManagement {
    private static AccountManagement accountManagement;
    private List<Account> accountList;
    public static final String ACCOUNT_DATA_FILE = "account-list.dat";

    private AccountManagement() {

    }

    private AccountManagement(List<Account> accountList) {
        this.accountList = new ArrayList<>(accountList);
    }

    public static AccountManagement getInstance() {
        if (accountManagement == null) {
            accountManagement = new AccountManagement();
        }

        return accountManagement;
    }

    public static AccountManagement getInstance(List<Account> accountList) {
        if (accountManagement == null) {
            accountManagement = new AccountManagement(accountList);
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

    public void unlockAccount(List<Account> accountList) {
        System.out.print("Nhập tên tài khoản: ");
        String username = ScannerUtility.inputValidString();

        Account account = checkExistAccount(accountList, username);

        if (account != null) {
            account.setLocked(false);
        } else {
            System.out.println("Tài khoản không tồn tại.");
        }
    }

    public void resetPassword(List<Account> accountList) {
        System.out.print("Nhập tên tài khoản: ");
        String username = ScannerUtility.inputValidString();

        Account account = checkExistAccount(accountList, username);

        if (account != null) {
            System.out.print("Nhập mật khẩu: ");
            String password;
            String repeatPassword;
            do {
                password = ScannerUtility.inputValidString();
                repeatPassword = ScannerUtility.inputValidString();

                if (password.equals(repeatPassword)) {
                    break;
                }
                System.out.println("mật khẩu xác nhân phải giống mật khẩu đã nhập");
            } while (true);

            account.setPassword(password);

        } else {
            System.out.println("Tài khoản không tồn tại.");
        }
    }

    public void displayLockedAccounts(List<Account> accountList) {
        for (Account account : accountList) {
            if (account.isLocked()) {
                account.displayAccountInfo();
            }
        }
    }

    private Account checkExistAccount(List<Account> accountList, String username) {
        for (Account account : accountList) {
            if (account.getUserName().equals(username)) {
                return account;
            }
        }

        return null;
    }
}
