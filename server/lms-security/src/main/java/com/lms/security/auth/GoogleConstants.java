package com.lms.security.auth;

public class GoogleConstants {

    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final long DISTRIBUTION_EXPIRATION_TIME = 86_400_000; // 1 day
    public static final String SECRET = "aTNXkVG94M3ICl0uAm0l0ta8wIQQJc";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Token";
}
