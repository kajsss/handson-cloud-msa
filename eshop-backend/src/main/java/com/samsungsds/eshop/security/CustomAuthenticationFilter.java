package com.samsungsds.eshop.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.samsungsds.eshop.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authRequest;
        User user = null;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            authRequest = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPw());
        } catch (InputNotFoundException | IOException e) {
                throw new InputNotFoundException();
        }

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
