package logic;

import entity.Department;
import entity.Person;
import statics.DepartmentType;
import utility.FileUtility;
import utility.ScannerUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DepartmentManagement {
    private static DepartmentManagement departmentManagement;
    private List<Department> departmentList;

    public static final String DEPARTMENT_DATA_FILE = "department-list.dat";

    public DepartmentManagement() {

    }

    public DepartmentManagement(List<Department> departmentList) {
        this.departmentList = new ArrayList<>(departmentList);
    }

    public static DepartmentManagement getInstance() {
        if (departmentManagement == null) {
            departmentManagement = new DepartmentManagement();
        }

        return departmentManagement;
    }

    public static DepartmentManagement getInstance(List<Department> departmentList) {
        if (departmentManagement == null) {
            departmentManagement = new DepartmentManagement(departmentList);
        }

        return departmentManagement;
    }

    public void setDepartmentList(Set<Department> departmentList) {
        this.departmentList = new ArrayList<>(departmentList);
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void saveDepartment(Department department) {
        this.departmentList.add(department);
        saveDepartmentListToFile();
    }

    public void saveDepartmentListToFile() {
        FileUtility.getInstance().writeDataToFile(this.departmentList, DEPARTMENT_DATA_FILE);
    }

    public void inputDepartmentList() {
        System.out.println("Nhập số lượng phòng ban cần thêm");
        int numberOfDepartment = ScannerUtility.inputValidInteger();
        for (int i = 0; i < numberOfDepartment; i++) {
            System.out.println("Thông tin phòng ban thứ " + (i + 1));
            Department department = new Department();
            department.inputInfo();

            if (checkExistDepartment(department) != null) {
                System.out.println("Phòng ban đã tồn tại");
                continue;
            }

            saveDepartment(department);
        }
    }

    public void displayDepartmentList() {
        if (this.departmentList.isEmpty()) {
            System.out.println("Danh sách phòng ban rỗng");
        }

        System.out.printf("%-12s | %-36s |\n", "Mã phòng ban", "Phòng ban");
        for (Department department : this.departmentList) {
            department.displayInfo();
        }
    }

    public void displayNumberOfUserEachDepartment() {
        List<Person> userList = new ArrayList<>(UserManagement.getInstance().getUserList());

        if (userList.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return;
        }

        int count;
        System.out.println("Số lượng nhân viên các phòng ban");
        System.out.println("-------------------------------");
        for (Department department : this.departmentList) {
            count = 0;
            for (Person person : userList) {
                if (department.getDepartmentType().equals(person.getDepartment())) {
                    count++;
                }
            }
            System.out.println(department.getDepartmentType() + ": " + count + " nhân viên");
            System.out.println("-------------------------------");
        }
    }

    public Department checkExistDepartment(Department newDepartment) {
        for (Department department : this.departmentList) {
            if (department.getDepartmentType().equals(newDepartment.getDepartmentType())) {
                return department;
            }
        }

        return null;
    }

    public Department checkExistDepartment(DepartmentType departmentType) {
        for (Department department : this.departmentList) {
            if (department.getDepartmentType().equals(departmentType)) {
                return department;
            }
        }

        return null;
    }

    public void addDefaultDepartments() {
        if (getDepartmentList().isEmpty()) {
            Department ITDepartment = new Department();
            ITDepartment.inputInfo(1);

            Department HRDepartment = new Department();
            HRDepartment.inputInfo(7);

            Department staffDepartment = new Department();
            staffDepartment.inputInfo(8);

            saveDepartment(ITDepartment);
            saveDepartment(HRDepartment);
            saveDepartment(staffDepartment);

            saveDepartmentListToFile();
        }
    }
}
