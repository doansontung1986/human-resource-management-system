package entity;

import statics.DepartmentType;
import statics.Role;
import utility.ScannerUtility;

import java.io.Serializable;

public abstract class Person implements Inputable, Displayable, Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    protected int id;
    protected String name;
    protected String address;
    protected String phoneNumber;
    protected String citizenIdentifyId;
    protected Account account;
    protected DepartmentType departmentType;

    public Person() {
        this.account = new Account();
    }

    public int getId() {
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

    public DepartmentType getDepartment() {
        return departmentType;
    }

    public void setDepartment(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }

    @Override
    public void inputInfo() {
        System.out.println("Nhập họ tên: ");
        this.setName(ScannerUtility.inputValidName());
        System.out.println("Nhập số căn cước công dân hoặc số chứng minh nhân dân: ");
        this.setCitizenIdentifyId(ScannerUtility.inputValidIdentityId());
        System.out.println("Nhập số điện thoại: ");
        this.setPhoneNumber(ScannerUtility.inputValidPhoneNumber());
        System.out.println("Nhập địa chỉ: ");
        this.setAddress(ScannerUtility.inputValidAddress());

        this.account.inputInfo();

        if (account.getRole().equals(Role.ADMIN)) {
            this.departmentType = DepartmentType.INFORMATION_TECHNOLOGY;
        } else if (account.getRole().equals(Role.HRAGENT)) {
            this.departmentType = DepartmentType.HR;
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
                case 1 -> this.departmentType = DepartmentType.INFORMATION_TECHNOLOGY;
                case 2 -> this.departmentType = DepartmentType.ACCOUNTING;
                case 3 -> this.departmentType = DepartmentType.OPERATION;
                case 4 -> this.departmentType = DepartmentType.SALE;
                case 5 -> this.departmentType = DepartmentType.MARKETING;
                case 6 -> this.departmentType = DepartmentType.SUPPORT;
                case 7 -> this.departmentType = DepartmentType.HR;
                default ->
                        throw new IllegalStateException("Không có phòng ban " + departmentChoice + " trong hệ thống");
            }
        }
    }

    @Override
    public void displayInfo() {
        System.out.printf("%-12s | %-21s | %-36s | %-36s | %-16s |\n", this.id, this.account.getUserName(), this.name, this.address, this.phoneNumber);
    }
}
