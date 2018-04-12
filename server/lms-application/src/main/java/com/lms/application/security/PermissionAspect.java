package com.lms.application.security;

import com.lms.application.exception.LMSAccessDeniedException;
import com.lms.security.auth.CustomGrantedAuthority;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Aspect
@Component
public class PermissionAspect {

    @Before("@annotation(com.lms.application.security.PermissionCheck)")
    public void check(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String requestedUrl = String.valueOf(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE));
        CustomGrantedAuthority grantedAuthority = (CustomGrantedAuthority) new ArrayList<>(auth.getAuthorities()).get(0);
        if (grantedAuthority.isActive()) {
            if (!grantedAuthority.getPermissions().contains(requestedUrl)) {
                throw new LMSAccessDeniedException("Access Denied");
            }
        } else {
            throw new LMSAccessDeniedException("Not Active User");
        }
    }
}
