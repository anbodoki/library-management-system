package com.lms.security.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.lms.client.client.service.ClientService;
import com.lms.client.client.storage.ClientHelper;
import com.lms.common.dto.client.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

@Component
public class TokenAuthenticationFilter extends GenericFilterBean {

    private final ClientService clientService;
    private static final HttpTransport TRANSPORT = new NetHttpTransport();

    @Autowired
    public TokenAuthenticationFilter(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;

        final String accessToken = httpRequest.getHeader(GoogleConstants.HEADER_STRING);
        if (accessToken != null) {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(TRANSPORT, JacksonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(GoogleConstants.GOOGLE_APP_ID))
                    .build();
            try {
                GoogleIdToken idToken = verifier.verify(accessToken);
                if (idToken != null) {
                    Payload payload = idToken.getPayload();
                    if (!payload.getHostedDomain().equals(GoogleConstants.FREEUNI_DOMAIN)
                            && !payload.getHostedDomain().equals(GoogleConstants.AGRUNI_DOMAIN)) {
                        SecurityContextHolder.clearContext();
                        chain.doFilter(request, response);
                        return;
                    }
                    String userId = payload.getSubject();
                    System.out.println("User ID: " + userId);
                    String email = payload.getEmail();
                    ClientDTO client = clientService.getByEmail(email);
                    if (client == null) {
                        String firstName = (String) payload.get("given_name");
                        String imageURL = (String) payload.get("picture");
                        String lastName = (String) payload.get("family_name");
                        client = new ClientDTO();
                        client.setImageUrl(imageURL);
                        client.setFirstName(firstName);
                        client.setLastName(lastName);
                        client.setEmail(email);
                        client.setActive(true);
                        client = clientService.save(client);
                    }
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    if (authentication == null) {
                        CustomGrantedAuthority role = new CustomGrantedAuthority(true);
                        role.setAuthType(AuthType.CLIENT);
                        role.setAuthority("ROLE_" + AuthType.CLIENT.name());
                        role.setClient(ClientHelper.toEntity(client));
                        authentication = new UsernamePasswordAuthenticationToken(client.getEmail(), null, Collections.singletonList(role));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } else {
                    SecurityContextHolder.clearContext();
                }
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                chain.doFilter(request, response);
            }
        }
        chain.doFilter(request, response);
    }
}