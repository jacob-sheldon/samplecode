package com.learnwithjacob.springbootsecurity.auth;/*
 *@title AuthenticationResponse
 *@description
 *@author LearnWithJacob
 *@version 1.0
 *@create 3/2/23 11:39 PM
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
}
