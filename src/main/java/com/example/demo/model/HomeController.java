package com.example.demo.model;



import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;



    @GetMapping("/")
    public String homePage(Model model) {
    model.addAttribute("allUsers", userRepository.findAll());
        if(userService.getUser() != null){
            model.addAttribute("loggedUser", userService.getUser());
        }
        // get employee's role/roles by username
        String username = userService.getUser().getUsername();
        Set<Role>roles = new HashSet<>();
        roles = roleRepository.findByUsername(username);

        // add the roles found above and send it to the form
        model.addAttribute("roles", roles);

        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String getRegistrationForm(Model model) {
        model.addAttribute("newUser", new User());
        if(userService.getUser() != null){
            model.addAttribute("loggedUser", userService.getUser());
        }
        return "register";
    }

    @RequestMapping("/admin-employee")
    public String adminPageListOfEmployee(Model model) {
//        model.addAttribute("newUser", new User());
        model.addAttribute("allUsers", userRepository.findAll());
        if(userService.getUser() != null){
            model.addAttribute("loggedUser", userService.getUser());
        }
        return "admin-list";

    }


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
        User tempUser;
        tempUser = userRepository.findById(id).get();
        if(tempUser.isEnabled()){
            tempUser.setEnabled(false);
        }
        else{
            tempUser.setEnabled(true);
        }
        userRepository.save(tempUser);
        model.addAttribute("allUsers", userRepository.findAll());
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        if(userService.getUser() != null){
            model.addAttribute("loggedUser", userService.getUser());
        }

        return "redirect:/";
    }


}
