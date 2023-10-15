package entity;

import logic.TimeOffManagement;
import statics.Gender;
import utility.ScannerUtility;

import java.util.List;

public class Salary implements Displayable {
    private Person person;
    private static final double SALARY_RATIO_IT = 5.5;
    private static final double SALARY_RATIO_ACCOUNTING = 3.0;
    private static final double SALARY_RATIO_OPERATION = 2.5;
    private static final double SALARY_RATIO_SALE = 4.0;
    private static final double SALARY_RATIO_MARKETING = 3.5;
    private static final double SALARY_RATIO_SUPPORT = 2.0;
    private static final double SALARY_RATIO_HR = 2.5;
    private static final double SALARY_RATIO_STAFF = 1.0;
    private static final int MAX_WORKING_DAYS = 22;
    private int workingDays;
    private double salary;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void inputWorkingDays() {
        System.out.print("Nhập số ngày công: ");
        int workingDays;
        do {
            workingDays = ScannerUtility.inputValidNumberInRange(1, 22);
            if (workingDays <= MAX_WORKING_DAYS - this.workingDays) {
                break;
            }
            System.out.println("Bạn không thể nhập thêm số ngày công quá số ngày còn lại: " + (MAX_WORKING_DAYS - this.workingDays));
            System.out.print("bạn có muốn tiếp tục không ? (Y/N)");
            String continueToInput = ScannerUtility.inputValidString();
            if (continueToInput.equalsIgnoreCase("N")) {
                workingDays = 0;
                break;
            }
        } while (true);

        this.workingDays += workingDays;
    }

    public void calculateSalary() {
        int totalLeaveDays = sumOfLeaveDays();
        double salaryRatio = 0;

        switch (person.getDepartment().getDepartmentType()) {
            case INFORMATION_TECHNOLOGY -> salaryRatio = SALARY_RATIO_IT;
            case ACCOUNTING -> salaryRatio = SALARY_RATIO_ACCOUNTING;
            case OPERATION -> salaryRatio = SALARY_RATIO_OPERATION;
            case SALE -> salaryRatio = SALARY_RATIO_SALE;
            case MARKETING -> salaryRatio = SALARY_RATIO_MARKETING;
            case SUPPORT -> salaryRatio = SALARY_RATIO_SUPPORT;
            case HR -> salaryRatio = SALARY_RATIO_HR;
            case STAFF -> salaryRatio = SALARY_RATIO_STAFF;
        }

        this.salary = (this.workingDays + totalLeaveDays) * salaryRatio * 1000000;

    }

    private int sumOfLeaveDays() {
        int totalLeaveDays = 0;
        List<TimeOff> timeOffList = TimeOffManagement.getInstance().getTimeOffList();
        for (TimeOff timeOff : timeOffList) {
            if (person.getId() == timeOff.getPerson().getId()) {
                for (TimeOffDetail timeOffDetail : timeOff.getTimeOffDetailList()) {
                    if (timeOffDetail.isApproved()) {
                        if (person.getGender().equals(Gender.FEMALE)) {
                            totalLeaveDays += timeOffDetail.getAnnualLeaveDay() + timeOffDetail.getMaternityLeaveDay();
                        } else {
                            totalLeaveDays += timeOffDetail.getAnnualLeaveDay();
                        }
                    }
                }
            }
        }
        return totalLeaveDays;
    }

    @Override
    public void displayInfo() {
        System.out.printf("%-16s | %-24s | %-12s | %-12s | %-24s |\n", person.getId(), person.getName(), this.workingDays, this.sumOfLeaveDays(), this.salary);
    }

    public void displayWorkingDays() {
        System.out.printf("%-16s | %-24s | %-12s |\n", person.getId(), person.getName(), this.workingDays);
    }
}
