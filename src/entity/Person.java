package entity;

import logic.DepartmentManagement;
import statics.DepartmentType;
import statics.Gender;
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
    protected Department department;
    protected Gender gender;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

        if (!DepartmentManagement.getInstance().getDepartmentList().isEmpty()) {
            if (account.getRole().equals(Role.ADMIN)) {
                this.department = DepartmentManagement.getInstance().checkExistDepartment(DepartmentType.INFORMATION_TECHNOLOGY);
            } else if (account.getRole().equals(Role.HRAGENT)) {
                this.department = DepartmentManagement.getInstance().checkExistDepartment(DepartmentType.HR);
            } else {
                System.out.println("Nhập phòng ban với lựa chọn sau:");
                System.out.println("1. Công nghệ thông tin");
                System.out.println("2. Kế toán");
                System.out.println("3. Khối sản xuất");
                System.out.println("4. Phòng kinh doanh");
                System.out.println("5. Phòng marketing");
                System.out.println("6. Phòng hậu cần");
                System.out.println("7. Phòng hành chính");
                System.out.println("8. Phòng nguồn nhân lực dự bị");

                int departmentChoice = ScannerUtility.inputValidNumberInRange(1, 8);

                switch (departmentChoice) {
                    case 1 ->
                            this.department = DepartmentManagement.getInstance().checkExistDepartment(DepartmentType.INFORMATION_TECHNOLOGY);
                    case 2 ->
                            this.department = DepartmentManagement.getInstance().checkExistDepartment(DepartmentType.ACCOUNTING);
                    case 3 ->
                            this.department = DepartmentManagement.getInstance().checkExistDepartment(DepartmentType.OPERATION);
                    case 4 ->
                            this.department = DepartmentManagement.getInstance().checkExistDepartment(DepartmentType.SALE);
                    case 5 ->
                            this.department = DepartmentManagement.getInstance().checkExistDepartment(DepartmentType.MARKETING);
                    case 6 ->
                            this.department = DepartmentManagement.getInstance().checkExistDepartment(DepartmentType.SUPPORT);
                    case 7 ->
                            this.department = DepartmentManagement.getInstance().checkExistDepartment(DepartmentType.HR);
                    case 8 ->
                            this.department = DepartmentManagement.getInstance().checkExistDepartment(DepartmentType.STAFF);
                }
            }
        } else {
            this.department = DepartmentManagement.getInstance().checkExistDepartment(DepartmentType.STAFF);
        }

        System.out.println("Chọn giới tính:");
        System.out.println("1. Nam");
        System.out.println("2. Nữ");
        System.out.println("3. Khác");

        int genderChoice = ScannerUtility.inputValidNumberInRange(1, 3);

        switch (genderChoice) {
            case 1 -> this.setGender(Gender.MALE);
            case 2 -> this.setGender(Gender.FEMALE);
            case 3 -> this.setGender(Gender.OTHER);
        }
    }

    @Override
    public void displayInfo() {
        System.out.printf("%-12s | %-21s | %-36s | %-36s | %-16s |\n", this.id, this.account.getUserName(), this.name, this.address, this.phoneNumber);
    }
}
