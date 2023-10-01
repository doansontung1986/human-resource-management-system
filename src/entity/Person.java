package entity;

import statics.Department;
import statics.Role;
import utility.ScannerUtility;
import utility.ValidateUserInput;

import java.io.Serializable;

public abstract class Person implements Inputable, Displayable, Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    protected String id;
    protected String name;
    protected String address;
    protected String phoneNumber;
    protected String citizenIdentifyId;
    protected Account account;
    protected Department department;

    public Person() {
        this.account = new Account();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCitizenIdentifyId() {
        return citizenIdentifyId;
    }

    public void setCitizenIdentifyId(String citizenIdentifyId) {
        this.citizenIdentifyId = citizenIdentifyId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public void inputInfo() {
        System.out.println("Nhập họ tên: ");
        this.setName(inputValidName());
        System.out.println("Nhập số căn cước công dân hoặc số chứng minh nhân dân: ");
        this.setCitizenIdentifyId(inputValidIdentityId());
        System.out.println("Nhập số điện thoại: ");
        this.setPhoneNumber(inputValidPhoneNumber());
        System.out.println("Nhập địa chỉ: ");
        this.setAddress(inputValidAddress());
        this.account.inputInfo();

        if (account.getRole().equals(Role.ADMIN)) {
            this.department = Department.INFORMATION_TECHNOLOGY;
        } else if (account.getRole().equals(Role.HRAGENT)) {
            this.department = Department.HR;
        } else {
            System.out.println("Nhập phòng ban với lựa chọn sau:");
            System.out.println("1. Công nghệ thông tin");
            System.out.println("2. Kế toán");
            System.out.println("3. Khối sản xuất");
            System.out.println("4. Phòng kinh doanh");
            System.out.println("5. Phòng marketing");
            System.out.println("6. Phòng hậu cần");
            System.out.println("7. Phòng hành chính");

            int departmentChoice = ScannerUtility.inputValidNumberInRange(1, 7);

            switch (departmentChoice) {
                case 1 -> this.department = Department.INFORMATION_TECHNOLOGY;
                case 2 -> this.department = Department.ACCOUNTING;
                case 3 -> this.department = Department.OPERATION;
                case 4 -> this.department = Department.SALE;
                case 5 -> this.department = Department.MARKETING;
                case 6 -> this.department = Department.SUPPORT;
                case 7 -> this.department = Department.HR;
                default ->
                        throw new IllegalStateException("Không có phòng ban " + departmentChoice + " trong hệ thống");
            }
        }
    }

    @Override
    public void displayInfo() {
        System.out.printf("%-12s | %-21s | %-36s | %-36s | %-16s |\n", this.id, this.account.getUserName(), this.name, this.address, this.phoneNumber);
    }

    private String inputValidName() {
        String name;
        do {
            name = ScannerUtility.inputStringInRange(6, 35);

            if (ValidateUserInput.checkValidName(name)) {
                break;
            }

            System.out.print("Tên nhân viên phải có độ dài 6 - 35 ký tự. Nhập lại tên nhân viên: ");
        } while (true);

        return name;
    }

    private String inputValidAddress() {
        String address;
        do {
            address = ScannerUtility.inputStringInRange(6, 35);

            if (ValidateUserInput.checkValidAddress(address)) {
                break;
            }

            System.out.print("Địa chỉ phải có độ dài 6 - 35 ký tự. Nhập lại địa chỉ: ");
        } while (true);

        return address;
    }

    private String inputValidPhoneNumber() {
        long phoneNumber;
        String phoneNumberStr;
        do {
            phoneNumber = ScannerUtility.inputValidLongNumber();
            phoneNumberStr = "0" + phoneNumber;

            if (ValidateUserInput.checkValidPhoneNumber(phoneNumberStr)) {
                break;
            }

            System.out.println("Số điện thoại phải có độ dài 10 hoặc 12 ký tự.");
            System.out.println("Bắt đầu bằng số 0 và có các số là 9, 8, 1, 7, 3, 5 phía sau. Ví dụ: 0912345678");
            System.out.println("Vui lòng nhập lại số điện thoại: ");
        } while (true);

        return phoneNumberStr;
    }

    public String inputValidIdentityId() {
        long identityId;
        do {
            identityId = ScannerUtility.inputValidLongNumber();

            if (ValidateUserInput.checkValidIdentityId(String.valueOf(identityId))) {
                break;
            }

            System.out.println("Căn cước công dân hoặc CMND phải có độ dài 9 hoặc 12 ký tự. Vui lòng nhập lại: ");
        } while (true);

        return String.valueOf(identityId);
    }
}
