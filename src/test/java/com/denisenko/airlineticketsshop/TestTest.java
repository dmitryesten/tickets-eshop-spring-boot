package com.denisenko.airlineticketsshop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestTest {

    private final String s = "123";

    @Test
    public void tets() {
        String name = "Hello World";
        String name2 = new String("Hello World").intern();
        System.out.println(name == name2);

        Object object = new Object();
    }

}

