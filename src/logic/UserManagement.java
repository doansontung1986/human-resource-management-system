package logic;

import entity.*;
import statics.Role;
import utility.FileUtility;
import utility.ScannerUtility;

import java.util.ArrayList;
import java.util.List;

public class UserManagement {
    private List<Person> userList;
    private AccountManagement accountManagement;
    private final String userListFileName = "UserList";

    public UserManagement(AccountManagement accountManagement) {
        userList = loadUserListFromFile();
        if (this.userList == null) {
            this.userList = new ArrayList<>();
        }
        this.accountManagement = accountManagement;

    }

    public List<Person> getUserList() {
        return userList;
    }

    public void addNewUser() {
        System.out.print("Nhập số tài khoản mới cần tạo: ");
        int numberOfAccount = ScannerUtility.inputValidNumberInRange(0);
        for (int i = 0; i < numberOfAccount; i++) {
            System.out.println("Thông tin nhân viên thứ " + (i + 1));

            Person newUser;

            System.out.println("Nhập loại tài khoản với lựa chọn sau:");
            System.out.println("1. Nhân viên quản trị");
            System.out.println("2. Nhân viên nhân sự");
            System.out.println("3. Nhân viên");

            int role = ScannerUtility.inputValidNumberInRange(1, 3);

            switch (role) {
                case 1 -> {
                    newUser = new Admin();
                    newUser.getAccount().setRole(Role.ADMIN);
                }
                case 2 -> {
                    newUser = new HRAgent();
                    newUser.getAccount().setRole(Role.HRAGENT);
                }
                case 3 -> {
                    newUser = new Staff();
                    newUser.getAccount().setRole(Role.STAFF);
                }
                default -> throw new IllegalStateException("Không có loại tài khoản " + role + " trong hệ thống");
            }

            try {
                newUser.inputInfo();
            } catch (NullPointerException e) {
                System.out.println("Lỗi tạo nhân viên mới");
            }

            do {
                if (!checkExistIdentifyId(newUser)) {
                    break;
                }
                System.out.print("Nhập lại số căn ước công dân hoặc CMND: ");
                newUser.setCitizenIdentifyId(newUser.inputValidIdentityId());
            } while (true);

            do {
                if (!accountManagement.checkAccountExist(newUser.getAccount().getUserName())) {
                    break;
                }
                System.out.println("Nhập lại thông tin tài khoản và mật khẩu.");
                newUser.getAccount().inputInfo();
            } while (true);

            accountManagement.saveAccount(newUser.getAccount());
            saveUserToList(newUser);
        }
    }

    public void displayUserList() {
        System.out.printf("%-12s | %-21s | %-36s | %-36s | %-16s |\n", "Mã nhân viên", "Tên tài khoản", "Tên nhân viên", "Địa chỉ", "Số điện thoại");
        for (Person user : userList) {
            user.displayInfo();
        }
    }

    private void saveUserToList(Person user) {
        userList.add(user);
    }

    public boolean checkExistIdentifyId(Person person) {
        for (Person value : userList) {
            if (value.getCitizenIdentifyId().equals(person.getCitizenIdentifyId())) {
                System.out.println("Số căn cước công dân hoc CMND bị trùng");
                return true;
            }
        }
        return false;
    }

    public Person getUserInfoByAccount(Account account) {
        for (Person user : userList) {
            if (user.getAccount().getUserName().equals(account.getUserName())) {
                return user;
            }
        }
        return null;
    }

    public List loadUserListFromFile() {
        return FileUtility.loadDataFromFile(userListFileName);
    }

    public void saveAccountListToFile() {
        FileUtility.saveDataToFive(userListFileName, userList);
    }
}
