package logic;

import entity.Account;
import entity.Department;
import entity.Person;
import statics.Role;
import utility.DataUtil;
import utility.FileUtility;
import utility.PrintMessageUtility;
import utility.ScannerUtility;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class MenuManagement {
    private AccountManagement accountManagement;
    private UserManagement userManagement;
    private AuthenticateManagement authenticateManagement;
    private DepartmentManagement departmentManagement;

    public void initializeData() {
        Object accountDataFromFile = FileUtility.getInstance().readDataFromFile(AccountManagement.ACCOUNT_DATA_FILE);
        List<Account> accountList = DataUtil.isNullOrEmpty(accountDataFromFile) ? new ArrayList<>() : (List<Account>) accountDataFromFile;
        this.accountManagement = AccountManagement.getInstance(accountList);

        Object departmentDataFromFile = FileUtility.getInstance().readDataFromFile(DepartmentManagement.DEPARTMENT_DATA_FILE);
        List<Department> departmentList = DataUtil.isNullOrEmpty(departmentDataFromFile) ? new ArrayList<>() : (List<Department>) departmentDataFromFile;
        this.departmentManagement = DepartmentManagement.getInstance(departmentList);

        Object userDataFromFile = FileUtility.getInstance().readDataFromFile(UserManagement.USER_DATA_FILE);
        List<Person> userList = DataUtil.isNullOrEmpty(userDataFromFile) ? new ArrayList<>() : (List<Person>) userDataFromFile;
        this.userManagement = UserManagement.getInstance(userList);

        this.authenticateManagement = AuthenticateManagement.getInstance(this.accountManagement);

    }

    public void run() {
        boolean isLoginExited = true;
        do {
            printLoginMenu();
            int choice = handleMenuChoice(1, 3);
            switch (choice) {
                case 1:
                    boolean backToLoginScreen = true;
                    Account account = authenticateManagement.loginToSystem(accountManagement.getAccountList());
                    Person user = null;
                    if (account != null) {
                        user = userManagement.getUserInfoByAccount(account);
                    }

                    if (user != null) {
                        boolean backToPreviousScreen;
                        Role role = account.getRole();
                        switch (role) {
                            case ADMIN:
                                do {
                                    AdminLogic.getInstance().printAdminMenuLevel1();
                                    choice = handleMenuChoice(1, 5);
                                    switch (choice) {
                                        case 1:
                                            do {
                                                backToPreviousScreen = true;
                                                AdminLogic.getInstance().printAdminMenuLevel2Sub1();
                                                choice = handleMenuChoice(1, 7);
                                                switch (choice) {
                                                    case 1:
                                                        AccountManagement.getInstance().resetPassword(accountManagement.getAccountList());
                                                        break;
                                                    case 2:
                                                        break;
                                                    case 3:
                                                        AccountManagement.getInstance().displayLockedAccounts(accountManagement.getAccountList());
                                                        break;
                                                    case 4:
                                                        AccountManagement.getInstance().unlockAccount(accountManagement.getAccountList());
                                                        break;
                                                    case 5:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 6:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 7:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 2:
                                            do {
                                                backToPreviousScreen = true;
                                                AdminLogic.getInstance().printAdminMenuLevel2Sub2();
                                                choice = handleMenuChoice(1, 10);
                                                switch (choice) {
                                                    case 1:
                                                        UserManagement.getInstance().addNewUser();
                                                        break;
                                                    case 2:
                                                        UserManagement.getInstance().updateUserInfo(userManagement.getUserList());
                                                        break;
                                                    case 3:
                                                        UserManagement.getInstance().viewUserDetails(userManagement.getUserList());
                                                        break;
                                                    case 4:
                                                        UserManagement.getInstance().displayUserList();
                                                        break;
                                                    case 5:
                                                        UserManagement.getInstance().displayUserByDepartment(userManagement.getUserList());
                                                        break;
                                                    case 6:
                                                        UserManagement.getInstance().displayUserByName(userManagement.getUserList());
                                                        break;
                                                    case 7:
                                                        UserManagement.getInstance().displayUserByPhone(userManagement.getUserList());
                                                        break;
                                                    case 8:
                                                        UserManagement.getInstance().displayUserByIdentityId(userManagement.getUserList());
                                                        break;
                                                    case 9:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 10:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 11:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 3:
                                            do {
                                                backToPreviousScreen = true;
                                                AdminLogic.getInstance().printAdminMenuLevel2Sub3();
                                                choice = handleMenuChoice(1, 10);
                                                switch (choice) {
                                                    case 1:
                                                        DepartmentManagement.getInstance().displayDepartmentList();
                                                        break;
                                                    case 2:
                                                        DepartmentManagement.getInstance().displayNumberOfUserEachDepartment();
                                                        break;
                                                    case 3:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 4:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 5:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 4:
                                            backToLoginScreen = false;
                                            break;
                                        case 5:
                                            backToLoginScreen = false;
                                            isLoginExited = false;
                                            break;
                                    }
                                } while (backToLoginScreen);
                                break;
                            case HRAGENT:
                                do {
                                    HRAgentLogic.getInstance().printHRAgentMenuLevel1();
                                    choice = handleMenuChoice(1, 7);
                                    switch (choice) {
                                        case 1:
                                            do {
                                                backToPreviousScreen = true;
                                                HRAgentLogic.getInstance().printHRAgentMenuLevel2Sub1();
                                                choice = handleMenuChoice(1, 8);
                                                switch (choice) {
                                                    case 1:
                                                        UserManagement.getInstance().viewUserDetails(userManagement.getUserList());
                                                        break;
                                                    case 2:
                                                        UserManagement.getInstance().displayUserList();
                                                        break;
                                                    case 3:
                                                        UserManagement.getInstance().displayUserByDepartment(userManagement.getUserList());
                                                        break;
                                                    case 4:
                                                        UserManagement.getInstance().displayUserByName(userManagement.getUserList());
                                                        break;
                                                    case 5:
                                                        UserManagement.getInstance().displayUserByPhone(userManagement.getUserList());
                                                        break;
                                                    case 6:
                                                        UserManagement.getInstance().displayUserByIdentityId(userManagement.getUserList());
                                                        break;
                                                    case 7:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 8:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 9:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 2:
                                            do {
                                                backToPreviousScreen = true;
                                                HRAgentLogic.getInstance().printHRAgentMenuLevel2Sub2();
                                                choice = handleMenuChoice(1, 5);
                                                switch (choice) {
                                                    case 1:
                                                        DepartmentManagement.getInstance().displayDepartmentList();
                                                        break;
                                                    case 2:
                                                        DepartmentManagement.getInstance().displayNumberOfUserEachDepartment();
                                                        break;
                                                    case 3:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 4:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 5:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 3:
                                            do {
                                                backToPreviousScreen = true;
                                                HRAgentLogic.getInstance().printHRAgentMenuLevel2Sub3();
                                                choice = handleMenuChoice(1, 6);
                                                switch (choice) {
                                                    case 1:
                                                        break;
                                                    case 2:
                                                        break;
                                                    case 3:
                                                        break;
                                                    case 4:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 5:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 6:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 4:
                                            do {
                                                backToPreviousScreen = true;
                                                HRAgentLogic.getInstance().printHRAgentMenuLevel2Sub4();
                                                choice = handleMenuChoice(1, 5);
                                                switch (choice) {
                                                    case 1:
                                                        break;
                                                    case 2:
                                                        break;
                                                    case 3:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 4:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 5:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 5:
                                            do {
                                                backToPreviousScreen = true;
                                                HRAgentLogic.getInstance().printHRAgentMenuLevel2Sub5();
                                                choice = handleMenuChoice(1, 5);
                                                switch (choice) {
                                                    case 1:
                                                        break;
                                                    case 2:
                                                        break;
                                                    case 3:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 4:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 5:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 6:
                                            backToLoginScreen = false;
                                            break;
                                        case 7:
                                            backToLoginScreen = false;
                                            isLoginExited = false;
                                            break;
                                    }
                                } while (backToLoginScreen);
                                break;
                            case STAFF:
                                do {
                                    StaffLogic.getInstance().printStaffMenuLevel1();
                                    choice = handleMenuChoice(1, 8);
                                    switch (choice) {
                                        case 1:
                                            do {
                                                backToPreviousScreen = true;
                                                StaffLogic.getInstance().printStaffMenuLevel2Sub1();
                                                choice = handleMenuChoice(1, 8);
                                                switch (choice) {
                                                    case 1:
                                                        break;
                                                    case 2:
                                                        break;
                                                    case 3:
                                                        break;
                                                    case 4:
                                                        break;
                                                    case 5:
                                                        break;
                                                    case 6:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 7:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 8:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 2:
                                            do {
                                                backToPreviousScreen = true;
                                                StaffLogic.getInstance().printStaffMenuLevel2Sub2();
                                                choice = handleMenuChoice(1, 5);
                                                switch (choice) {
                                                    case 1:
                                                        break;
                                                    case 2:
                                                        break;
                                                    case 3:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 4:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 5:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 3:
                                            do {
                                                backToPreviousScreen = true;
                                                StaffLogic.getInstance().printStaffMenuLevel2Sub3();
                                                choice = handleMenuChoice(1, 5);
                                                switch (choice) {
                                                    case 1:
                                                        break;
                                                    case 2:
                                                        break;
                                                    case 3:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 4:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 5:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 4:
                                            do {
                                                backToPreviousScreen = true;
                                                StaffLogic.getInstance().printStaffMenuLevel2Sub4();
                                                choice = handleMenuChoice(1, 5);
                                                switch (choice) {
                                                    case 1:
                                                        break;
                                                    case 2:
                                                        break;
                                                    case 3:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 4:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 5:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 5:
                                            do {
                                                backToPreviousScreen = true;
                                                StaffLogic.getInstance().printStaffMenuLevel2Sub5();
                                                choice = handleMenuChoice(1, 5);
                                                switch (choice) {
                                                    case 1:
                                                        break;
                                                    case 2:
                                                        break;
                                                    case 3:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 4:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 5:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 6:

                                            break;
                                        case 7:
                                            backToLoginScreen = false;
                                            break;
                                        case 8:
                                            backToLoginScreen = false;
                                            isLoginExited = false;
                                            break;
                                    }
                                } while (backToLoginScreen);
                                break;
                            default:
                                throw new IllegalStateException("Không có loại tài khoản " + role + " trong hệ thống");
                        }
                    }
                    break;
                case 2:
                    System.out.println(accountManagement.getAccountList().get(0).getUserName() + "-" + accountManagement.getAccountList().get(0).getPassword());
                    break;
                case 3:
                    isLoginExited = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }

        } while (isLoginExited);

        AccountManagement.getInstance().saveAccountListToFile();
        UserManagement.getInstance().saveUserListToFile();

        System.out.println("Hẹn gặp lại");
    }

    private int chooseFunction() {
        System.out.print("Xin mời lựa chọn chức năng (1-9): ");
        int functionChoice;
        do {
            try {
                functionChoice = ScannerUtility.inputValidInteger();
                if (functionChoice > 0 && functionChoice < 10) {
                    break;
                }
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForInteger();
            }

            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại");
        } while (true);

        return functionChoice;
    }

    private int chooseSubmenuChoice() {
        int choice;

        do {
            System.out.print("Xin mời lựa chọn chức năng (1 hoặc 2): ");

            try {
                choice = ScannerUtility.inputValidInteger();

                if (choice > 0 && choice < 3) {
                    break;
                }
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForInteger();
            }

            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại");
        } while (true);

        return choice;
    }

    private void printLoginMenu() {
        System.out.println("------ HỆ THỐNG QUẢN LÝ NGUỒN NHÂN LỰC ------");
        System.out.println("1. Đăng nhập vào hệ thống");
        System.out.println("2. Yêu cầu đổi mật khẩu");
        System.out.println("3. Thoát hệ thống");
    }

    private void printHRAgentMenu() {
        System.out.println("------ HỆ THỐNG QUẢN LÝ NGUỒN NHÂN LỰC ------");
        System.out.println("1. Quản lý tài khoản hệ thống");
        System.out.println("2. Quản lý thông tin cá nhân nhân viên");
        System.out.println("3. Quản lý thông tin các phòng ban");
        System.out.println("4. Quản lý thời gian làm việc");
        System.out.println("5. Quản lý nghỉ phép");
        System.out.println("6. Quản lý lương");
        System.out.println("7. Đăng xuất");
        System.out.println("8. Thoát hệ thống");
    }

    private void printStaffMenu() {
        System.out.println("------ HỆ THỐNG QUẢN LÝ NGUỒN NHÂN LỰC ------");
        System.out.println("1. Quản lý tài khoản hệ thống");
        System.out.println("2. Quản lý thông tin cá nhân nhân viên");
        System.out.println("3. Quản lý thông tin các phòng ban");
        System.out.println("4. Quản lý thời gian làm việc");
        System.out.println("5. Quản lý nghỉ phép");
        System.out.println("6. Quản lý lương");
        System.out.println("7. Đăng xuất");
        System.out.println("8. Thoát hệ thống");
    }

    private int handleMenuChoice(int begin, int end) {
        int choice;

        do {
            System.out.print("Xin mời lựa chọn chức năng: ");

            try {
                choice = ScannerUtility.inputValidNumberInRange(begin, end);
                break;
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForInteger();
            }

            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại");
        } while (true);

        return choice;
    }
}
