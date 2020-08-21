package com.denisenko.airlineticketsshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="LOGIN")
public class Login {
    @Id
    private long id;

    @NotNull
    private String nickname;

    @NotNull
    private String password;

    public Login(){}

    public Login(long id, @NotNull String nickname, @NotNull String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    public Login(@NotNull String nickname, @NotNull String password) {
        this(-1L, nickname, password);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return nickname.equals(login.nickname) &&
            password.equals(login.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, password);
    }

    @Override
    public String toString() {
        return "Login{" +
            "id=" + id +
            ", nickname='" + nickname + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
