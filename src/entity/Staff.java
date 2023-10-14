package entity;

import logic.AccountManagement;

public class Staff extends Person {
    public static int AUTO_ID;

    public Staff() {
        AUTO_ID = AccountManagement.getInstance().getAccountList().size();

        if (AUTO_ID < 1000) {
            AUTO_ID = 1000;
        }

        this.id = AUTO_ID;
        AUTO_ID++;
    }
}
