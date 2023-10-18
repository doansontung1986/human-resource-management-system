package logic;

import entity.Person;
import entity.TimeOff;
import entity.TimeOffDetail;
import entity.Writable;
import statics.Gender;
import utility.FileUtility;
import utility.ScannerUtility;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TimeOffManagement implements Writable {
    private static TimeOffManagement timeOffManagement;
    private List<TimeOff> timeOffList;
    private List<TimeOffDetail> totalTimeOffDetailList;
    public static final String TIME_OFF_DATA_FILE = "time-off-list.dat";
    public static final String TOTAL_TIME_OFF_DETAIL_DATA_FILE = "total-time-off-detail-list.dat";

    public TimeOffManagement() {
    }

    public TimeOffManagement(List<TimeOff> timeOffList, List<TimeOffDetail> totalTimeOffDetailList) {
        this.timeOffList = new ArrayList<>(timeOffList);
        this.totalTimeOffDetailList = new ArrayList<>(totalTimeOffDetailList);
    }

    public static TimeOffManagement getInstance() {
        if (timeOffManagement == null) {
            timeOffManagement = new TimeOffManagement();
        }

        return timeOffManagement;
    }

    public static TimeOffManagement getInstance(List<TimeOff> timeOffList, List<TimeOffDetail> totalTimeOffDetailList) {
        if (timeOffManagement == null) {
            timeOffManagement = new TimeOffManagement(timeOffList, totalTimeOffDetailList);
        }

        return timeOffManagement;
    }

    public List<TimeOff> getTimeOffList() {
        return timeOffList;
    }

    public void setTimeOffList(List<TimeOff> timeOffList) {
        this.timeOffList = new ArrayList<>(timeOffList);
    }

    public List<TimeOffDetail> getTotalTimeOffDetailList() {
        return totalTimeOffDetailList;
    }

    public void setTotalTimeOffDetailList(List<TimeOffDetail> totalTimeOffDetailList) {
        this.totalTimeOffDetailList = new ArrayList<>(totalTimeOffDetailList);
    }

    public void submitLeaveDay(Person person) {
        System.out.println("1. Nghỉ phép");
        System.out.println("2. Nghỉ thai sản");
        System.out.println("Nhập loại nghỉ phép: ");
        int type = ScannerUtility.inputValidNumberInRange(1, 2);

        TimeOff timeOff = checkExistTimeOff(person);

        if (timeOff == null) {
            timeOff = new TimeOff(person, new ArrayList<>());
        }

        switch (type) {
            case 1 -> {
                System.out.println("Nhập số ngày nghỉ phép năm (tối đa 12 ngày)");
                TimeOffDetail timeOffDetail = inputAnnualLeaveDays(type, 12);
                timeOff.getTimeOffDetailList().add(timeOffDetail);
                saveTimeOff(timeOff);
                saveTotalTimeDetail(timeOffDetail);
            }
            case 2 -> {
                if (person.getGender().equals(Gender.FEMALE)) {
                    System.out.println("Nhập số ngày nghỉ phép thai sản (tối đa 5 ngày)");
                    TimeOffDetail timeOffDetail = inputAnnualLeaveDays(type, 5);
                    timeOff.getTimeOffDetailList().add(timeOffDetail);
                    saveTimeOff(timeOff);
                    saveTotalTimeDetail(timeOffDetail);
                } else {
                    System.out.println("Nghỉ phép thai sản chỉ dảnh cho nhân viên nữ");
                }
            }
        }
    }

    private TimeOffDetail inputAnnualLeaveDays(int type, long maxDay) {
        LocalDate fromDate;
        LocalDate toDate;
        long days;

        do {
            System.out.println("Nhập ngày bắt đầu nghỉ phép");
            do {
                fromDate = inputLocalDate();
                if (checkValidAnnualLeaveDate(fromDate)) {
                    break;
                }
                System.out.println("Ngày nhập vào phải là sau ngày hôm nay");
            } while (true);

            System.out.println("Nhập ngày kết thúc nghỉ phép");
            do {
                toDate = inputLocalDate();
                if (checkValidAnnualLeaveDate(toDate)) {
                    break;
                }
                System.out.println("Ngày nhập vào phải là sau ngày hôm nay");
            } while (true);

            if (fromDate.isBefore(toDate)) {
                days = ChronoUnit.DAYS.between(fromDate, toDate);
                if (days <= maxDay) {
                    break;
                }
                System.out.println("Số ngày phép không quá " + maxDay);
            } else {
                System.out.println("Ngày bắt đầu nghỉ phép phải trước ngày kết thúc nghỉ phép");
            }
        } while (true);

        if (type == 1) {
            return new TimeOffDetail(days, 0, fromDate, toDate);
        }

        return new TimeOffDetail(0, days, fromDate, toDate);
    }

    private boolean checkValidAnnualLeaveDate(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    private LocalDate inputLocalDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date;
        do {
            try {
                String dateStr = inputDate();
                date = LocalDate.parse(dateStr, dateTimeFormatter);
                break;
            } catch (DateTimeException e) {
                System.out.println("Sai định dạng ngày");
            }
        } while (true);

        return date;
    }

    private String inputDate() {
        System.out.print("Nhập ngày: ");
        String day = new Scanner(System.in).nextLine();
        System.out.print("Nhập tháng: ");
        String month = new Scanner(System.in).nextLine();
        System.out.print("Nhập năm: ");
        String year = new Scanner(System.in).nextLine();

        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }

        return day + "-" + month + "-" + year;
    }

    public void displayTimeOffList() {
        if (this.timeOffList.isEmpty()) {
            System.out.println("Danh sách nghỉ phép rỗng");
            return;
        }

        for (TimeOff timeOff : this.timeOffList) {
            timeOff.displayInfo();
        }
    }

    public void displayTimeOffList(Person person) {
        if (this.timeOffList.isEmpty()) {
            System.out.println("Danh sách nghỉ phép rỗng");
            return;
        }

        System.out.printf("%-12s | %-36s | %-12s | %-16s | %-16s |\n", "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày phép năm", "Ngày phép thai sản");
        for (TimeOff timeOff : this.timeOffList) {
            if (timeOff.getPerson().getId() == person.getId()) {
                timeOff.displayInfo();
            }
        }
    }

    public void saveTimeOff(TimeOff timeOff) {
        this.timeOffList.add(timeOff);
        writeData();
    }

    public void saveTotalTimeDetail(TimeOffDetail timeOffDetail) {
        this.totalTimeOffDetailList.add(timeOffDetail);
        writeData();
    }

    @Override
    public void writeData() {
        FileUtility.getInstance().writeDataToFile(this.timeOffList, TIME_OFF_DATA_FILE);
        FileUtility.getInstance().writeDataToFile(this.totalTimeOffDetailList, TOTAL_TIME_OFF_DETAIL_DATA_FILE);
    }

    private TimeOff checkExistTimeOff(Person person) {
        for (TimeOff timeOff : this.timeOffList) {
            if (timeOff.getPerson().getId() == person.getId()) {
                return timeOff;
            }
        }
        return null;
    }

    public void approveLeave() {
        System.out.print("Nhập mã nghỉ phép: ");
        int timeOffDetailId = ScannerUtility.inputValidInteger();
        TimeOffDetail timeOffDetail = checkExistTimeOffDetail(timeOffDetailId);
        if (timeOffDetail != null) {
            timeOffDetail.setApproved(true);
        } else {
            System.out.println("Mã nghỉ phép không tồn tại");
        }
    }

    private TimeOffDetail checkExistTimeOffDetail(int timeOffDetailId) {
        for (TimeOff timeOff : this.timeOffList) {
            for (TimeOffDetail timeOffDetail : timeOff.getTimeOffDetailList()) {
                if (timeOffDetail.getId() == timeOffDetailId) {
                    return timeOffDetail;
                }
            }
        }

        return null;
    }
}
