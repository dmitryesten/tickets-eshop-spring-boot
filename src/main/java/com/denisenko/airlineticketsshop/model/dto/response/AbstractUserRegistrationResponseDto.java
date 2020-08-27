package com.denisenko.airlineticketsshop.model.dto.response;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public abstract class AbstractUserRegistrationResponseDto {

    @NotNull(message = "ID may not be null")
    @NotEmpty (message = "ID may not be empty")
    private long id;

    @NotNull(message = "FirstName may not be null")
    @NotEmpty (message = "FirstName may not be empty")
    private String firstName;

    @NotNull(message = "LastName may not be null")
    @NotEmpty (message = "LastName may not be empty")
    private String lastName;
    private String patronymic;

    @NotNull(message = "UserType may not be null")
    @NotEmpty (message = "UserType may not be empty")
    private String userType;


    public AbstractUserRegistrationResponseDto(){}

    public AbstractUserRegistrationResponseDto(@NotNull @NotEmpty long id, @NotNull @NotEmpty String firstName, @NotNull @NotEmpty String lastName, String patronymic, @NotNull @NotEmpty String userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
