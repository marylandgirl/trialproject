package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userName;
    private String lastName;
    private String firstName;
    private String email;
    private double payRate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<TimeSheet> timeSheetSet;

    public Employee() {
        timeSheetSet = null;
    }

    public Employee(String userName, String lastName, String firstName, String email,
                    double payRate, Manager manager, Set<TimeSheet> timeSheetSet) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.payRate = payRate;
        this.manager = manager;
        this.timeSheetSet = timeSheetSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }


    public Set<TimeSheet> getTimeSheetSet() {
        return timeSheetSet;
    }

    public void setTimeSheetSet(Set<TimeSheet> timeSheetSet) {
        this.timeSheetSet = timeSheetSet;
    }
}
