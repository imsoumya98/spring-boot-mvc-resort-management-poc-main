package com.poc.resortmanagementsystem.service;

import com.poc.resortmanagementsystem.dto.UserRegistrationDto;
import com.poc.resortmanagementsystem.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

    List<UserRegistrationDto> findAllUsers();
}
