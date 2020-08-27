package com.denisenko.airlineticketsshop.model.entity;

import javax.validation.constraints.NotNull;


public abstract class User {

    private long id;

    private String firstName;

    private String lastName;

    private String patronymicName;

    private Login login;

    private UserType userType;

    protected User(){}
    public User(long id, @NotNull String firstName, @NotNull String lastName, String patronymicName, @NotNull Login login, @NotNull UserType userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.login = login;
        this.userType = userType;
    }

    public User(@NotNull String firstName, @NotNull String lastName, String patronymicName, @NotNull Login login, @NotNull UserType userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.login = login;
        this.userType = userType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public Login getLoginObject() {
        return login;
    }

    public void setLoginObject(Login login) {
        this.login = login;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public void setUserType(String userType) {
        this.userType = UserType.valueOf(userType);
    }
}
