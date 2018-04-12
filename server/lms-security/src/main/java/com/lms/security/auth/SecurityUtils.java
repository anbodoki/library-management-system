package com.lms.security.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityUtils {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
}
