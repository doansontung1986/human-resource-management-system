package entity;

import logic.AdminLogic;
import logic.HRAgentLogic;

public class HRAgent extends Person {
    public static int AUTO_ID;

    public HRAgent() {
        if (HRAgentLogic.getInstance().loadAutoIdFromFile() != null) {
            AUTO_ID = HRAgentLogic.getInstance().loadAutoIdFromFile();
        }

        if (AUTO_ID < 1000) {
            AUTO_ID = 1000;
        }

        this.id = "HR" + AUTO_ID;
        AUTO_ID++;
    }
}
