package com.example.demo.model;


import com.example.demo.repository.*;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserService userService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TestTimeSheetRepository testTimeSheetRepository;

    @Autowired
    AuditTrailService auditTrailService;

    @Autowired
    AuditTrailRepository auditTrailRepository;


    @GetMapping("/")
    public String homePage(Model model) {
        // get all employees in the model
        model.addAttribute("allUsers", employeeRepository.findAll());
        // notify the Service class to add a record of "log in" action
        auditTrailService.addLoginAudit();

        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        // get employee's role/roles by username
//        String username = employeeService.getEmployee().getUserName();
//        Set<Role> roles = new HashSet<>();
//        roles = roleRepository.findByUsername(username);

        // add the roles found above and send it to the form
//        model.addAttribute("roles", roles);

        return "index";
    }

//    @GetMapping("/")
//    public String homePage(Model model) {
//        model.addAttribute("allUsers", userRepository.findAll());
//        if(userService.getUser() != null){
//            model.addAttribute("loggedUser", userService.getUser());
//        }
//        // get employee's role/roles by username
//        String username = userService.getUser().getUsername();
//        Set<Role>roles = new HashSet<>();
//        roles = roleRepository.findByUsername(username);
//
//        // add the roles found above and send it to the form
//        model.addAttribute("roles", roles);
//
//        return "index";
//    }

    @RequestMapping("/login")
    public String login() {
//        auditTrailService.logoutAuditTrailer();
        return "login";
    }


    @RequestMapping("/logoutsuccess")
    public String logout() {

        return "redirect:/login";
    }

    @RequestMapping("/register")
    public String getRegistrationForm(Model model) {
        model.addAttribute("newUser", new Employee());
        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        return "register";
    }

    @RequestMapping("/admin-employee")
    public String adminPageListOfEmployee(Model model) {
        model.addAttribute("allUsers", employeeRepository.findAll());
        // get logged in employeeId
        long loggedInEmployeeId = employeeService.getEmployee().getId();
        Employee tempEmployee = employeeRepository.findById(loggedInEmployeeId).get();

        // inform the service class that this employee has accessed admin page spot
        auditTrailService.addAdminAudit(tempEmployee);


        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        int countPendingTimesheet = 0;
        for (TestTimesheet tt : testTimeSheetRepository.findAll()) {
            countPendingTimesheet++;
        }

        model.addAttribute("qtyOfPendingTimesheets", countPendingTimesheet);

        return "admin-list";
    }

