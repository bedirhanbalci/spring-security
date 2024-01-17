package com.security.basicauth.dtos;

import com.security.basicauth.models.Role;
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
