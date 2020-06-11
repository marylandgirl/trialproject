package com.example.demo.model;

import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TimeSheetRepository timeSheetRepository;

    @Autowired
    AuditTrailService auditTrailService;

    @Autowired
    AuditTrailRepository auditTrailRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        // get all employees in the model
        model.addAttribute("allEmployees", employeeRepository.findAll());
        // notify the Service class to add a record of "log in" action
        auditTrailService.addLoginAudit();

        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }

        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping("/logoutSuccess")
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

    @RequestMapping("/adminPage")
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
        for (TimeSheet tt : timeSheetRepository.findAll()) {
            countPendingTimesheet++;
        }

        model.addAttribute("qtyOfPendingTimesheets", countPendingTimesheet);

        return "adminList";
    }

    @RequestMapping("/process-registration")
    public String processRegistration(@Valid @ModelAttribute("user") Employee employee,
                                      BindingResult result, Model model) {
        model.addAttribute("newUser", employee);
        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        if (result.hasErrors()) {
            employee.clearPassword();
            return "register";
        } else {
            model.addAttribute("message", "User Account Created");
            employee.setEnabled(false);         // admin should be able to enable the new user to login
            Role role = new Role(employee.getUserName(), "ROLE_USER"); // user by default gets user role
            Set<Role> roles = new HashSet<Role>();
            roles.add(role);

            roleRepository.save(role);
            employeeRepository.save((employee));
        }
        return "redirect:/";
    }

    @RequestMapping("/disableUser/{id}")
    public String disableOrEnableEmployee(@PathVariable("id") long id, Model model, Principal principal) {
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
        return "redirect:/adminPage";
    }


    @PostMapping("/promote-to-manager/{id})")
    public String promoteEmployeeToManagerPosition(@PathVariable("id") long id, Model model) {

        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }

        Manager tempManager = new Manager();
        tempManager.setEmp_id(id);

        managerRepository.save(tempManager);
        return "redirect:/";
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
        for (TimeSheet tt : timeSheetRepository.findAll()) {
            countPendingTimesheet++;
        }

        model.addAttribute("qtyOfPendingTimesheets", countPendingTimesheet);

        model.addAttribute("allTimesheets", timeSheetRepository.findAll());

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

        // add employees whose id found in auditTrack repository to the model
        model.addAttribute("allEmployeesLogged", onlyEmployeesWithLogHistory);

        if (employeeService.getEmployee() != null) {
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        return "audit-trail";
    }
}
