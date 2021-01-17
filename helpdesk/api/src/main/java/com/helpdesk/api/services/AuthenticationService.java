package com.helpdesk.api.services;

import com.helpdesk.api.entities.UserEntity;


public interface AuthenticationService {

    public String signIn(String email, String password);

    public UserEntity signUp(UserEntity user);

}
