package com.denisenko.airlineticketsshop.model.entity;

public enum EntitySystem {
    ADMIN("admin"), CLIENT("client");

    private String value;

    EntitySystem(String typeEntity) {
        this.value = typeEntity;
    }

    public String getTypeEntityString() {
        return value;
    }
}
