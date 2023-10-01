package logic;

import entity.Staff;
import utility.FileUtility;

public class StaffLogic {
    private static StaffLogic staffLogic;
    private static final String autoIdFileName = "StaffAutoId.txt";

    private StaffLogic() {
    }

    public static StaffLogic getInstance() {
        if (staffLogic == null) {
            staffLogic = new StaffLogic();
        }
        return staffLogic;
    }

    public static Integer loadAutoIdFromFile() {
        return FileUtility.loadIntegerDataFromFile(autoIdFileName);
    }

    public static void saveAutoIdToFile() {
        FileUtility.saveDataToFive(autoIdFileName, Staff.AUTO_ID);
    }
}