//    @RequestMapping("/admin-employee")
//    public String adminPageListOfEmployee(Model model) {
//        model.addAttribute("allUsers", userRepository.findAll());
//        if(userService.getUser() != null){
//            model.addAttribute("loggedUser", userService.getUser());
//        }
//        return "admin-list";
//    }

    @RequestMapping("/process-registration")
    public String processRegistration(@Valid @ModelAttribute("user") User user,
                                      BindingResult result, Model model) {
        model.addAttribute("newUser", user);
        if (userService.getUser() != null) {
            model.addAttribute("loggedUser", userService.getUser());
        }
        if (result.hasErrors()) {
            user.clearPassword();
            return "register";
        } else {
            model.addAttribute("message", "User Account Created");
            user.setEnabled(false);         // admin should be able to enable the new user to login
            Role role = new Role(user.getUsername(), "ROLE_USER"); // user by default gets user role
            Set<Role> roles = new HashSet<Role>();
            roles.add(role);

            roleRepository.save(role);
            userRepository.save((user));
        }
        return "redirect:/";

    }

    @RequestMapping("/disable-user/{id}")
    public String disableUser(@PathVariable("id") long id, Model model, Principal principal) {
        Employee tempUser;
        tempUser = employeeRepository.findById(id).get();
        if (tempUser.isEnabled()) {
            tempUser.setEnabled(false);
        } else {
            tempUser.setEnabled(true);
        }
        employeeRepository.save(tempUser);
        model.addAttribute("allUsers", employeeRepository.findAll());
        String username = principal.getName();
        model.addAttribute("user", employeeRepository.findByUserName(username));
        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
//        return "redirect:/";
        return "redirect:/admin-employee";
    }


    @PostMapping("/promote-to-manager/{id})")
    public String promoteEmployeeToManagerPosition(@PathVariable("id") long id, Model model) {

        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        Employee tempEmployee;
//        tempEmployee = employeeRepository.findById(id).get();         // needs prior work

        Manager tempManager = new Manager();
        tempManager.setEmp_id(id);

        managerRepository.save(tempManager);
        return "redirect:/";

    }

    @RequestMapping("/timesheet/{id}")
    public String startTimesheet(@PathVariable("id") long id, Model model) {
        model.addAttribute("timesheet", new TestTimesheet());


        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }



        auditTrailService.logTimesheetStarted();
        return "test-timesheet";
    }

    @RequestMapping("/save-timesheet/{id}")
    public String saveTimesheet(@PathVariable("id") long id, @ModelAttribute TestTimesheet testTimeSheet, Model model) {

        Set<TestTimesheet>testTimesheets = new HashSet<>();

        for(Employee employee : employeeRepository.findAll()){
            for(TestTimesheet testTimesheet : testTimeSheetRepository.findAll()){
                if(testTimeSheet.getEmployeeId() == employee.getId()){
                    testTimesheets.add(testTimeSheet);
                }
            }
        }
        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }

        model.addAttribute("timesheets", testTimesheets);

        auditTrailService.logTimesheetSaved();
        return "testSaveTimesheet";
    }




    @RequestMapping("/submit-timesheet/{id}")
    public String submitTimesheet(@PathVariable("id") long id, @ModelAttribute TestTimesheet testTimeSheet, Model model) {

//        double thisWeekRegularHours = testTimeSheet.getRegularHours();
//        double thisWeekOvertimeHours = testTimeSheet.getOvertimeHours();
//        Employee employee = new Employee();
//        employee = employeeRepository.findById(id).get();
//        double employeeRegularRate = employee.getPayRate();
//        double totalPay = employeeRegularRate * (thisWeekRegularHours + 1.5 * thisWeekOvertimeHours);
//        // deduct tax pension etc
//        testTimeSheet.setTotalPayForThisPeriod(totalPay);
//        testTimeSheetRepository.save(testTimeSheet);
        auditTrailService.logTimesheetSubmitted();
        //send email to supervisor ...
        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        return "testSubmitTimesheet";
    }





    @RequestMapping("/timesheet-group")
    public String timesheetByGroup(@ModelAttribute TestTimesheet testTimeSheet, Model model) {


        //send email to supervisor ...
        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        return "listOfTimesheet";
    }


    @RequestMapping("/paystub")
    public String paystub(Model model) {


        //send email to supervisor ...
        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        return "paystub";
    }

































    @RequestMapping("/allTimesheets")
        public String listAllEmployee(Model model) {
        model.addAttribute("allUsers", employeeRepository.findAll());
        // get logged in employeeId
        long loggedInEmployeeId = employeeService.getEmployee().getId();
        Employee tempEmployee = employeeRepository.findById(loggedInEmployeeId).get();



        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }

        // display number of pending timesheets
        int countPendingTimesheet = 0;
        for (TestTimesheet tt : testTimeSheetRepository.findAll()) {
            countPendingTimesheet++;
        }

        model.addAttribute("qtyOfPendingTimesheets", countPendingTimesheet);

        model.addAttribute("allTimesheets", testTimeSheetRepository.findAll());

        // inform the service class that this employee has accessed admin page spot
        auditTrailService.addAdminAudit(tempEmployee);
        return "listOfAllTimesheets";
    }


    @RequestMapping("/audit-trail")
    public String auditTrail(Model model) {

        // get all log history from repository to the model
        model.addAttribute("allAudits", auditTrailRepository.findAll());


        // let the service class send back list of employees that have logged in history
        // instead of all employee
        Set<Employee> onlyEmployeesWithLogHistory = new HashSet<>();
        onlyEmployeesWithLogHistory = auditTrailService.employeesWithLogHistory();


//        model.addAttribute("auditTrackerRepo", auditTrailRepository.findAll());

        // add employees whose id found in auditTrack repository to the model
        model.addAttribute("allEmployeesLogged", onlyEmployeesWithLogHistory);

        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        return "audit-trail";
    }

//    @RequestMapping("/disable-user/{id}")
//    public String disableUser(@PathVariable("id") long id, Model model, Principal principal){
//        User tempUser;
//        tempUser = userRepository.findById(id).get();
//        if(tempUser.isEnabled()){
//            tempUser.setEnabled(false);
//        }
//        else{
//            tempUser.setEnabled(true);
//        }
//        userRepository.save(tempUser);
//        model.addAttribute("allUsers", userRepository.findAll());
//        String username = principal.getName();
//        model.addAttribute("user", userRepository.findByUsername(username));
//        if(userService.getUser() != null){
//            model.addAttribute("loggedUser", userService.getUser());
//        }
//        return "redirect:/";
//    }


}
