package com.denisenko.airlineticketsshop.model.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.servlet.http.Cookie;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cookie_login")
public class CookieLogin {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "common_seq_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="common_seq_id", sequenceName="SEQ_ID", allocationSize=1)
    private long id;

    @AttributeOverrides(
        @AttributeOverride(
            name = "name",
            column = @Column( name = "cookie_name" )
        )
    )
    private Cookie cookie;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Login login;

    public CookieLogin(){}

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

    public CookieLogin(Login login){
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
