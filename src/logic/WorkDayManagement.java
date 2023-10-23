package logic;

import entity.Person;
import entity.WorkDay;
import entity.Writable;
import utility.FileUtility;
import utility.ScannerUtility;

import java.util.ArrayList;
import java.util.List;

public class WorkDayManagement implements Writable {
    private static WorkDayManagement workDayManagement;
    private List<WorkDay> workDayList;
    public static final String WORKDAY_DATA_FILE = "workday-list.dat";

    private WorkDayManagement() {
    }

    private WorkDayManagement(List<WorkDay> workDayList) {
        this.workDayList = new ArrayList<>(workDayList);
    }

    public static WorkDayManagement getInstance() {
        if (workDayManagement == null) {
            workDayManagement = new WorkDayManagement();
        }
        return workDayManagement;
    }

    public static WorkDayManagement getInstance(List<WorkDay> workDayList) {
        if (workDayManagement == null) {
            workDayManagement = new WorkDayManagement(workDayList);
        }
        return workDayManagement;
    }

    public void saveWorkday(WorkDay workDay) {
        this.workDayList.add(workDay);
        writeData();
    }

    @Override
    public void writeData() {
        FileUtility.getInstance().writeDataToFile(this.workDayList, WORKDAY_DATA_FILE);
    }

    public void inputWorkingDays(Person person) {
        WorkDay workDay = checkExistWorkday(person);
        if (workDay == null) {
            workDay = new WorkDay(person);
        }
        workDay.inputWorkingDays();
        saveWorkday(workDay);
    }

    public void displayWorkingDays() {
        if (this.workDayList.isEmpty()) {
            System.out.println("Danh sách ngày công rỗng");
            return;
        }

        System.out.printf("%-16s | %-24s | %-12s |\n", "Mã nhân viên", "Tên nhân viên", "Số ngày công");
        for (WorkDay workDay : this.workDayList) {
            workDay.displayInfo();
        }
    }

    public void filterByWorkingDays() {
        if (this.workDayList.isEmpty()) {
            System.out.println("Danh sách ngày công rỗng");
            return;
        }

        System.out.println("Nhập số ngày công cần lọc: ");
        int days = ScannerUtility.inputValidNumberInRange(1, 22);
        System.out.println("Danh sách các nhân viên có số ngày công ít hơn " + days + " ngày");
        System.out.printf("%-16s | %-24s | %-12s |\n", "Mã nhân viên", "Tên nhân viên", "Số ngày công");
        for (WorkDay workDay : this.workDayList) {
            if (workDay.getWorkingDays() <= days) {
                workDay.displayInfo();
            }
        }
    }

    public List<WorkDay> sortByWorkingDaysInDecrease() {
        List<WorkDay> workDayList = new ArrayList<>(this.workDayList);
        workDayList.sort((o1, o2) -> o2.getWorkingDays() - o1.getWorkingDays());
        return workDayList;
    }

    public void displayWorkingDays(Person person) {
        if (this.workDayList.isEmpty()) {
            System.out.println("Danh sách ngày công rỗng");
            return;
        }

        System.out.printf("%-16s | %-24s | %-12s |\n", "Mã nhân viên", "Tên nhân viên", "Số ngày công");
        for (WorkDay workDay : this.workDayList) {
            if (workDay.getPerson().getId() == person.getId()) {
                workDay.displayInfo();
                break;
            }
        }
    }

    public void displayWorkingDays(List<WorkDay> workDayList) {
        if (workDayList.isEmpty()) {
            System.out.println("Danh sách ngày công rỗng");
            return;
        }

        System.out.printf("%-16s | %-24s | %-12s |\n", "Mã nhân viên", "Tên nhân viên", "Số ngày công");
        for (WorkDay workDay : workDayList) {
            workDay.displayInfo();
        }
    }

    public WorkDay checkExistWorkday(Person person) {
        if (this.workDayList.isEmpty()) {
            return null;
        }

        for (WorkDay workDay : this.workDayList) {
            if (workDay.getPerson().getId() == person.getId()) {
                return workDay;
            }
        }

        return null;
    }
}
