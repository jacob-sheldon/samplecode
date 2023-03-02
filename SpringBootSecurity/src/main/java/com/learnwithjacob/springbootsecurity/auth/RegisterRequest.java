package com.learnwithjacob.springbootsecurity.auth;/*
 *@title RegisterRequest
 *@description
 *@author LearnWithJacob
 *@version 1.0
 *@create 3/2/23 11:40 PM
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
