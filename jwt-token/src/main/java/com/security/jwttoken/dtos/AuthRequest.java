package com.security.jwttoken.dtos;

public record AuthRequest(
        String username,
        String password
) {
}
