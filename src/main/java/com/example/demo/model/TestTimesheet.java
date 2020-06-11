package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TestTimesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long employeeId;

    private double regularHours;
    private double overtimeHours;
    private double totalPayForThisPeriod;

    public TestTimesheet() {
    }

    public TestTimesheet(long employeeId, double regularHours, double overtimeHours, double totalPayForThisPeriod) {
        this.regularHours = regularHours;
        this.overtimeHours = overtimeHours;
        this.totalPayForThisPeriod = totalPayForThisPeriod;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public double getRegularHours() {
        return regularHours;
    }

    public void setRegularHours(double regularHours) {
        this.regularHours = regularHours;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double getTotalPayForThisPeriod() {
        return totalPayForThisPeriod;
    }

    public void setTotalPayForThisPeriod(double totalPayForThisPeriod) {
        this.totalPayForThisPeriod = totalPayForThisPeriod;
    }
}
