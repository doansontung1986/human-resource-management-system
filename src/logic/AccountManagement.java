package logic;

import entity.Account;
import utility.FileUtility;

import java.util.ArrayList;
import java.util.List;

public class AccountManagement {
    private List<Account> accountList;
    private final String accountListFileName = "AccountList";

    public AccountManagement() {
        this.accountList = loadAccountListFromFile();
        if (this.accountList == null) {
            this.accountList = new ArrayList<>();
        }
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

    public List loadAccountListFromFile() {
        return FileUtility.loadDataFromFile(accountListFileName);
    }

    private void saveAccountListToFile() {
        FileUtility.saveDataToFive(accountListFileName, accountList);
    }
}
