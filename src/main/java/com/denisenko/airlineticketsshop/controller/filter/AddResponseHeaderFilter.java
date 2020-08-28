package com.denisenko.airlineticketsshop.controller.filter;

import com.denisenko.airlineticketsshop.service.exception.CookieNotFoundException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@WebFilter("/*")
public class AddResponseHeaderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if(Optional.ofNullable(httpServletRequest.getCookies()).isPresent()){
            System.out.println("Куки есть в загаловке запроса");
            Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> cookie.getName().equals("JAVASESSIONID"))
                .forEach(cookie -> {
                    cookie.setMaxAge(5);
                    System.out.println(cookie.getName() + " " + cookie.getValue() + " время " + cookie.getMaxAge());
                }
                );

        } //else throw new CookieNotFoundException("В загаловке запроса не обнаружено значение cookie по ключу JAVASESSIONID");
        /*else {
            httpServletResponse.setHeader("TEST_INFORMATION-FOR_HEADER","Key--007");
            String valueCookie = UUID.randomUUID().toString();
            httpServletResponse.addCookie(new Cookie("JAVASESSIONID", valueCookie));

        }*/
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
