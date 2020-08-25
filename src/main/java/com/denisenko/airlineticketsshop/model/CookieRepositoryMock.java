package com.denisenko.airlineticketsshop.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CookieRepositoryMock {

    private static Set<String> remoteRepositoryMock = new HashSet<>();

    public static Set<String> cookieRepository = Collections.synchronizedSet(remoteRepositoryMock);

}
