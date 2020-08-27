package com.denisenko.airlineticketsshop.model.entity;

public enum UserType {
    ADMIN("ADMIN"), CLIENT("CLIENT");

    private String value;

    UserType(String typeEntity) {
        this.value = typeEntity;
    }

    public String getTypeEntityString() {
        return value;
    }
}
