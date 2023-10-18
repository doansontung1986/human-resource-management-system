package entity;

import utility.ScannerUtility;

import java.io.Serializable;

public class WorkDay implements Displayable, Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private static final int MAX_WORKING_DAYS = 22;
    private Person person;
    private int workingDays;

    public WorkDay(Person person) {
        this.person = person;
    }

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

    public void inputWorkingDays() {
        System.out.print("Nhập số ngày công: ");
        int workingDays;
        do {
            workingDays = ScannerUtility.inputValidNumberInRange(1, 22);
            if (workingDays <= MAX_WORKING_DAYS - this.workingDays) {
                break;
            }
            System.out.println("Bạn không thể nhập thêm số ngày công quá số ngày còn lại: " + (MAX_WORKING_DAYS - this.workingDays));
            System.out.print("Bạn có muốn tiếp tục không ? (Y/N)");
            String continueToInput = ScannerUtility.inputValidString();
            if (continueToInput.equalsIgnoreCase("N")) {
                workingDays = 0;
                break;
            }
        } while (true);

        this.workingDays += workingDays;
    }

    @Override
    public void displayInfo() {
        System.out.printf("%-16s | %-24s | %-12s |\n", person.getId(), person.getName(), this.workingDays);
    }
}
