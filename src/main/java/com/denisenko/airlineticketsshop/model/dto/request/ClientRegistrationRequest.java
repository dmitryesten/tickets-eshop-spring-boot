package com.denisenko.airlineticketsshop.model.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ClientRegistrationRequest {

    @NotNull(message = "FirstName may not be null")
    @NotEmpty(message = "FirstName may not be empty")
    private String firstName;

    @NotNull(message = "LastName may not be null")
    @NotEmpty (message = "LastName may not be empty")
    private String lastName;

    @NotNull(message = "Patronymic may not be null")
    private String patronymic;

    @NotNull(message = "Email may not be null")
    @NotEmpty (message = "Email may not be empty")
    @Email
    private String email;

    @NotNull(message = "Phone may not be null")
    @NotEmpty (message = "Phone may not be empty")
    private String phone;

    @NotNull(message = "Login may not be null")
    @NotEmpty (message = "Login may not be empty")
    private String login;

    @NotNull(message = "Password may not be null")
    @NotEmpty (message = "Password may not be empty")
    private String password;

    public ClientRegistrationRequest(){}

    public ClientRegistrationRequest(@NotNull String firstName, @NotNull String lastName, @NotNull String patronymic, @NotNull String email, @NotNull String phone, @NotNull String login, @NotNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.login = login;
        this.password = password;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
