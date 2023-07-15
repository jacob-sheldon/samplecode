package com.jacob.controller;/*
 *@title AdminController
 *@description
 *@author LearnWithJacob
 *@version 1.0
 *@create 7/12/23 11:33 PM
 */

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @GetMapping("/")
    public String hello() {
        return "Admin level access";
    }
}
