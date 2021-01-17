package com.helpdesk.api.services;

import com.helpdesk.api.entities.UserEntity;


public interface UserService {

    public UserEntity findUserByEmail(String email);

    public UserEntity createUser(UserEntity user);

}
