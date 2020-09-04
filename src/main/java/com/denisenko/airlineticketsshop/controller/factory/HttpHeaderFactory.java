package com.denisenko.airlineticketsshop.controller.factory;

import com.denisenko.airlineticketsshop.model.entity.CookieLogin;
import org.springframework.http.HttpHeaders;


public class HttpHeaderFactory {

    private HttpHeaderFactory(){};

    public static HttpHeaders getHttpHeader(CookieLogin cookieLogin){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Set-Cookie", cookieLogin.getCookieString());
        return httpHeaders;
    }

}
