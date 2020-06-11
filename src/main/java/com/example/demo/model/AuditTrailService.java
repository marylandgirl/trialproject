package com.example.demo.model;

import com.example.demo.repository.AuditTrailRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AuditTrailService {

    @Autowired
    AuditTrailRepository auditTrailRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

        // always save an employee when login and check out the current user if similar to the one saved.
        // So if the two are different then that means that the previous one has logged out.
        // But please do not ask when.
        ArrayList<Employee> lastLoggedInEmployee = new ArrayList<>();


    public void addLoginAudit(){

        Employee lastLoggedEmployee = null;
        Employee tempCurrentEmployee = null;
        long tempEmpId = 0;

        if(employeeService.getEmployee() != null){
            tempEmpId = employeeService.getEmployee().getId();
            //save current user to estimate logout time
            lastLoggedEmployee = auditLogout();
            tempCurrentEmployee = employeeService.getEmployee();
            if(lastLoggedEmployee.getId() != tempCurrentEmployee.getId()){
                previousEmployeeLoggedOut();
            }
        }

        long staffId = tempEmpId;
        long timesheetId = -1;

        String action = tempCurrentEmployee.getUserName() + " logged in";

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();
        String dateLoggedIn = df.format(dateObj);

        AuditTrail loginAuditTrail = new AuditTrail(staffId, timesheetId,action, dateLoggedIn);

        auditTrailRepository.save(loginAuditTrail);
    }

    public void addAdminAudit(Employee tempEmployee){
        long staffId = tempEmployee.getId();
        long timesheetId = -1;

        String action = tempEmployee.getUserName() + " accessed admin page";
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();
        String dateLoggedIn = df.format(dateObj);

        AuditTrail logAdminPageAuditTrail = new AuditTrail(staffId, timesheetId,action, dateLoggedIn);

        auditTrailRepository.save(logAdminPageAuditTrail);
    }

    public Set<Employee> employeesWithLogHistory() {

        Set<Employee> employeesWithLog = new HashSet<>();

        // iterate through each and all the log records
        for (AuditTrail tempAuditTrail : auditTrailRepository.findAll()) {
            // get the each log record's unique id
            long tempEmpId = tempAuditTrail.getStaffId();
            //iterate through all employees and identify those with log history atleast once
            for (Employee tempEmp : employeeRepository.findAll()) {
                // id will be found if employee has log experience
                if (tempEmpId == tempEmp.getId()) {
                    // add employee to the collection
                    employeesWithLog.add(tempEmp);
                }
            }
        }
        return employeesWithLog;
    }

    public Employee auditLogout(){

        // check if you are the first user logged in. That means the array with one member only is null.
        if(lastLoggedInEmployee.size() == 0){
            //null means you are the first user logged in
            lastLoggedInEmployee.add(employeeService.getEmployee());
        }

        Employee temp = lastLoggedInEmployee.get(0);
        return temp;
    }

    public void previousEmployeeLoggedOut(){
        Employee tempEmployee = lastLoggedInEmployee.get(0);

        if(tempEmployee.getId() != employeeService.getEmployee().getId()){

        long staffId = tempEmployee.getId();
        long timesheetId = -1;

        String action = tempEmployee.getUserName() + " logged out";

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();
        String dateLoggedIn = df.format(dateObj);

        AuditTrail logAdminPageAuditTrail = new AuditTrail(staffId, timesheetId,action, dateLoggedIn);

        auditTrailRepository.save(logAdminPageAuditTrail);
        lastLoggedInEmployee.set(0, employeeService.getEmployee()); /////
        }
    }

    public void logTimesheetStarted(){

        Employee tempCurrentEmployee = null;
        long tempEmpId = 0;

        if(employeeService.getEmployee() != null){
            tempEmpId = employeeService.getEmployee().getId();
            tempCurrentEmployee = employeeService.getEmployee();

        }

        long staffId = employeeService.getEmployee().getId();
        long timesheetId = -1;

        String action = employeeService.getEmployee().getUserName() + " started timesheet";

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();
        String dateLoggedIn = df.format(dateObj);

        AuditTrail loginAuditTrail = new AuditTrail(staffId, timesheetId,action, dateLoggedIn);

        auditTrailRepository.save(loginAuditTrail);

    }

    public void logTimesheetSaved(){
        Employee tempCurrentEmployee = null;
        long tempEmpId = 0;

        if(employeeService.getEmployee() != null){
            tempEmpId = employeeService.getEmployee().getId();
            tempCurrentEmployee = employeeService.getEmployee();
        }
        long staffId = employeeService.getEmployee().getId();
        long timesheetId = -1;

        String action = employeeService.getEmployee().getUserName() + " saved timesheet";

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();
        String dateLoggedIn = df.format(dateObj);

        AuditTrail logTimesheetSaved = new AuditTrail(staffId, timesheetId,action, dateLoggedIn);

        auditTrailRepository.save(logTimesheetSaved);
    }

    public void logTimesheetSubmitted(){
        Employee tempCurrentEmployee = null;
        long tempEmpId = 0;

        if(employeeService.getEmployee() != null){
            tempEmpId = employeeService.getEmployee().getId();
            tempCurrentEmployee = employeeService.getEmployee();
        }
        long staffId = employeeService.getEmployee().getId();
        long timesheetId = -1;

        String action = employeeService.getEmployee().getUserName() + " submitted timesheet";

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();
        String dateLoggedIn = df.format(dateObj);

        AuditTrail logTimesheetSaved = new AuditTrail(staffId, timesheetId,action, dateLoggedIn);

        auditTrailRepository.save(logTimesheetSaved);
    }

}   // end of service class
