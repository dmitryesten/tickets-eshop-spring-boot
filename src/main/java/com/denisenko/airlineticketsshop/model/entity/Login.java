package com.denisenko.airlineticketsshop.model.entity;

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
    private String login;

    @NotNull
    private String password;

    public Login(){}

    public Login(long id, @NotNull String login, @NotNull String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Login(@NotNull String login, @NotNull String password) {
        this(-1L, login, password);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return login.equals(login.login) &&
            password.equals(login.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "LoginController{" +
            "id=" + id +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
