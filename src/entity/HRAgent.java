package entity;

import logic.AccountManagement;

public class HRAgent extends Person {
    public static int AUTO_ID;

    public HRAgent() {
        AUTO_ID = AccountManagement.getInstance().getAccountList().size();

        if (AUTO_ID < 1000) {
            AUTO_ID = 1000;
        }

        this.id = AUTO_ID;
        AUTO_ID++;
    }
}
