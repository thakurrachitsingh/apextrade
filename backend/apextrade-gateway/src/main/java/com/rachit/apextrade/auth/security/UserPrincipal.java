package com.rachit.apextrade.auth.security;

public record UserPrincipal(
        long userId,
        String email
) {}

