package com.jacob.controller;/*
 *@title UserController
 *@description
 *@author LearnWithJacob
 *@version 1.0
 *@create 7/12/23 11:34 PM
 */

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @GetMapping("/")
    public String hello() {
        return "User level access";
    }
}
