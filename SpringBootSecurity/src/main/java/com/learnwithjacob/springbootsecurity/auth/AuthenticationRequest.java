package com.learnwithjacob.springbootsecurity.auth;/*
 *@title AuthenticationRequest
 *@description
 *@author LearnWithJacob
 *@version 1.0
 *@create 3/2/23 11:41 PM
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}
