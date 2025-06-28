package com.managementApp.dto;

public record AuthRequest(
        String email,
        String password
) {
}
