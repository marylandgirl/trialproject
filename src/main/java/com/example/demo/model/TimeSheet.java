/**
 * This is the daily Timesheet
 */
package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TimeSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME, pattern = "yyyy-MM-dd" )
    private LocalDate startDate;

    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME, pattern = "yyyy-MM-dd" )
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="employee_id")
    Employee employee;

    private double regHours;
    private double overtimeHours;
    private double holidayHours;
    private double holidayWorkedHours;
    private double holidayOTHours;
    private double leaveNoPayHours;
    private double compTimeEarnedHours;
    private double compTimeUsedHours;
    private double annualLeaveHours;
    private int rejectCode;                        //unapproved=0, approved=1, rejected=-1
    private String rejectMsg;
    private boolean enabled;

    @OneToMany(mappedBy = "timeSheet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<TestDailyTimeEntry> dailyTimeEntrySet;

    public TimeSheet() {
        dailyTimeEntrySet = new HashSet<>();
    }

    public TimeSheet(LocalDate startDate, LocalDate endDate, Employee employee, double regHours,
                     double overtimeHours, double holidayHours, double holidayWorkedHours,
                     double holidayOTHours, double leaveNoPayHours, double compTimeEarnedHours,
                     double compTimeUsedHours, double annualLeaveHours, int rejectCode, String rejectMsg,
                     boolean enabled, Set<TestDailyTimeEntry> dailyTimeEntrySet) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.employee = employee;
        this.regHours = regHours;
        this.overtimeHours = overtimeHours;
        this.holidayHours = holidayHours;
        this.holidayWorkedHours = holidayWorkedHours;
        this.holidayOTHours = holidayOTHours;
        this.leaveNoPayHours = leaveNoPayHours;
        this.compTimeEarnedHours = compTimeEarnedHours;
        this.compTimeUsedHours = compTimeUsedHours;
        this.annualLeaveHours = annualLeaveHours;
        this.rejectCode = rejectCode;
        this.rejectMsg = rejectMsg;
        this.enabled = enabled;
        this.dailyTimeEntrySet = dailyTimeEntrySet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getRegHours() {
        return regHours;
    }

    public void setRegHours(double regHours) {
        this.regHours = regHours;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double getHolidayHours() {
        return holidayHours;
    }

    public void setHolidayHours(double holidayHours) {
        this.holidayHours = holidayHours;
    }

    public double getHolidayWorkedHours() {
        return holidayWorkedHours;
    }

    public void setHolidayWorkedHours(double holidayWorkedHours) {
        this.holidayWorkedHours = holidayWorkedHours;
    }

    public double getHolidayOTHours() {
        return holidayOTHours;
    }

    public void setHolidayOTHours(double holidayOTHours) {
        this.holidayOTHours = holidayOTHours;
    }

    public double getLeaveNoPayHours() {
        return leaveNoPayHours;
    }

    public void setLeaveNoPayHours(double leaveNoPayHours) {
        this.leaveNoPayHours = leaveNoPayHours;
    }

    public double getCompTimeEarnedHours() {
        return compTimeEarnedHours;
    }

    public void setCompTimeEarnedHours(double compTimeEarnedHours) {
        this.compTimeEarnedHours = compTimeEarnedHours;
    }

    public double getCompTimeUsedHours() {
        return compTimeUsedHours;
    }

    public void setCompTimeUsedHours(double compTimeUsedHours) {
        this.compTimeUsedHours = compTimeUsedHours;
    }

    public double getAnnualLeaveHours() {
        return annualLeaveHours;
    }

    public void setAnnualLeaveHours(double annualLeaveHours) {
        this.annualLeaveHours = annualLeaveHours;
    }

    public int getRejectCode() {
        return rejectCode;
    }

    public void setRejectCode(int rejectCode) {
        this.rejectCode = rejectCode;
    }

    public String getRejectMsg() {
        return rejectMsg;
    }

    public void setRejectMsg(String rejectMsg) {
        this.rejectMsg = rejectMsg;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<TestDailyTimeEntry> getDailyTimeEntrySet() {
        return dailyTimeEntrySet;
    }

    public void setDailyTimeEntrySet(Set<TestDailyTimeEntry> dailyTimeEntrySet) {
        this.dailyTimeEntrySet = dailyTimeEntrySet;
    }

}
