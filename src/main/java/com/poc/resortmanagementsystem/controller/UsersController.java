package com.poc.resortmanagementsystem.controller;

import com.poc.resortmanagementsystem.dto.UserRegistrationDto;
import com.poc.resortmanagementsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserRegistrationDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}