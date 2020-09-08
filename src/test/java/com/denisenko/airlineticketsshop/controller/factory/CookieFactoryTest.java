package com.denisenko.airlineticketsshop.controller.factory;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CookieFactoryTest {

    private final String cookieNameValue = "JAVASESSIONID=13";

    @Test
    void getCookie() {
        Assert.assertNotNull(CookieFactory.getCookie());
        Assert.assertEquals(300, CookieFactory.getCookie().getMaxAge());
    }

    @Test
    void getDeletedCookie() {
        Assertions.assertThat(CookieFactory.getCookie()).isNotNull();
        Assertions.assertThat(CookieFactory.getDeletedCookie(cookieNameValue).getMaxAge()).isEqualTo(0);
    }

}