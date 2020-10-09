package com.denisenko.airlineticketsshop.model.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.servlet.http.Cookie;
import java.io.Serializable;

@Embeddable
@Access(AccessType.PROPERTY)
public class AppCookie extends Cookie implements Serializable  {

    private String name;

    public AppCookie(String name, String value) {
        super(name, value);
        this.name = name;
    }


    @Override
    @Column(name = "cookie_name")
    public String getName() {
        this.name = super.getName();
        return super.getName();
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    @Column(name = "cookie_value")
    public String getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(String newValue) {
        super.setValue(newValue);
    }

    @Override
    @Column(name = "cookie_path")
    public String getPath() {
        return super.getPath();
    }

    @Override
    public void setPath(String uri) {
        super.setPath(uri);
    }

    @Override
    @Column(name = "cookie_max_age")
    public int getMaxAge() {
        return super.getMaxAge();
    }

    @Override
    public void setMaxAge(int expiry) {
        super.setMaxAge(expiry);
    }

    @Override
    @Column(name = "cookie_is_sercure")
    public boolean getSecure() {
        return super.getSecure();
    }

    @Override
    public void setSecure(boolean flag) {
        super.setSecure(flag);
    }

    @Override
    @Column(name = "cookie_is_http_only")
    public boolean isHttpOnly() {
        return super.isHttpOnly();
    }

    @Override
    public void setHttpOnly(boolean httpOnly) {
        super.setHttpOnly(httpOnly);
    }
}
