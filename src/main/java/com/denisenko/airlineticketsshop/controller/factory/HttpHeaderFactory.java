package com.denisenko.airlineticketsshop.controller.factory;

import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.springframework.http.HttpHeaders;

import java.util.UUID;

public class HttpHeaderFactory {

    private HttpHeaderFactory(){};

    public static HttpHeaders getHttpHeader(){
        return HttpHeaderFactory.getHttpHeader("JAVASESSIONID", UUID.randomUUID().toString());
    }

    public static HttpHeaders getHttpHeader(String nameCookie, String valueCookie){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(nameCookie, valueCookie);
        return httpHeaders;
    }


}
