package com.jacob.controller;

import com.jacob.models.ApplicationUser;
import com.jacob.models.LoginResponseDTO;
import com.jacob.models.RegisterDTO;
import com.jacob.services.AuthenticationService;
import org.springframework.web.bind.annotation.*;

/*
 *@title AuthenticationController
 *@description AuthenticationController used for User Register and User Login etc.
 *@author Jacob Sheldon
 *@version 1.0
 *@create 7/15/23 3:43 PM
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegisterDTO body) {
        return authenticationService.registerUser(body.getUsername(),
                body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegisterDTO body) {
        return authenticationService.loginUser(body.getUsername(),
                body.getPassword());
    }
}
