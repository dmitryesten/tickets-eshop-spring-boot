package com.denisenko.airlineticketsshop.model.rest.response;

import javax.validation.constraints.NotNull;

public class ClientRegistrationResponse extends UserAbstractResponse {
    @NotNull
    private long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String patronymic;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String userType;

    public ClientRegistrationResponse(@NotNull long id, @NotNull String firstName, @NotNull String lastName, String patronymic, @NotNull String email, @NotNull String phone, @NotNull String userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
