package logic;

import entity.*;
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
    private TimeOffManagement timeOffManagement;
    private WorkDayManagement workDayManagement;
    private SalaryManagement salaryManagement;

    public void initializeData() {
        Object departmentDataFromFile = FileUtility.getInstance().readDataFromFile(DepartmentManagement.DEPARTMENT_DATA_FILE);
        List<Department> departmentList = DataUtil.isNullOrEmpty(departmentDataFromFile) ? new ArrayList<>() : (List<Department>) departmentDataFromFile;
        this.departmentManagement = DepartmentManagement.getInstance(departmentList);

        Object accountDataFromFile = FileUtility.getInstance().readDataFromFile(AccountManagement.ACCOUNT_DATA_FILE);
        List<Account> accountList = DataUtil.isNullOrEmpty(accountDataFromFile) ? new ArrayList<>() : (List<Account>) accountDataFromFile;

        Object resetPasswordAccountDataFromFile = FileUtility.getInstance().readDataFromFile(AccountManagement.RESET_PASSWORD_ACCOUNT_DATA_FILE);
        List<Account> resetPasswordAccountList = DataUtil.isNullOrEmpty(resetPasswordAccountDataFromFile) ? new ArrayList<>() : (List<Account>) resetPasswordAccountDataFromFile;
        this.accountManagement = AccountManagement.getInstance(accountList, resetPasswordAccountList);

        Object userDataFromFile = FileUtility.getInstance().readDataFromFile(UserManagement.USER_DATA_FILE);
        List<Person> userList = DataUtil.isNullOrEmpty(userDataFromFile) ? new ArrayList<>() : (List<Person>) userDataFromFile;
        this.userManagement = UserManagement.getInstance(userList);
        this.authenticateManagement = AuthenticateManagement.getInstance(this.accountManagement);

        Object timeOffDataFromFile = FileUtility.getInstance().readDataFromFile(TimeOffManagement.TIME_OFF_DATA_FILE);
        List<TimeOff> timeOffList = DataUtil.isNullOrEmpty(timeOffDataFromFile) ? new ArrayList<>() : (List<TimeOff>) timeOffDataFromFile;

        Object timeDetailDataFromFile = FileUtility.getInstance().readDataFromFile(TimeOffManagement.TOTAL_TIME_OFF_DETAIL_DATA_FILE);
        List<TimeOffDetail> timeOffDetailList = DataUtil.isNullOrEmpty(timeDetailDataFromFile) ? new ArrayList<>() : (List<TimeOffDetail>) timeDetailDataFromFile;
        this.timeOffManagement = TimeOffManagement.getInstance(timeOffList, timeOffDetailList);

        Object workDayDataFromFile = FileUtility.getInstance().readDataFromFile(WorkDayManagement.WORKDAY_DATA_FILE);
        List<WorkDay> workDayList = DataUtil.isNullOrEmpty(workDayDataFromFile) ? new ArrayList<>() : (List<WorkDay>) workDayDataFromFile;
        this.workDayManagement = WorkDayManagement.getInstance(workDayList);

        Object salaryDataFromFile = FileUtility.getInstance().readDataFromFile(SalaryManagement.SALARY_DATA_FILE);
        List<Salary> salaryList = DataUtil.isNullOrEmpty(salaryDataFromFile) ? new ArrayList<>() : (List<Salary>) salaryDataFromFile;
        this.salaryManagement = SalaryManagement.getInstance(salaryList);

        this.departmentManagement.addDefaultDepartments();

        if (AccountManagement.getInstance().getAccountList().isEmpty()) {
            Person user = UserManagement.getInstance().addDefaultAdminUser();
            UserManagement.getInstance().getUserList().add(user);
            AccountManagement.getInstance().saveAccount(user.getAccount());
            UserManagement.getInstance().writeData();
        }
    }

    public void run() {
        boolean isLoginExited = true;
        do {
            printLoginMenu();
            int choice = handleMenuChoice(1, 2);
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
                                                        AccountManagement.getInstance().resetPassword();
                                                        break;
                                                    case 2:
                                                        AccountManagement.getInstance().displayResetPasswordList();
                                                        break;
                                                    case 3:
                                                        AccountManagement.getInstance().displayLockedAccounts();
                                                        break;
                                                    case 4:
                                                        AccountManagement.getInstance().unlockAccount();
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
                                                choice = handleMenuChoice(1, 11);
                                                switch (choice) {
                                                    case 1:
                                                        UserManagement.getInstance().addNewUser();
                                                        break;
                                                    case 2:
                                                        UserManagement.getInstance().updateUserInfo();
                                                        break;
                                                    case 3:
                                                        UserManagement.getInstance().viewUserDetails();
                                                        break;
                                                    case 4:
                                                        UserManagement.getInstance().displayUserList();
                                                        break;
                                                    case 5:
                                                        UserManagement.getInstance().displayUserByDepartment();
                                                        break;
                                                    case 6:
                                                        UserManagement.getInstance().displayUserByName();
                                                        break;
                                                    case 7:
                                                        UserManagement.getInstance().displayUserByPhone();
                                                        break;
                                                    case 8:
                                                        UserManagement.getInstance().displayUserByIdentityId();
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
                                                choice = handleMenuChoice(1, 6);
                                                switch (choice) {
                                                    case 1:
                                                        DepartmentManagement.getInstance().inputDepartmentList();
                                                        break;
                                                    case 2:
                                                        DepartmentManagement.getInstance().displayDepartmentList();
                                                        break;
                                                    case 3:
                                                        DepartmentManagement.getInstance().displayNumberOfUserEachDepartment();
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
                                    choice = handleMenuChoice(1, 8);
                                    switch (choice) {
                                        case 1:
                                            do {
                                                backToPreviousScreen = true;
                                                HRAgentLogic.getInstance().printHRAgentMenuLevel2Sub1();
                                                choice = handleMenuChoice(1, 9);
                                                switch (choice) {
                                                    case 1:
                                                        UserManagement.getInstance().viewUserDetails();
                                                        break;
                                                    case 2:
                                                        UserManagement.getInstance().displayUserList();
                                                        break;
                                                    case 3:
                                                        UserManagement.getInstance().displayUserByDepartment();
                                                        break;
                                                    case 4:
                                                        UserManagement.getInstance().displayUserByName();
                                                        break;
                                                    case 5:
                                                        UserManagement.getInstance().displayUserByPhone();
                                                        break;
                                                    case 6:
                                                        UserManagement.getInstance().displayUserByIdentityId();
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
                                                        WorkDayManagement.getInstance().displayWorkingDays();
                                                        break;
                                                    case 2:
                                                        WorkDayManagement.getInstance().filterByWorkingDays();
                                                        break;
                                                    case 3:
                                                        List<WorkDay> workDayList = WorkDayManagement.getInstance().sortByWorkingDaysInDecrease();
                                                        WorkDayManagement.getInstance().displayWorkingDays(workDayList);
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
                                                        TimeOffManagement.getInstance().displayTimeOffList();
                                                        break;
                                                    case 2:
                                                        TimeOffManagement.getInstance().approveLeave();
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
                                                        SalaryManagement.getInstance().displaySalaryList();
                                                        break;
                                                    case 2:
                                                        List<Salary> salaryList = new ArrayList<>(SalaryManagement.getInstance().getSalaryList());
                                                        salaryList.sort(((o1, o2) -> (int) (o2.getSalary() - o1.getSalary())));
                                                        SalaryManagement.getInstance().displaySalaryList(salaryList);
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
                                            AccountManagement.getInstance().requestToResetPassword(account);
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
                            case STAFF:
                                do {
                                    StaffLogic.getInstance().printStaffMenuLevel1();
                                    choice = handleMenuChoice(1, 8);
                                    switch (choice) {
                                        case 1:
                                            do {
                                                backToPreviousScreen = true;
                                                StaffLogic.getInstance().printStaffMenuLevel2Sub1();
                                                choice = handleMenuChoice(1, 4);
                                                switch (choice) {
                                                    case 1:
                                                        AccountManagement.getInstance().changePassword(account);
                                                        break;
                                                    case 2:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 3:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 4:
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
                                                        UserManagement.getInstance().updateUserInfo(user);
                                                        break;
                                                    case 2:
                                                        UserManagement.getInstance().viewUserDetails(user);
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
                                                        TimeOffManagement.getInstance().submitLeaveDay(user);
                                                        break;
                                                    case 2:
                                                        TimeOffManagement.getInstance().displayTimeOffList(user);
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
                                                        WorkDayManagement.getInstance().inputWorkingDays(user);
                                                        SalaryManagement.getInstance().calculateSalary(user);
                                                        break;
                                                    case 2:
                                                        WorkDayManagement.getInstance().displayWorkingDays(user);
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
                                                choice = handleMenuChoice(1, 4);
                                                switch (choice) {
                                                    case 1:
                                                        SalaryManagement.getInstance().displaySalaryList(user);
                                                        break;
                                                    case 2:
                                                        backToPreviousScreen = false;
                                                        break;
                                                    case 3:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        break;
                                                    case 4:
                                                        backToPreviousScreen = false;
                                                        backToLoginScreen = false;
                                                        isLoginExited = false;
                                                        break;
                                                }
                                            } while (backToPreviousScreen);
                                            break;
                                        case 6:
                                            AccountManagement.getInstance().requestToResetPassword(account);
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
                    isLoginExited = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }

        } while (isLoginExited);

        AccountManagement.getInstance().writeData();
        UserManagement.getInstance().writeData();
        DepartmentManagement.getInstance().writeData();
        TimeOffManagement.getInstance().writeData();
        WorkDayManagement.getInstance().writeData();
        SalaryManagement.getInstance().writeData();

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
        System.out.println("2. Thoát hệ thống");
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
