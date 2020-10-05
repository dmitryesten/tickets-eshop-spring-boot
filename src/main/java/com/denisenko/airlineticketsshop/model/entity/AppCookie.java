package com.denisenko.airlineticketsshop.model.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.servlet.http.Cookie;
import java.io.Serializable;

@Embeddable
@Access(AccessType.PROPERTY)
public class AppCookie extends Cookie implements Serializable  {
    public AppCookie(String name, String value) {
        super(name, value);
    }
}
