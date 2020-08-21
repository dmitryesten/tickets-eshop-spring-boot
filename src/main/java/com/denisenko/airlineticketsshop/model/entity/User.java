package com.denisenko.airlineticketsshop.model.entity;

import com.denisenko.airlineticketsshop.model.Login;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


public abstract class User {

    private long id;

    private String firstName;

    private String lastName;

    private String patronymicName;

    private Login login;

    private EntitySystem userType;

    protected User(){}
    public User(long id, @NotNull String firstName, @NotNull String lastName, String patronymicName, @NotNull Login login, @NotNull EntitySystem userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.login = login;
        this.userType = userType;
    }

    public User(@NotNull String firstName, @NotNull String lastName, String patronymicName, @NotNull Login login, @NotNull EntitySystem userType) {
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

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public EntitySystem getUserType() {
        return userType;
    }

    public void setUserType(EntitySystem userType) {
        this.userType = userType;
    }
}
