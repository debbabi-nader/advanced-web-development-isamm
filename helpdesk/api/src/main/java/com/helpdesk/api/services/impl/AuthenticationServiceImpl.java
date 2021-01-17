package com.helpdesk.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpdesk.api.entities.UserEntity;
import com.helpdesk.api.exceptions.WrongPasswordException;
import com.helpdesk.api.services.AuthenticationService;
import com.helpdesk.api.services.UserService;
import com.helpdesk.api.utils.security.JWTUtil;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public AuthenticationServiceImpl(
            PasswordEncoder passwordEncoder,
            UserService userService) {

        this.passwordEncoder = passwordEncoder;
        this.userService = userService;

    }

    @Override
    public String signIn(String email, String password) {

        final UserEntity user = this.userService.findUserByEmail(email);

        if (!this.passwordEncoder.matches(password, user.getPassword()))
            throw new WrongPasswordException();

        return JWTUtil.createJWT(
                user.getId(),
                user.getFirstName() + ' ' + user.getLastName(),
                user.getEmail(),
                user.getUserType().getUserType());

    }

    @Override
    public UserEntity signUp(UserEntity user) {

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        return this.userService.createUser(user);

    }

}
