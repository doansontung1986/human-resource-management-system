package entity;

import statics.Gender;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TimeOff implements Displayable, Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private Person person;
    private List<TimeOffDetail> timeOffDetailList;

    public TimeOff(Person person, List<TimeOffDetail> timeOffDetailList) {
        this.person = person;
        this.timeOffDetailList = new ArrayList<>(timeOffDetailList);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<TimeOffDetail> getTimeOffDetailList() {
        return timeOffDetailList;
    }

    public void setTimeOffDetailList(List<TimeOffDetail> timeOffDetailList) {
        this.timeOffDetailList = new ArrayList<>(timeOffDetailList);
    }

    @Override
    public void displayInfo() {
        String status;
        System.out.println("Thông tin nghỉ phép của nhân viên " + "(" + this.person.getId() + " - " + this.person.getName() + " - Giới tính: " + this.person.getGender() + ")");
        if (person.getGender().equals(Gender.FEMALE)) {
            for (TimeOffDetail timeOffDetail : this.timeOffDetailList) {
                if (timeOffDetail.isApproved()){
                    status = "Đã duyệt";
                } else {
                    status = "Chưa duyệt";
                }

                if (timeOffDetail.getAnnualLeaveDay() > 0) {
                    System.out.printf("%-12s | %-24s | %-16s | %-16s | %-16s | %-16s |\n", timeOffDetail.getId(), "Ngày phép năm", timeOffDetail.getAnnualLeaveDay(), timeOffDetail.getFromDate(), timeOffDetail.getToDate(), status);
                } else {
                    System.out.printf("%-12s | %-24s | %-16s | %-16s | %-16s | %-16s |\n", timeOffDetail.getId(), "Ngày phép thai sản", timeOffDetail.getMaternityLeaveDay(), timeOffDetail.getFromDate(), timeOffDetail.getToDate(), status);
                }
            }
        } else {
            for (TimeOffDetail timeOffDetail : this.timeOffDetailList) {
                if (timeOffDetail.isApproved()){
                    status = "Đã duyệt";
                } else {
                    status = "Chưa duyệt";
                }

                System.out.printf("%-12s | %-24s | %-16s | %-16s | %-16s | %-16s |\n", timeOffDetail.getId(), "Ngày phép năm", timeOffDetail.getAnnualLeaveDay(), timeOffDetail.getFromDate(), timeOffDetail.getToDate(), status);
            }
        }
        System.out.println("-------------------------------------------");
    }
}
