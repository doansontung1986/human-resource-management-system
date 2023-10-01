package logic;

import entity.Account;
import utility.ScannerUtility;

import java.util.List;

public class AuthenticateManagement {
    private AccountManagement accountManagement;

    public AuthenticateManagement(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    public Account loginToSystem(List<Account> accountList) {
        System.out.println("------ ĐĂNG NHẬP HỆ THỐNG ------");
        Account account = authenticateAccount(accountList);
        if (account != null) {
            System.out.println("Đăng nhập thành công.");
        } else {
            System.out.println("Tên tài khoản hoặc mật khẩu sai.");
        }
        return account;
    }

    private Account authenticateAccount(List<Account> accountList) {
        System.out.print("Nhập tên tài khoản: ");
        String username = ScannerUtility.inputValidString();
        System.out.print("Nhập mật khẩu: ");
        String password = ScannerUtility.inputValidString();
        if (accountManagement.checkAccountExist(username)) {
            for (Account account : accountList) {
                if (account.getUserName().equals(username) && account.getPassword().equals(password)) {
                    return account;
                }
            }
        }
        return null;
    }
}
