package com.spring.bike.bikeshopapp.security.auth;

import com.spring.bike.bikeshopapp.model.Role;
import com.spring.bike.bikeshopapp.model.User;
import com.spring.bike.bikeshopapp.security.config.JwtService;
import com.spring.bike.bikeshopapp.service.RoleService;
import com.spring.bike.bikeshopapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Role role = roleService.readRole("ROLE_USER").orElseThrow(NoSuchElementException::new);
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .loginName(request.getLoginName())
                .password(passwordEncoder.encode(request.getPassword()))
                .address(request.getAddress())
                .phone(request.getPhone())
                .role(role)
                .build();
        userService.addUser(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userService.readUser(request.getUsername()).get();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}