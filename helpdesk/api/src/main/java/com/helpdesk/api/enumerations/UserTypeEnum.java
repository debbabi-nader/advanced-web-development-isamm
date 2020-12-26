package com.helpdesk.api.enumerations;


public enum UserTypeEnum {

    CLIENT("CLIENT"),
    OPERATOR("OPERATOR");

    private final String userType;

    private UserTypeEnum(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return this.userType;
    }

}
