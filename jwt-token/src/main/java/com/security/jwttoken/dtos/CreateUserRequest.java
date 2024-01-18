package com.security.jwttoken.dtos;

import com.security.jwttoken.models.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateUserRequest(

        String name,
        String username,
        String password,
        Set<Role> authorities
) {
}
