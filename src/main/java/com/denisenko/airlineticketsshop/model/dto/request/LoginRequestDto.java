package com.denisenko.airlineticketsshop.model.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class LoginRequestDto {

    @NotNull(message = "Login mustn't be null")
    @NotEmpty (message = "Login mustn't be empty")
    private String login;

    @NotNull(message = "Login mustn't be null")
    @NotEmpty (message = "Login mustn't be empty")
    private String password;

    public LoginRequestDto() {}

    public LoginRequestDto(@NotNull String login, @NotNull String password) {
        this.login = login;
        this.password = password;
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
