package entity;

import logic.TimeOffManagement;

import java.io.Serializable;
import java.time.LocalDate;

public class TimeOffDetail implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private static int AUTO_ID;
    private int id;
    private long annualLeaveDay;
    private long maternityLeaveDay;
    private boolean isApproved;
    private LocalDate fromDate;
    private LocalDate toDate;

    public TimeOffDetail(long annualLeaveDay, long maternityLeaveDay, LocalDate fromDate, LocalDate toDate) {
        int size = TimeOffManagement.getInstance().getTotalTimeOffDetailList().size();

        if (size == 0) {
            AUTO_ID = 1000;
        } else {
            AUTO_ID = TimeOffManagement.getInstance().getTotalTimeOffDetailList().get(size - 1).getId() + 1;
        }

        this.id = AUTO_ID;

        this.annualLeaveDay = annualLeaveDay;
        this.maternityLeaveDay = maternityLeaveDay;
        this.isApproved = false;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public int getId() {
        return id;
    }

    public long getAnnualLeaveDay() {
        return annualLeaveDay;
    }

    public void setAnnualLeaveDay(int annualLeaveDay) {
        this.annualLeaveDay = annualLeaveDay;
    }

    public long getMaternityLeaveDay() {
        return maternityLeaveDay;
    }

    public void setMaternityLeaveDay(int maternityLeaveDay) {
        this.maternityLeaveDay = maternityLeaveDay;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

}
