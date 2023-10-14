package logic;

import entity.Account;
import utility.ScannerUtility;

import java.util.List;

public class AuthenticateManagement {
    private static AuthenticateManagement authenticateManagement;
    private AccountManagement accountManagement;

    private AuthenticateManagement(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    public static AuthenticateManagement getInstance(AccountManagement accountManagement) {
        if (authenticateManagement == null) {
            authenticateManagement = new AuthenticateManagement(accountManagement);
        }

        return authenticateManagement;
    }

    public Account loginToSystem(List<Account> accountList) {
        System.out.println("------ ĐĂNG NHẬP HỆ THỐNG ------");
        Account account = authenticateAccount(accountList);

        if (account != null) {
            if (account.isLocked()) {
                System.out.println("Tài koản " + account.getUserName() + " đã bị khóa");
            } else {
                System.out.println("Đăng nhập thành công.");
                return account;
            }
        }

        System.out.println("Đăng nhập thất bại");

        return null;
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
                } else if (account.getUserName().equals(username) && !account.getPassword().equals(password)) {
                    account.setFailedLoginAttempt(account.getFailedLoginAttempt() + 1);
                    System.out.println("Đăng nhập sai lần " + account.getFailedLoginAttempt());
                    if (account.getFailedLoginAttempt() == 5) {
                        account.setLocked(true);
                        System.out.println("Tài khoản " + account.getUserName() + " đã bị khóa vì đăng nhập sai 5 lần");
                        account.setFailedLoginAttempt(0);
                        return null;
                    }
                }
            }
        }
        return null;
    }


}
