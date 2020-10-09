package com.denisenko.airlineticketsshop.model.entity;

import javax.persistence.*;
import javax.servlet.http.Cookie;
import java.io.Serializable;

@Entity
@Table(name = "cookie_login")
public class CookieLogin implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "common_seq_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="common_seq_id", sequenceName="SEQ_ID", allocationSize=1)
    private long id;


    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "cookie_name")),
        @AttributeOverride(name = "value", column = @Column(name = "cookie_value")),
        @AttributeOverride(name = "path", column = @Column(name = "cookie_path")),
        @AttributeOverride(name = "maxAge", column = @Column(name = "cookie_max_age")),
        @AttributeOverride(name = "secure", column = @Column(name = "cookie_is_sercure")),
        @AttributeOverride(name = "httpOnly", column = @Column(name = "cookie_is_http_only"))
    })
    @Embedded
    private AppCookie cookie;

    @ManyToOne
    @JoinColumn(name = "id_login")
    private Login login;

    public CookieLogin(){}

    public CookieLogin(AppCookie cookie) {
        this(0L, cookie, null);
    }

    public CookieLogin(AppCookie cookie, Login login) {
        this(0L, cookie, login);
    }

    public CookieLogin(long id, AppCookie cookie, Login login) {
        this.id = id;
        this.cookie = cookie;
        this.login = login;
    }

    public CookieLogin(Login login){
        this.login = login;
    }

    public CookieLogin(Cookie cookie) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AppCookie getCookie() {
        return cookie;
    }

    public void setCookie(AppCookie cookie) {
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
