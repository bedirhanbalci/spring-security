package com.security.jwttoken.controllers;

import com.security.jwttoken.dtos.AuthRequest;
import com.security.jwttoken.dtos.CreateUserRequest;
import com.security.jwttoken.models.User;
import com.security.jwttoken.services.JwtService;
import com.security.jwttoken.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Hello World! this is sparta!";
    }

    @PostMapping("/addNewUser")
    public User addNewUser(CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.username());
        }
        log.info("invalid username " + authRequest.username());
        throw new UsernameNotFoundException("invalid username {}" + authRequest.username());

    }

    @GetMapping("/user")
    public String getUserString() {
        return "This is USER!";
    }

    @GetMapping("/admin")
    public String getAdminString() {
        return "This is ADMIN!";
    }
}
