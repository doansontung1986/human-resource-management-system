package logic;

import entity.Person;
import entity.Salary;
import entity.Writable;
import utility.FileUtility;

import java.util.ArrayList;
import java.util.List;

public class SalaryManagement implements Writable {
    private static SalaryManagement salaryManagement;
    public static final String SALARY_DATA_FILE = "salary-list.dat";
    private List<Salary> salaryList;

    private SalaryManagement() {
    }

    private SalaryManagement(List<Salary> salaryList) {
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

    public Salary inputNewSalaryEntry(Person person) {
        Salary salary = checkExistSalary(person);
        if (salary == null) {
            salary = new Salary(person);
            saveSalary(salary);
        }
        return salary;
    }

    public void calculateSalary(Person person) {
        Salary salary = inputNewSalaryEntry(person);
        salary.calculateSalary();
    }

    public void saveSalary(Salary salary) {
        this.salaryList.add(salary);
        writeData();
    }

    @Override
    public void writeData() {
        FileUtility.getInstance().writeDataToFile(this.salaryList, SALARY_DATA_FILE);
    }

    public void displaySalaryList() {
        if (this.salaryList.isEmpty()) {
            System.out.println("Danh sách lương rỗng");
            return;
        }

        System.out.printf("%-16s | %-24s | %-12s |\n", "Mã nhân viên", "Tên nhân viên", "Lương");
        for (Salary salary : this.salaryList) {
            salary.displayInfo();
        }
    }

    public void displaySalaryList(List<Salary> salaryList) {
        if (this.salaryList.isEmpty()) {
            System.out.println("Danh sách lương rỗng");
            return;
        }

        System.out.printf("%-16s | %-24s | %-12s |\n", "Mã nhân viên", "Tên nhân viên", "Lương");
        for (Salary salary : salaryList) {
            salary.displayInfo();
        }
    }


    public void displaySalaryList(Person person) {
        if (this.salaryList.isEmpty()) {
            System.out.println("Danh sách lương rỗng");
            return;
        }

        System.out.printf("%-16s | %-24s | %-12s |\n", "Mã nhân viên", "Tên nhân viên", "Lương");
        for (Salary salary : this.salaryList) {
            if (salary.getPerson().getId() == person.getId()) {
                salary.displayInfo();
                break;
            }
        }
    }

    private Salary checkExistSalary(Person person) {
        if (this.salaryList.isEmpty()) {
            return null;
        }

        for (Salary salary : this.salaryList) {
            if (salary.getPerson().getId() == person.getId()) {
                return salary;
            }
        }
        return null;
    }
}
