package com.learnwithjacob;/*
 *@title Main
 *@description
 *@author $USER
 *@version 1.0
 *@create 2023-02-26
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.beans.Encoder;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String jws1 =
                Jwts.builder()
                        .setSubject("Joe")
                        .signWith(secretKey)
                        .compact();
        System.out.println("jws = " + jws1);

        Jws<Claims> claim = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jws1);
        System.out.println("claim = " + claim);
    }
}