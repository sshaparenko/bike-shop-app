package com.spring.bike.bikeshopapp.security.auth;

import com.spring.bike.bikeshopapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        if (userService.readUser(request.getLoginName()).isPresent()) return new ResponseEntity<>(new AuthenticationResponse("login name is already used"), HttpStatus.CONFLICT);
        try {
            return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new AuthenticationResponse("invalid role"), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        if (userService.readUser(request.getUsername()).isEmpty()) return new ResponseEntity<>(new AuthenticationResponse("username is invalid"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(authService.authenticate(request), HttpStatus.OK);
    }
}