package entity;

import logic.StaffLogic;

public class Staff extends Person {
    public static int AUTO_ID;

    public Staff() {
        if (StaffLogic.getInstance().loadAutoIdFromFile() != null) {
            AUTO_ID = StaffLogic.getInstance().loadAutoIdFromFile();
        }

        if (AUTO_ID < 1000) {
            AUTO_ID = 1000;
        }

        this.id = "S" + AUTO_ID;
        AUTO_ID++;
    }
}
