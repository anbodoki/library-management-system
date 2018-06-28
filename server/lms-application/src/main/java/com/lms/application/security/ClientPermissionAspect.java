package com.lms.application.security;

import com.lms.application.exception.LMSAccessDeniedException;
import com.lms.security.auth.AuthType;
import com.lms.security.auth.CustomGrantedAuthority;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Aspect
@Component
public class ClientPermissionAspect {

    @Before("@annotation(ClientPermissionCheck)")
    public void check(JoinPoint joinPoint) throws Throwable {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        CustomGrantedAuthority grantedAuthority = (CustomGrantedAuthority) new ArrayList<>(auth.getAuthorities()).get(0);
        if (grantedAuthority.isActive()) {
            if (grantedAuthority.getAuthType() != AuthType.CLIENT) {
                throw new LMSAccessDeniedException("Access Denied");
            }
        } else {
            throw new LMSAccessDeniedException("Not Active User");
        }
    }
}
