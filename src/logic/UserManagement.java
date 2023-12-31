package logic;

import entity.*;
import statics.DepartmentType;
import statics.Role;
import utility.FileUtility;
import utility.ScannerUtility;

import java.util.ArrayList;
import java.util.List;

public class UserManagement implements Writable {
    private static UserManagement userManagement;
    private List<Person> userList;
    public static final String USER_DATA_FILE = "user-list.dat";

    private UserManagement() {

    }

    private UserManagement(List<Person> userList) {
        this.userList = new ArrayList<>(userList);
    }

    public static UserManagement getInstance() {
        if (userManagement == null) {
            userManagement = new UserManagement();
        }
        return userManagement;
    }

    public static UserManagement getInstance(List<Person> userList) {
        if (userManagement == null) {
            userManagement = new UserManagement(userList);
        }
        return userManagement;
    }

    public List<Person> getUserList() {
        return userList;
    }

    public Person addDefaultAdminUser() {
        Account account = new Account();
        account.setUserName("admin123");
        account.setPassword("111111");

        Person defaultAdmin = new Admin();
        defaultAdmin.setName("Default Administrator");
        defaultAdmin.setAccount(account);
        defaultAdmin.getAccount().setRole(Role.ADMIN);
        defaultAdmin.setDepartment(DepartmentManagement.getInstance().checkExistDepartment(DepartmentType.INFORMATION_TECHNOLOGY));
        defaultAdmin.setAddress("Không có");
        defaultAdmin.setPhoneNumber("Không có");
        defaultAdmin.setCitizenIdentifyId("");

        return defaultAdmin;
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

            while (checkExistIdentifyId(newUser)) {
                System.out.print("Nhập lại số căn ước công dân hoặc CMND: ");
                newUser.setCitizenIdentifyId(ScannerUtility.inputValidIdentityId());
            }

            while (AccountManagement.getInstance().checkExistAccount(newUser.getAccount().getUserName()) != null) {
                System.out.println("Nhập lại thông tin tài khoản và mật khẩu.");
                newUser.getAccount().inputInfo();
            }

            AccountManagement.getInstance().saveAccount(newUser.getAccount());
            saveUserToList(newUser);
        }
    }

    public void displayUserList() {
        if (this.userList.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return;
        }

        System.out.printf("%-12s | %-16s | %-36s | %-60s | %-16s | %-24s |\n", "Mã nhân viên", "Tên tài khoản", "Tên nhân viên", "Địa chỉ", "Số điện thoại", "Phòng ban");
        for (Person user : userList) {
            user.displayInfo();
        }
    }

    private void saveUserToList(Person user) {
        userList.add(user);
        writeData();
    }

    public boolean checkExistIdentifyId(Person person) {
        if (this.userList.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return false;
        }

        for (Person value : userList) {
            if (value.getCitizenIdentifyId().equals(person.getCitizenIdentifyId())) {
                System.out.println("Số căn cước công dân hoc CMND bị trùng");
                return true;
            }
        }
        return false;
    }

    public Person getUserInfoByAccount(Account account) {
        if (this.userList.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return null;
        }

        for (Person user : userList) {
            if (user.getAccount().getUserName().equals(account.getUserName())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void writeData() {
        FileUtility.getInstance().writeDataToFile(userList, USER_DATA_FILE);

    }

    private Person checkExistUser(List<Person> userList, int userId) {
        if (userList.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return null;
        }

        for (Person person : userList) {
            if (person.getId() == userId) {
                return person;
            }
        }

        return null;
    }

    public void updateUserInfo() {
        if (this.userList.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return;
        }

        System.out.println("Nhập mã nhân viên");
        int userId = ScannerUtility.inputValidInteger();
        Person user = checkExistUser(this.userList, userId);

        if (user != null) {
            user.setCitizenIdentifyId("");
            user.setPhoneNumber("");
            user.inputInfo();
        } else {
            System.out.println("User không tồn tại");
        }
    }

    public void updateUserInfo(Person user) {
        if (this.userList.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return;
        }

        user.inputInfo();
    }

    public void viewUserDetails() {
        if (this.userList.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return;
        }

        System.out.println("Nhập mã nhân viên");
        int personId = ScannerUtility.inputValidInteger();
        Person user = checkExistUser(this.userList, personId);

        if (user != null) {
            System.out.printf("%-12s | %-16s | %-36s | %-60s | %-16s | %-24s |\n", "Mã nhân viên", "Tên tài khoản", "Tên nhân viên", "Địa chỉ", "Số điện thoại", "Phòng ban");
            user.displayInfo();
        } else {
            System.out.println("User không tồn tại");
        }
    }

    public void viewUserDetails(Person user) {
        if (this.userList.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return;
        }

        System.out.printf("%-12s | %-16s | %-36s | %-60s | %-16s | %-24s |\n", "Mã nhân viên", "Tên tài khoản", "Tên nhân viên", "Địa chỉ", "Số điện thoại", "Phòng ban");
        user.displayInfo();
    }

    public void displayUserByDepartment() {
        if (this.userList.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return;
        }

        System.out.println("Nhập phòng ban với lựa chọn sau:");
        System.out.println("1. Công nghệ thông tin");
        System.out.println("2. Kế toán");
        System.out.println("3. Khối sản xuất");
        System.out.println("4. Phòng kinh doanh");
        System.out.println("5. Phòng marketing");
        System.out.println("6. Phòng hậu cần");
        System.out.println("7. Phòng hành chính");
        System.out.println("8. Phòng nguồn nhân lực dự bị");

        int departmentChoice = ScannerUtility.inputValidNumberInRange(1, 7);

        DepartmentType departmentType;

        switch (departmentChoice) {
            case 1 -> departmentType = DepartmentType.INFORMATION_TECHNOLOGY;
            case 2 -> departmentType = DepartmentType.ACCOUNTING;
            case 3 -> departmentType = DepartmentType.OPERATION;
            case 4 -> departmentType = DepartmentType.SALE;
            case 5 -> departmentType = DepartmentType.MARKETING;
            case 6 -> departmentType = DepartmentType.SUPPORT;
            case 7 -> departmentType = DepartmentType.HR;
            case 8 -> departmentType = DepartmentType.STAFF;
            default -> throw new IllegalStateException("Không có phòng ban " + departmentChoice + " trong hệ thống");
        }

        System.out.printf("%-12s | %-16s | %-36s | %-60s | %-16s | %-24s |\n", "Mã nhân viên", "Tên tài khoản", "Tên nhân viên", "Địa chỉ", "Số điện thoại", "Phòng ban");
        for (Person person : this.userList) {
            if (person.getDepartment().getDepartmentType().equals(departmentType)) {
                person.displayInfo();
            }
        }
    }

    public void displayUserByName() {
        if (this.userList.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return;
        }

        System.out.println("Nhập tên nhân viên");
        String name = ScannerUtility.inputValidString();

        System.out.printf("%-12s | %-16s | %-36s | %-60s | %-16s | %-24s |\n", "Mã nhân viên", "Tên tài khoản", "Tên nhân viên", "Địa chỉ", "Số điện thoại", "Phòng ban");
        for (Person person : this.userList) {
            if (person.getName().toLowerCase().contains(name.toLowerCase())) {
                person.displayInfo();
            }
        }
    }

    public void displayUserByPhone() {
        if (this.userList.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return;
        }

        System.out.println("Nhập số điện thoại nhân viên cần tìm:");
        String phone = ScannerUtility.inputValidPhoneNumber();

        System.out.printf("%-12s | %-16s | %-36s | %-60s | %-16s | %-24s |\n", "Mã nhân viên", "Tên tài khoản", "Tên nhân viên", "Địa chỉ", "Số điện thoại", "Phòng ban");
        for (Person person : this.userList) {
            if (person.getPhoneNumber().equals(phone)) {
                person.displayInfo();
            }
        }
    }

    public void displayUserByIdentityId() {
        if (this.userList.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return;
        }

        System.out.println("Nhập số chứng minh nhân dân:");
        String identityId = ScannerUtility.inputValidIdentityId();

        System.out.printf("%-12s | %-16s | %-36s | %-60s | %-16s | %-24s |\n", "Mã nhân viên", "Tên tài khoản", "Tên nhân viên", "Địa chỉ", "Số điện thoại", "Phòng ban");
        for (Person person : this.userList) {
            if (person.getCitizenIdentifyId().equals(identityId)) {
                person.displayInfo();
            }
        }
    }

    public boolean checkExistPhoneNumber(String phoneNumber) {
        for (Person person : userList) {
            if (person.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkExistCitizenId(String citizenId) {
        for (Person person : userList) {
            if (person.getCitizenIdentifyId().equals(citizenId)) {
                return true;
            }
        }
        return false;
    }
}
