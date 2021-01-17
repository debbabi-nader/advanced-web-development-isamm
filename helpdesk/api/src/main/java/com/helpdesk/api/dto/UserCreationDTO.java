package com.helpdesk.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.helpdesk.api.entities.UserEntity;
import com.helpdesk.api.enumerations.UserTypeEnum;
import com.helpdesk.api.utils.validation.constraints.EmailConstraint;
import com.helpdesk.api.utils.validation.constraints.PasswordConfirmationConstraint;
import com.helpdesk.api.utils.validation.constraints.PasswordConstraint;


@PasswordConfirmationConstraint
public final class UserCreationDTO implements Serializable {

    private static final long serialVersionUID = 5366057497857160950L;

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;

    @EmailConstraint
    private final String email;

    @NotNull
    private final UserTypeEnum userType;

    @NotNull
    @PasswordConstraint
    private final String password;

    @NotNull
    private final String passwordConfirmation;

    public UserCreationDTO(
            String firstName,
            String lastName,
            String email,
            UserTypeEnum userType,
            String password,
            String passwordConfirmation) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;

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

    public String getPassword() {
        return this.password;
    }

    public String getPasswordConfirmation() {
        return this.passwordConfirmation;
    }

    public UserEntity toUserEntity() {

        return new UserEntity(
                this.firstName,
                this.lastName,
                this.email,
                this.password,
                this.userType);

    }

}
