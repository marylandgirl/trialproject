package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DailyTimeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME, pattern = "yyyy-MM-dd" )
    private LocalDate workDate;

    private double regHours;
    private double overtimeHours;
    private double holidayHours;
    private double holidayWorkedHours;
    private double holidayOTHours;
    private double leaveNoPayHours;
    private double compTimeEarnedHours;
    private double compTimeUsedHours;
    private double leaveHours;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "timeSheet_id")
    private TimeSheet timeSheet;

    public DailyTimeEntry() {
    }

    public DailyTimeEntry(LocalDate workDate, double regHours, double overtimeHours, double holidayHours,
                          double holidayWorkedHours, double holidayOTHours, double leaveNoPayHours,
                          double compTimeEarnedHours, double compTimeUsedHours, double leaveHours,
                          TimeSheet timeSheet) {
        this.workDate = workDate;
        this.regHours = regHours;
        this.overtimeHours = overtimeHours;
        this.holidayHours = holidayHours;
        this.holidayWorkedHours = holidayWorkedHours;
        this.holidayOTHours = holidayOTHours;
        this.leaveNoPayHours = leaveNoPayHours;
        this.compTimeEarnedHours = compTimeEarnedHours;
        this.compTimeUsedHours = compTimeUsedHours;
        this.leaveHours = leaveHours;
        this.timeSheet = timeSheet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
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

    public double getLeaveHours() {
        return leaveHours;
    }

    public void setLeaveHours(double leaveHours) {
        this.leaveHours = leaveHours;
    }

    public TimeSheet getTimeSheet() {
        return timeSheet;
    }

    public void setTimeSheet(TimeSheet timeSheet) {
        this.timeSheet = timeSheet;
    }
}
