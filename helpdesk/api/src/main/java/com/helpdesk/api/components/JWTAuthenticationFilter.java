package com.helpdesk.api.components;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.helpdesk.api.dto.JWTAuthenticationTokenDTO;
import com.helpdesk.api.dto.UserAuthenticationTokenDTO;


@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String AUTHORIZATION_BEARER_TOKEN_PREFIX = "Bearer ";

    private final AuthenticationManager authenticationManager;

    @Autowired
    public JWTAuthenticationFilter(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        LOGGER.debug("Request is being proccessed by JWTAuthenticationFilter...");

        final String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER_NAME);
        String jwt = "";

        if (authorizationHeader != null)
            jwt = authorizationHeader.replace(AUTHORIZATION_BEARER_TOKEN_PREFIX, "");

        if (jwt.equals("")) {
            LOGGER.debug("Request is not meant to be processed by the JWTAuthenticationFilter as it lacks a JWT");
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }

        UserAuthenticationTokenDTO userAuthenticationToken;

        try {
            userAuthenticationToken = (UserAuthenticationTokenDTO) this.authenticationManager.authenticate(new JWTAuthenticationTokenDTO(jwt));
        } catch (AuthenticationException authenticationException) {
            LOGGER.debug("JWTAuthenticationFilter failed to authenticate the request!", authenticationException);
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }

        LOGGER.debug("JWTAuthenticationFilter authenticated the request successfully!");

        SecurityContextHolder.getContext().setAuthentication(userAuthenticationToken);
        filterChain.doFilter(request, response);

    }

}
