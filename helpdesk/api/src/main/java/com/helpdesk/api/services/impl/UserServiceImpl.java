package com.helpdesk.api.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.api.entities.UserEntity;
import com.helpdesk.api.exceptions.AccountNotFoundException;
import com.helpdesk.api.repositories.UserRepository;
import com.helpdesk.api.services.UserService;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findUserByEmail(String email) {

        return this.userRepository.findUserByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException(email));

    }

    @Override
    public UserEntity createUser(UserEntity user) {

        user.setId(UUID.randomUUID().toString());

        return this.userRepository.saveAndFlush(user);

    }

}
