package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class AuditTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long staffId;       // foreign key
    private long timesheetId;       // foreign key

    private String action;      //"logged_in", "logged_out", "timesheet_started", "timesheet_saved",
                                // "timesheet_submitted", "timesheet_approved", "timesheet_rejected"
    private String timeOfAction;

    public AuditTrail() {
    }

    public AuditTrail(long staffId, long timesheetId, String action, String timeOfAction) {
        this.staffId = staffId;
        this.action = action;
        this.timeOfAction = timeOfAction;
        this.timesheetId = timesheetId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimeOfAction() {
        return timeOfAction;
    }

    public void setTimeOfAction(String timeOfAction) {
        this.timeOfAction = timeOfAction;
    }

    public long getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(long timesheetId) {
        this.timesheetId = timesheetId;
    }
}
