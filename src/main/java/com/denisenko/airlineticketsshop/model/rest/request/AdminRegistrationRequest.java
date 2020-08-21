package com.denisenko.airlineticketsshop.model.rest.request;

import javax.validation.constraints.NotNull;

public class AdminRegistrationRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private String patronymic;

    @NotNull
    private String position;
    @NotNull
    private String login;
    @NotNull
    private String password;

    public AdminRegistrationRequest(@NotNull String firstName, @NotNull String lastName, String patronymic, @NotNull String position, @NotNull String login, @NotNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.position = position;
        this.login = login;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fistName) {
        this.firstName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminRegistrationRequest{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", patronymic='" + patronymic + '\'' +
            ", position='" + position + '\'' +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
