package entity;

import logic.DepartmentManagement;
import logic.UserManagement;
import statics.DepartmentType;
import utility.ScannerUtility;

import java.io.Serializable;

public class Department implements Inputable, Displayable, Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    public static int AUTO_ID = 100;
    private int id;
    private DepartmentType departmentType;

    public Department() {
        int size = DepartmentManagement.getInstance().getDepartmentList().size();

        if (size == 0) {
            AUTO_ID = 100;
        } else {
            AUTO_ID = UserManagement.getInstance().getUserList().get(size - 1).getId() + 1;
        }

        this.id = AUTO_ID;
        AUTO_ID++;
    }

    public int getId() {
        return id;
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }

    @Override
    public void inputInfo() {
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
            case 1 -> this.departmentType = DepartmentType.INFORMATION_TECHNOLOGY;
            case 2 -> this.departmentType = DepartmentType.ACCOUNTING;
            case 3 -> this.departmentType = DepartmentType.OPERATION;
            case 4 -> this.departmentType = DepartmentType.SALE;
            case 5 -> this.departmentType = DepartmentType.MARKETING;
            case 6 -> this.departmentType = DepartmentType.SUPPORT;
            case 7 -> this.departmentType = DepartmentType.HR;
            case 8 -> this.departmentType = DepartmentType.STAFF;
            default -> throw new IllegalStateException("Không có phòng ban " + departmentChoice + " trong hệ thống");
        }
    }

    public void inputInfo(int departmentChoice) {
        switch (departmentChoice) {
            case 1 -> this.departmentType = DepartmentType.INFORMATION_TECHNOLOGY;
            case 2 -> this.departmentType = DepartmentType.ACCOUNTING;
            case 3 -> this.departmentType = DepartmentType.OPERATION;
            case 4 -> this.departmentType = DepartmentType.SALE;
            case 5 -> this.departmentType = DepartmentType.MARKETING;
            case 6 -> this.departmentType = DepartmentType.SUPPORT;
            case 7 -> this.departmentType = DepartmentType.HR;
            case 8 -> this.departmentType = DepartmentType.STAFF;
            default -> throw new IllegalStateException("Không có phòng ban " + departmentChoice + " trong hệ thống");
        }
    }

    @Override
    public void displayInfo() {
        System.out.printf("%-12s | %-36s |\n", this.id, this.departmentType.name);
    }
}
