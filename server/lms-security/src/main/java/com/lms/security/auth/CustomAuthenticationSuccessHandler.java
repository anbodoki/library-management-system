package com.lms.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ServletContext servletContext;

    @Autowired
    public CustomAuthenticationSuccessHandler(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomGrantedAuthority authority = ((CustomGrantedAuthority) new ArrayList<>(authentication.getAuthorities()).get(0));
        AuthType authType = authority.getAuthType();
        switch (authType) {
            case CLIENT:
                String token = request.getHeader(GoogleConstants.HEADER_STRING);
                response.setContentType("text/plain");
                response.setContentLength(token.length());
                PrintWriter out = response.getWriter();
                out.println(token);
                out.close();
                out.flush();
                break;
            case SYSTEM_USER:
                response.sendRedirect(servletContext.getContextPath().length() == 0 ? "/" : servletContext.getContextPath());
                break;
        }
    }
}