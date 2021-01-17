package com.helpdesk.api.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.auth0.jwt.exceptions.JWTVerificationException;

import com.helpdesk.api.dto.JWTAuthenticationTokenDTO;
import com.helpdesk.api.dto.UserAuthenticationTokenDTO;
import com.helpdesk.api.utils.security.JWTUtil;


@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        LOGGER.debug("JWTAuthenticationProvider authenticate method is invoked...");

        if (!this.supports(authentication.getClass()))
            return null;

        final JWTAuthenticationTokenDTO jwtAuthenticationToken = (JWTAuthenticationTokenDTO) authentication;
        final String jwt = (String) jwtAuthenticationToken.getCredentials();

        UserAuthenticationTokenDTO userAuthenticationToken;

        try {
            userAuthenticationToken = JWTUtil.decodeJWT(jwt);
        } catch (JWTVerificationException e) {
            throw new BadCredentialsException(e.getMessage(), e);
        }

        return userAuthenticationToken;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationTokenDTO.class.isAssignableFrom(authentication);
    }

}
