package com.denisenko.airlineticketsshop.model.entity;

import javax.persistence.Entity;
import javax.servlet.http.Cookie;
import javax.validation.constraints.NotNull;


public class CookieLogin  {

    private long id;

    private Cookie cookie;

    private Login login;

    public CookieLogin(Cookie cookie) {
        this(0L, cookie, null);
    }

    public CookieLogin(Cookie cookie, Login login) {
        this(0L, cookie, login);
    }

    public CookieLogin(long id, Cookie cookie, Login login) {
        this.id = id;
        this.cookie = cookie;
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getCookieString(){
        String secure = cookie.getSecure() ?";Secure":"";
        String httpOnly = cookie.isHttpOnly() ?";HttpOnly":"";
        return cookie.getName() + "="+ cookie.getValue() +"; Path="+ cookie.getPath() + "; Max-Age="+cookie.getMaxAge()+ secure+httpOnly;
    }

}
