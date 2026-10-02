package com.rachit.apextrade.auth.dto;

public record LoginResponse(
        String email,
        long userId,
        String token
) {}

