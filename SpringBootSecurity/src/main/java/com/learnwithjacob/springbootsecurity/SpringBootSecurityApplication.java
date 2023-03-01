package com.learnwithjacob.springbootsecurity;

import com.learnwithjacob.springbootsecurity.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityApplication {

    public static void main(String[] args) {
        User.builder()
                .firstName("")
                .lastName("")
                .build();
        SpringApplication.run(SpringBootSecurityApplication.class, args);
    }
}
