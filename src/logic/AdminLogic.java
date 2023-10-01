package logic;

import entity.Admin;
import utility.FileUtility;

public class AdminLogic {
    private static AdminLogic adminLogic;
    private static final String autoIdFileName = "AdminAutoId.txt";

    private AdminLogic() {
    }

    public static AdminLogic getInstance() {
        if (adminLogic == null) {
            adminLogic = new AdminLogic();
        }
        return adminLogic;
    }

    public static Integer loadAutoIdFromFile() {
        return FileUtility.loadIntegerDataFromFile(autoIdFileName);
    }

    public static void saveAutoIdToFile() {
        FileUtility.saveDataToFive(autoIdFileName, Admin.AUTO_ID);
    }
}
