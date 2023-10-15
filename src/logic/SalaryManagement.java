package logic;

import entity.Person;
import entity.Salary;
import utility.ScannerUtility;

import java.util.ArrayList;
import java.util.List;

public class SalaryManagement {
    private static SalaryManagement salaryManagement;
    public static final String SALARY_DATA_FILE = "salary-list.dat";
    private List<Salary> salaryList;

    public SalaryManagement() {
    }

    public SalaryManagement(List<Salary> salaryList) {
        this.salaryList = new ArrayList<>(salaryList);
    }

    public static SalaryManagement getInstance() {
        if (salaryManagement == null) {
            salaryManagement = new SalaryManagement();
        }
        return salaryManagement;
    }

    public static SalaryManagement getInstance(List<Salary> salaryList) {
        if (salaryManagement == null) {
            salaryManagement = new SalaryManagement(salaryList);
        }
        return salaryManagement;
    }

    public List<Salary> getSalaryList() {
        return salaryList;
    }

    public void setSalaryList(List<Salary> salaryList) {
        this.salaryList = new ArrayList<>(salaryList);
    }

    public void inputWorkingDays(Person person) {
        Salary salary = checkExistSalary(person);
        if (salary == null) {
            salary = new Salary();
        }
        salary.inputWorkingDays();
    }

    public void displayWorkingDays() {
        System.out.printf("%-16s | %-24s | %-12s |\n", "Mã nhân viên", "Tên nhân viên", "Số ngày công");
        for (Salary salary : this.salaryList) {
            salary.displayWorkingDays();
        }
    }

    public void filterByWorkingDays() {
        System.out.println("Nhập số ngày công cần lọc: ");
        int days = ScannerUtility.inputValidNumberInRange(1, 22);
        System.out.println("Danh sách các nhân viên có số ngày công ít hơn " + days + " ngày");
        System.out.printf("%-16s | %-24s | %-12s |\n", "Mã nhân viên", "Tên nhân viên", "Số ngày công");
        for (Salary salary : this.salaryList) {
            if (salary.getWorkingDays() <= days) {
                salary.displayWorkingDays();
            }
        }
    }

    public void displayWorkingDays(Person person) {
        System.out.printf("%-16s | %-24s | %-12s |\n", "Mã nhân viên", "Tên nhân viên", "Số ngày công");
        for (Salary salary : this.salaryList) {
            if (salary.getPerson().getId() == person.getId()) {
                salary.displayWorkingDays();
                break;
            }
        }
    }

    public void displaySalaryList() {
        System.out.printf("%-16s | %-24s | %-12s | %-12s | %-24s |\n", "Mã nhân viên", "Tên nhân viên", "Số ngày công", "Tổng số ngày phép", "Lương");
        for (Salary salary : this.salaryList) {
            salary.displayInfo();
        }
    }

    public void displaySalaryList(List<Salary> salaryList) {
        System.out.printf("%-16s | %-24s | %-12s | %-12s | %-24s |\n", "Mã nhân viên", "Tên nhân viên", "Số ngày công", "Tổng số ngày phép", "Lương");
        for (Salary salary : salaryList) {
            salary.displayInfo();
        }
    }


    public void displaySalaryList(Person person) {
        System.out.printf("%-16s | %-24s | %-12s | %-12s | %-24s |\n", "Mã nhân viên", "Tên nhân viên", "Số ngày công", "Tổng số ngày phép", "Lương");
        for (Salary salary : this.salaryList) {
            if (salary.getPerson().getId() == person.getId()) {
                salary.displayInfo();
                break;
            }
        }
    }

    private Salary checkExistSalary(Person person) {
        for (Salary salary : this.salaryList) {
            if (salary.getPerson().getId() == person.getId()) {
                return salary;
            }
        }
        return null;
    }
}
