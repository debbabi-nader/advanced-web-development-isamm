package com.helpdesk.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.api.dto.AuthenticationTokenDTO;
import com.helpdesk.api.dto.SignInCredentialsDTO;
import com.helpdesk.api.dto.UserCreationDTO;
import com.helpdesk.api.dto.UserDTO;
import com.helpdesk.api.services.AuthenticationService;


@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-in")
    public AuthenticationTokenDTO signIn(@RequestBody @Valid SignInCredentialsDTO signInCredentials) {

        return new AuthenticationTokenDTO(
                this.authenticationService.signIn(signInCredentials.getEmail(), signInCredentials.getPassword()));

    }

    @PostMapping("/sign-up")
    public UserDTO signUp(@RequestBody @Valid UserCreationDTO userCreation) {
        return UserDTO.from(this.authenticationService.signUp(userCreation.toUserEntity()));
    }

}
