package com.example.demo;

import com.example.demo.model.DailyTimeEntry;
import com.example.demo.model.Employee;
import com.example.demo.model.TimeSheet;
import org.apache.tomcat.jni.Local;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class WeeklyTimeEntry {

    private String[] regTimeStart;
    private String[] regTimeEnd;
    private String[] overtimeStart;
    private String[] overtimeEnd;
    private String[] paidHdyStart;
    private String[] paidHdyEnd;
    private String[] hdyWorkedStart;
    private String[] hdyWorkedEnd;
    private String[] leaveWOPayStart;
    private String[] leaveWOPayEnd;
    private String[] compTimeEarnedStart;
    private String[] compTimeEarnedEnd;
    private String[] annualLeaveStart;
    private String[] annualLeaveEnd;
    private String[] compTimeUsedStart;
    private String[] compTimeUsedEnd;
    private String[] holidayOTStart;
    private String[] holidayOTEnd;
    static final int DAYS_PER_PAY_PERIOD = 7;

    public WeeklyTimeEntry() {
        initialize();
    }

    public String[] getRegTimeStart() {
        return regTimeStart;
    }

    public void setRegTimeStart(String[] regTimeStart) {
        this.regTimeStart = regTimeStart;
    }

    public String[] getRegTimeEnd() {
        return regTimeEnd;
    }

    public void setRegTimeEnd(String[] regTimeEnd) {
        this.regTimeEnd = regTimeEnd;
    }

    public String[] getOvertimeStart() {
        return overtimeStart;
    }

    public void setOvertimeStart(String[] overtimeStart) {
        this.overtimeStart = overtimeStart;
    }

    public String[] getOvertimeEnd() {
        return overtimeEnd;
    }

    public void setOvertimeEnd(String[] overtimeEnd) {
        this.overtimeEnd = overtimeEnd;
    }

    public String[] getPaidHdyStart() {
        return paidHdyStart;
    }

    public void setPaidHdyStart(String[] paidHdyStart) {
        this.paidHdyStart = paidHdyStart;
    }

    public String[] getPaidHdyEnd() {
        return paidHdyEnd;
    }

    public void setPaidHdyEnd(String[] paidHdyEnd) {
        this.paidHdyEnd = paidHdyEnd;
    }

    public String[] getHdyWorkedStart() {
        return hdyWorkedStart;
    }

    public void setHdyWorkedStart(String[] hdyWorkedStart) {
        this.hdyWorkedStart = hdyWorkedStart;
    }

    public String[] getHdyWorkedEnd() {
        return hdyWorkedEnd;
    }

    public void setHdyWorkedEnd(String[] hdyWorkedEnd) {
        this.hdyWorkedEnd = hdyWorkedEnd;
    }

    public String[] getLeaveWOPayStart() {
        return leaveWOPayStart;
    }

    public void setLeaveWOPayStart(String[] leaveWOPayStart) {
        this.leaveWOPayStart = leaveWOPayStart;
    }

    public String[] getLeaveWOPayEnd() {
        return leaveWOPayEnd;
    }

    public void setLeaveWOPayEnd(String[] leaveWOPayEnd) {
        this.leaveWOPayEnd = leaveWOPayEnd;
    }

    public String[] getCompTimeEarnedStart() {
        return compTimeEarnedStart;
    }

    public void setCompTimeEarnedStart(String[] compTimeEarnedStart) {
        this.compTimeEarnedStart = compTimeEarnedStart;
    }

    public String[] getCompTimeEarnedEnd() {
        return compTimeEarnedEnd;
    }

    public void setCompTimeEarnedEnd(String[] compTimeEarnedEnd) {
        this.compTimeEarnedEnd = compTimeEarnedEnd;
    }

    public String[] getAnnualLeaveStart() {
        return annualLeaveStart;
    }

    public void setAnnualLeaveStart(String[] annualLeaveStart) {
        this.annualLeaveStart = annualLeaveStart;
    }

    public String[] getAnnualLeaveEnd() {
        return annualLeaveEnd;
    }

    public void setAnnualLeaveEnd(String[] annualLeaveEnd) {
        this.annualLeaveEnd = annualLeaveEnd;
    }

    public String[] getCompTimeUsedStart() {
        return compTimeUsedStart;
    }

    public void setCompTimeUsedStart(String[] compTimeUsedStart) {
        this.compTimeUsedStart = compTimeUsedStart;
    }

    public String[] getCompTimeUsedEnd() {
        return compTimeUsedEnd;
    }

    public void setCompTimeUsedEnd(String[] compTimeUsedEnd) {
        this.compTimeUsedEnd = compTimeUsedEnd;
    }

    public String[] getHolidayOTStart() {
        return holidayOTStart;
    }

    public void setHolidayOTStart(String[] holidayOTStart) {
        this.holidayOTStart = holidayOTStart;
    }

    public String[] getHolidayOTEnd() {
        return holidayOTEnd;
    }

    public void setHolidayOTEnd(String[] holidayOTEnd) {
        this.holidayOTEnd = holidayOTEnd;
    }

    private void initialize() {
        regTimeStart = new String [7];
        Arrays.setAll(regTimeStart,i->"__:__ __");
        regTimeEnd = new String [7];
        Arrays.setAll(regTimeEnd,i->"__:__ __");
        overtimeStart = new String [7];
        Arrays.setAll(overtimeStart,i->"__:__ __");
        overtimeEnd = new String [7];
        Arrays.setAll(overtimeEnd,i->"__:__ __");
        paidHdyStart = new String [7];
        Arrays.setAll(paidHdyStart,i->"__:__ __");
        paidHdyEnd = new String [7];
        Arrays.setAll(paidHdyEnd,i->"__:__ __");
        hdyWorkedStart = new String [7];
        Arrays.setAll(hdyWorkedStart,i->"__:__ __");
        hdyWorkedEnd = new String [7];
        Arrays.setAll(hdyWorkedEnd,i->"__:__ __");
        leaveWOPayStart = new String [7];
        Arrays.setAll(leaveWOPayStart,i->"__:__ __");
        leaveWOPayEnd = new String [7];
        Arrays.setAll(leaveWOPayEnd,i->"__:__ __");
        compTimeEarnedStart = new String [7];
        Arrays.setAll(compTimeEarnedStart,i->"__:__ __");
        compTimeEarnedEnd = new String [7];
        Arrays.setAll(compTimeEarnedEnd,i->"__:__ __");
        annualLeaveStart = new String [7];
        Arrays.setAll(annualLeaveStart,i->"__:__ __");
        annualLeaveEnd = new String [7];
        Arrays.setAll(annualLeaveEnd,i->"__:__ __");
        compTimeUsedStart = new String [7];
        Arrays.setAll(compTimeUsedStart,i->"__:__ __");
        compTimeUsedEnd = new String [7];
        Arrays.setAll(compTimeUsedEnd,i->"__:__ __");
        holidayOTStart = new String [7];
        Arrays.setAll(holidayOTStart,i->"__:__ __");
        holidayOTEnd = new String [7];
        Arrays.setAll(holidayOTEnd,i->"__:__ __");
    }

    public DailyTimeEntry[] updateDailyEntries(LocalDate begDate, LocalDate endDate, TimeSheet tSheet) {
        TimeSheet timeSheet = tSheet;
        DailyTimeEntry[] dailyTimeEntries = new DailyTimeEntry[7];
        timeSheet.setStartDate(begDate);
        timeSheet.setEndDate(endDate);
        timeSheet.setEnabled(true);
        for ( int i = 0; i < DAYS_PER_PAY_PERIOD; i++) {
            dailyTimeEntries[i] = new DailyTimeEntry();
            //***For testing to get around null pointer exception****
     /*       timeSheet.setRejectMsg("All is Good");
            timeSheet.setEmployee(new Employee());*/
            //************
            dailyTimeEntries[i].setWorkDate(begDate.plusDays(i));
            try {
                LocalTime startTime = LocalTime.parse(regTimeStart[i]);
                LocalTime endTime = LocalTime.parse(regTimeEnd[i]);
                Duration diff = Duration.between(startTime,endTime);
                double regHours = ((double) diff.toMinutes()/60.0);
                dailyTimeEntries[i].setRegHours(regHours);
                //  dailyTimeEntries[i].getTimeSheet().updateRegHours(regHours);
                timeSheet.updateRegHours(regHours);
            } catch (Exception e){
                e.getMessage();
            }

            try {
                LocalTime startTime = LocalTime.parse(overtimeStart[i]);
                LocalTime endTime = LocalTime.parse(overtimeEnd[i]);
                Duration diff = Duration.between(startTime,endTime);
                double overtimeHours = ((double) diff.toMinutes()/60.0);
                dailyTimeEntries[i].setOvertimeHours(overtimeHours);
                dailyTimeEntries[i].getTimeSheet().updateOvertimeHours(overtimeHours);
            } catch (Exception e){
                e.getMessage();
            }

            try {
                LocalTime startTime = LocalTime.parse(paidHdyStart[i]);
                LocalTime endTime = LocalTime.parse(paidHdyEnd[i]);
                Duration diff = Duration.between(startTime,endTime);
                double holidayHours = ((double) diff.toMinutes()/60.0);
                dailyTimeEntries[i].setHolidayHours(holidayHours);
                dailyTimeEntries[i].getTimeSheet().updateHolidayHours(holidayHours);
            } catch (Exception e){
                e.getMessage();
            }

            try {
                LocalTime startTime = LocalTime.parse(hdyWorkedStart[i]);
                LocalTime endTime = LocalTime.parse(hdyWorkedEnd[i]);
                Duration diff = Duration.between(startTime,endTime);
                double holidayWorkedHours = ((double) diff.toMinutes()/60.0);
                dailyTimeEntries[i].setHolidayWorkedHours(holidayWorkedHours);
                dailyTimeEntries[i].getTimeSheet().updateHolidayWorkedHours(holidayWorkedHours);

            } catch (Exception e){
                e.getMessage();
            }

            try {
                LocalTime startTime = LocalTime.parse(leaveWOPayStart[i]);
                LocalTime endTime = LocalTime.parse(leaveWOPayEnd[i]);
                Duration diff = Duration.between(startTime,endTime);
                double leaveNoPayHours = ((double) diff.toMinutes()/60.0);
                dailyTimeEntries[i].setLeaveNoPayHours(leaveNoPayHours);
                dailyTimeEntries[i].getTimeSheet().updateLeaveNoPayHours(leaveNoPayHours);

            } catch (Exception e){
                e.getMessage();
            }

            try {
                LocalTime startTime = LocalTime.parse(compTimeEarnedStart[i]);
                LocalTime endTime = LocalTime.parse(compTimeEarnedEnd[i]);
                Duration diff = Duration.between(startTime,endTime);
                double compTimeEarnedHours = ((double) diff.toMinutes()/60.0);
                dailyTimeEntries[i].setCompTimeEarnedHours(compTimeEarnedHours);
                dailyTimeEntries[i].getTimeSheet().updateCompTimeEarnedHours(compTimeEarnedHours);

            } catch (Exception e){
                e.getMessage();
            }

            try {
                LocalTime startTime = LocalTime.parse(annualLeaveStart[i]);
                LocalTime endTime = LocalTime.parse(annualLeaveEnd[i]);
                Duration diff = Duration.between(startTime,endTime);
                double leaveHours = ((double) diff.toMinutes()/60.0);
                dailyTimeEntries[i].setLeaveHours(leaveHours);
                dailyTimeEntries[i].getTimeSheet().updateAnnualLeaveHours(leaveHours);

            } catch (Exception e){
                e.getMessage();
            }

            try {
                LocalTime startTime = LocalTime.parse(holidayOTStart[i]);
                LocalTime endTime = LocalTime.parse(holidayOTEnd[i]);
                Duration diff = Duration.between(startTime,endTime);
                double holidayOTHours = ((double) diff.toMinutes()/60.0);
                dailyTimeEntries[i].setHolidayOTHours(holidayOTHours);
                dailyTimeEntries[i].getTimeSheet().updateHolidayOTHours(holidayOTHours);

            } catch (Exception e){
                e.getMessage();
            }

            try {
                LocalTime startTime = LocalTime.parse(compTimeUsedStart[i]);
                LocalTime endTime = LocalTime.parse(compTimeUsedEnd[i]);
                Duration diff = Duration.between(startTime,endTime);
                double compTimeUsedHours = ((double) diff.toMinutes()/60.0);
                dailyTimeEntries[i].setCompTimeUsedHours(compTimeUsedHours);
                dailyTimeEntries[i].getTimeSheet().updateCompTimeUsedHours(compTimeUsedHours);
            } catch (Exception e){
                e.getMessage();
            }

            try {
                dailyTimeEntries[i].setWorkDate(begDate.plusDays(i));
                dailyTimeEntries[i].setTimeSheet(timeSheet);
            } catch (Exception err) {
                err.getMessage();
            }
        }
        return dailyTimeEntries;
    }
}