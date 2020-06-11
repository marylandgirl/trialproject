package com.example.demo.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="employees_db")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userName;

    private String password;

    private String lastName;
    private String firstName;
    private String email;
    private double payRate;
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<TimeSheet> timeSheetSet;

    public Employee() {
        timeSheetSet = null;
    }

    public Employee(String userName, String password, String lastName, String firstName, String email,
                    double payRate, boolean enabled, Manager manager, Set<TimeSheet> timeSheetSet) {
        this.userName = userName;
        this.setPassword(password);     // call the setPassword method to encode
        // the password and check its length
        // (user should enter >= 3) before go
        // and be saved in database
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.payRate = payRate;
        this.enabled = enabled;
        this.manager = manager;
        this.timeSheetSet = timeSheetSet;
    }


    public Employee(String userName, String password, String lastName, String firstName, String email,
                    double payRate, boolean enabled, Manager manager) {
        this.userName = userName;
        this.setPassword(password);     // call the setPassword method to encode
                                        // the password and check its length
                                        // (user should enter >= 3) before go
                                        // and be saved in database
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.payRate = payRate;
        this.enabled = enabled;
        this.manager = manager;

    }

    public void clearPassword(){        // when registration is updated or has
        this.password = "";             // error the password input box will be
    }                                   // flushed and is seen empty in the form.

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.length() < 3){
            this.password = password;
        } else{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            this.password = passwordEncoder.encode(password);
        }
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
