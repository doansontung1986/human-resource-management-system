package logic;

import entity.HRAgent;
import utility.FileUtility;

public class HRAgentLogic {
    private static HRAgentLogic hrAgentLogic;
    private static final String autoIdFileName = "HRAutoId.txt";

    private HRAgentLogic() {
    }

    public static HRAgentLogic getInstance() {
        if (hrAgentLogic == null) {
            hrAgentLogic = new HRAgentLogic();
        }
        return hrAgentLogic;
    }

    public static Integer loadAutoIdFromFile() {
        return FileUtility.loadIntegerDataFromFile(autoIdFileName);
    }

    public static void saveAutoIdToFile() {
        FileUtility.saveDataToFive(autoIdFileName, HRAgent.AUTO_ID);
    }
}
