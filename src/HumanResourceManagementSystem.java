import logic.MenuManagement;

public class HumanResourceManagementSystem {

    public static void main(String[] args) {
        MenuManagement menuManagement = new MenuManagement();
        menuManagement.initializeData();
        menuManagement.run();
    }
}
