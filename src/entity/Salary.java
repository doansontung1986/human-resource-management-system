package entity;

import logic.TimeOffManagement;
import logic.WorkDayManagement;
import statics.Gender;

import java.io.Serializable;
import java.util.List;

public class Salary implements Displayable, Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private Person person;
    private static final double SALARY_RATIO_IT = 5.5;
    private static final double SALARY_RATIO_ACCOUNTING = 3.0;
    private static final double SALARY_RATIO_OPERATION = 2.5;
    private static final double SALARY_RATIO_SALE = 4.0;
    private static final double SALARY_RATIO_MARKETING = 3.5;
    private static final double SALARY_RATIO_SUPPORT = 2.0;
    private static final double SALARY_RATIO_HR = 2.5;
    private static final double SALARY_RATIO_STAFF = 1.0;
    private double salary;

    public Salary(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double calculateSalary() {
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

        WorkDay workDay = WorkDayManagement.getInstance().checkExistWorkday(person);

        if (workDay != null) {
            this.salary = (workDay.getWorkingDays() + totalLeaveDays) * salaryRatio * 1000000;
        } else {
            System.out.println("Không có dữ liệu ngày công");
            return 0;
        }
        return this.salary;
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
        System.out.printf("%-16s | %-24s | %.2f |\n", person.getId(), person.getName(), calculateSalary());
    }
}
