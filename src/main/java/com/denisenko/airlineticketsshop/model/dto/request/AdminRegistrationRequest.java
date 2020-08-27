package com.denisenko.airlineticketsshop.model.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdminRegistrationRequest {

    @NotNull(message = "FirstName may not be null")
    @NotEmpty (message = "FirstName may not be empty")
    private String firstName;

    @NotNull(message = "LastName may not be null")
    @NotEmpty (message = "LastName may not be empty")
    private String lastName;

    @NotNull(message = "Patronymic may not be null")
    private String patronymic;

    @NotNull(message = "Position may not be null")
    @NotEmpty (message = "Position may not be empty")
    private String position;

    @NotNull(message = "Login may not be null")
    @NotEmpty (message = "Login may not be empty")
    private String login;

    @NotNull(message = "Password may not be null")
    @NotEmpty (message = "Password may not be empty")
    private String password;

    public AdminRegistrationRequest() {}

    public AdminRegistrationRequest(@NotNull @NotEmpty String firstName, @NotNull @NotEmpty String lastName, String patronymic, @NotNull @NotEmpty String position, @NotNull @NotEmpty String login, @NotNull @NotEmpty String password) {
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
