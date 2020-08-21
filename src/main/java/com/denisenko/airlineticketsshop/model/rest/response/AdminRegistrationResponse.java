package com.denisenko.airlineticketsshop.model.rest.response;

import javax.validation.constraints.NotNull;

public class AdminRegistrationResponse {

    @NotNull
    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String patronymic;
    @NotNull
    private String position;
    @NotNull
    private String userType;


    public AdminRegistrationResponse(@NotNull long id, @NotNull String firstName, @NotNull String lastName, String patronymic, @NotNull String position, @NotNull String userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "AdminRegistrationResponse{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", patronymic='" + patronymic + '\'' +
            ", position='" + position + '\'' +
            ", userType='" + userType + '\'' +
            '}';
    }
}
