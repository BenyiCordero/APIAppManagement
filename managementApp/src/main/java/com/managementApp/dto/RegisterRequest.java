package com.managementApp.dto;

public record RegisterRequest(
        String name,
        String email,
        String password
){

}
