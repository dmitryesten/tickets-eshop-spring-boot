package com.denisenko.airlineticketsshop.model.rest.request;

import javax.validation.constraints.NotNull;

public class LoginRequest {

    private long id;
    @NotNull
    private String nickname;
    @NotNull
    private String password;

    public LoginRequest() {}

    public LoginRequest(@NotNull String nickname, @NotNull String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public LoginRequest(long id, @NotNull String nickname, @NotNull String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
