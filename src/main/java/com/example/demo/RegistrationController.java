package com.example.demo;

import com.example.demo.model.AuditTrailService;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeService;
import com.example.demo.model.Role;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController {

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


    /** Registration**/
    @GetMapping("/register")
    public String getRegistrationForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("managers", managerRepository.findAll());
        model.addAttribute("count", managerRepository.count());
        if(employeeService.getEmployee() != null){
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        return "registrationBilen";
    }
    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("employee") Employee employee,
                                      BindingResult result, Model model) {
        model.addAttribute("managers", managerRepository.findAll());
        model.addAttribute("count", managerRepository.count());
        if(employeeService.getEmployee() != null){
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        if(result.hasErrors()) {
            model.addAttribute("employee", employee);
            employee.clearPassword();
            return "register";
        }
        else {
            model.addAttribute("message", "User Account Created");
            employee.setEnabled(false);// admin should be able to enable the new user to login
            employeeRepository.save(employee);
            Role role = new Role(employee.getUserName(), "ROLE_USER"); // user by default gets user role
            roleRepository.save(role);

        }
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String detailEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findAll());
        return "updateEmployee";
    }

    @PostMapping("/saveUpdate")
    public String saveUpdate(@ModelAttribute("employee") Employee employee){
        employeeRepository.save(employee);
        return "redirect:/";
    }
}
