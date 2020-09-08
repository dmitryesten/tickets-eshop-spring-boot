package com.denisenko.airlineticketsshop.controller.factory;

import javax.servlet.http.Cookie;
import java.util.UUID;

public class CookieFactory {

    private CookieFactory(){}

    public static Cookie getCookie(String cookieNameValue){
        StringBuilder builder = new StringBuilder(cookieNameValue);
        String name = builder.substring(0, builder.indexOf("="));
        String value = builder.substring(builder.indexOf("=") + 1);

        Cookie cookie  = new Cookie(name, value);
        cookie.setMaxAge(300);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        return cookie;
    }

    public static Cookie getCookie(){
        return getCookie( "JAVASESSIONID="+UUID.randomUUID().toString());
    }

    public static Cookie getDeletedCookie(String cookieNameValue){
        StringBuilder builder = new StringBuilder(cookieNameValue);
        String name = builder.substring(0, builder.indexOf("="));
        String value = builder.substring(builder.indexOf("=") + 1);

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        return cookie;
    }


}
