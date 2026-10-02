package com.rachit.apextrade.auth.dto;

public record LoginRequest(
        String email,
        String password
) {}

