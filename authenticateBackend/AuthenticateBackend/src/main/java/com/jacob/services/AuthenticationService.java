package com.jacob.services;

import com.jacob.models.ApplicationUser;
import com.jacob.models.LoginResponseDTO;
import com.jacob.models.Role;
import com.jacob.repository.RoleRepository;
import com.jacob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.HashSet;
import java.util.Set;

/*
 *@title AuthenticationService
 *@description
 *@author Jacob Sheldon
 *@version
 *@create 7/15/23 3:28 PM
 */
@Service
@Transactional
public class AuthenticationService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

    public AuthenticationService(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public ApplicationUser registerUser(String username, String password) {
        String encodePassword = passwordEncoder.encode(password);
        Role role = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(role);

        return userRepository.save(new ApplicationUser(0, username,
                encodePassword, authorities));
    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication auth =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(username, password)
                    );
            String token = tokenService.generateJwt(auth);
            System.out.println(userRepository.findByUsername(username));
            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
        } catch (Exception e) {
            return new LoginResponseDTO(null, "");
        }
    }
}
