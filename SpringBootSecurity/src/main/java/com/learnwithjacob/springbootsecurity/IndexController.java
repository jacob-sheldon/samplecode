package com.learnwithjacob.springbootsecurity;/*
 *@title IndexController
 *@description
 *@author LearnWithJacob
 *@version 1.0
 *@create 2/26/23 9:55 PM
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "Hello, Spring Security";
    }
}
