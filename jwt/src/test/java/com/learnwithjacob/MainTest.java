package com.learnwithjacob;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

/*
 *@title MainTest
 *@description
 *@author LearnWithJacob
 *@version 1.0
 *@create 2/26/23 10:15 AM
 */
class MainTest {
    @Test
    public void testMain() {
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws1 =
                Jwts.builder().setSubject("Joe").signWith(secretKey).compact();
        assert Jwts.parserBuilder().setSigningKey(secretKey).build().
                parseClaimsJws(jws1).getBody().getSubject().equals("Joe");
    }
}