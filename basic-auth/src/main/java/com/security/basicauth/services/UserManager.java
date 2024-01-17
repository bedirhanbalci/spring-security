package com.security.basicauth.services;

import com.security.basicauth.dtos.CreateUserRequest;
import com.security.basicauth.models.User;
import com.security.basicauth.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserManager {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<User> getByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(CreateUserRequest createUserRequest) {
        User newUser = User.builder()
                .name(createUserRequest.name())
                .username(createUserRequest.username())
                .password(bCryptPasswordEncoder.encode(createUserRequest.password()))
                .authorities(createUserRequest.authorities())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .build();

        return userRepository.save(newUser);
    }

}
