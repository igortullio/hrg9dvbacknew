package com.igortullio.hering.core.domain;


import com.igortullio.hering.core.domain.enumeration.UserField;

public class Precondition {

    private UserField userField;
    private String condition;

    public Precondition(UserField userField, String condition) {
        this.userField = userField;
        this.condition = condition;
    }

    public UserField getUserField() {
        return userField;
    }

    public void setUserField(UserField userField) {
        this.userField = userField;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}
