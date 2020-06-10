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



    @GetMapping("/")
    public String homePage(Model model) {
    model.addAttribute("allUsers", employeeRepository.findAll());
        if(employeeService.getEmployee() != null){
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        // get employee's role/roles by username
        String username = employeeService.getEmployee().getUserName();
        Set<Role>roles = new HashSet<>();
        roles = roleRepository.findByUsername(username);

        // add the roles found above and send it to the form
        model.addAttribute("roles", roles);

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
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String getRegistrationForm(Model model) {
        model.addAttribute("newUser", new Employee());
        if(employeeService.getEmployee() != null){
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        return "register";
    }

    @RequestMapping("/admin-employee")
    public String adminPageListOfEmployee(Model model) {
        model.addAttribute("allUsers", employeeRepository.findAll());
        if(employeeService.getEmployee() != null){
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        int countPendingTimesheet = 0;
        for(TestTimesheet tt : testTimeSheetRepository.findAll()){
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
        if(userService.getUser() != null){
            model.addAttribute("loggedUser", userService.getUser());
        }
        if(result.hasErrors()) {
            user.clearPassword();
            return "register";
        }
        else {
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
    public String disableUser(@PathVariable("id") long id, Model model, Principal principal){
        Employee tempUser;
        tempUser = employeeRepository.findById(id).get();
        if(tempUser.isEnabled()){
            tempUser.setEnabled(false);
        }
        else{
            tempUser.setEnabled(true);
        }
        employeeRepository.save(tempUser);
        model.addAttribute("allUsers", employeeRepository.findAll());
        String username = principal.getName();
        model.addAttribute("user", employeeRepository.findByUserName(username));
        if(employeeService.getEmployee() != null){
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
//        return "redirect:/";
        return "redirect:/admin-employee";
    }


    @PostMapping("/promote-to-manager/{id})")
    public String promoteEmployeeToManagerPosition(@PathVariable("id") long id, Model model){

        if(employeeService.getEmployee() != null){
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        Employee tempEmployee;
//        tempEmployee = employeeRepository.findById(id).get();         // needs prior work

        Manager tempManager = new Manager();
        tempManager.setEmp_id(id);
        System.out.println("adm-adm-line164-homecontroler emp-id = " + tempManager.getEmp_id());
        managerRepository.save(tempManager);
        return "redirect:/";

    }

    @RequestMapping("/timesheet/{id}")
    public String startTimesheet(@PathVariable("id") long id, Model model){

        model.addAttribute("timesheet", new TestTimesheet());

        if(employeeService.getEmployee() != null){
            model.addAttribute("loggedUser", employeeService.getEmployee());
        }
        return "test-timesheet";
    }

    @PostMapping("/process-timesheet")
    public String processTimesheet(@ModelAttribute Long id, @ModelAttribute TestTimesheet testTimeSheet, Model model){

        double thisWeekRegularHours = testTimeSheet.getRegularHours();
        double thisWeekOvertimeHours = testTimeSheet.getOvertimeHours();
        Employee employee = new Employee();
        employee = employeeRepository.findById(id).get();
        double employeeRegularRate = employee.getPayRate();
        double totalPay = employeeRegularRate * (thisWeekRegularHours + 1.5 * thisWeekOvertimeHours);
        // deduct tax pension etc
        testTimeSheet.setTotalPayForThisPeriod(totalPay);
        testTimeSheetRepository.save(testTimeSheet);

        //send email to supervisor ...

        return "redirect:/";
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
