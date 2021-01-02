package com.helpdesk.api.dto;

import java.io.Serializable;

import com.helpdesk.api.entities.UserEntity;
import com.helpdesk.api.enumerations.UserTypeEnum;


public final class UserDTO implements Serializable {

    private static final long serialVersionUID = 7444790264691006812L;

    private final String id;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final UserTypeEnum userType;

    private UserDTO(
            String id,
            String firstName,
            String lastName,
            String email,
            UserTypeEnum userType) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;

    }

    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public UserTypeEnum getUserType() {
        return this.userType;
    }

    public static UserDTO from(UserEntity userEntity) {

        return new UserDTO(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getUserType());

    }

}
