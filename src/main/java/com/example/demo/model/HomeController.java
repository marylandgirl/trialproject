package com.example.demo.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    UserService userService;


    @GetMapping("/")
    public String index(Model model) {

        if(userService.getUser() != null){
            model.addAttribute("loggedUser", userService.getUser());
        }
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
//        model.addAttribute("interests", interestRepository.findAll());

        if(userService.getUser() != null){
            model.addAttribute("loggedUser", userService.getUser());
        }
        return "login";
    }

    @RequestMapping("/register")
    public String getRegistrationForm(Model model) {
//        model.addAttribute("userForm", new UserForm());
        model.addAttribute("newUser", new User());
//        model.addAttribute("interests", interestRepository.findAll());

        if(userService.getUser() != null){
            model.addAttribute("loggedUser", userService.getUser());
        }
        return "register";
    }
}
