//package com.example.demo.model;
//
//
//import com.example.demo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository)
//    {
//        this.userRepository = userRepository;
//    }
//
//    // returns currently logged in user
//    public User getUser(){
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        String currentUsername = authentication.getName();
//
//        User user = userRepository.findByUsername(currentUsername);
//
//        return user;
//    }
//}
