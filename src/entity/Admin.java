package entity;

import logic.AdminLogic;

public class Admin extends Person {
    public static int AUTO_ID;

    public Admin() {
        if (AdminLogic.getInstance().loadAutoIdFromFile() != null) {
            AUTO_ID = AdminLogic.getInstance().loadAutoIdFromFile();
        }

        if (AUTO_ID < 1000) {
            AUTO_ID = 1000;
        }

        this.id = "AD" + AUTO_ID;
        AUTO_ID++;
    }
}