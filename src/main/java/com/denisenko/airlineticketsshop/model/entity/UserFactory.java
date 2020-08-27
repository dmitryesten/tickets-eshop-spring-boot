package com.denisenko.airlineticketsshop.model.entity;

import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;

public class UserFactory {

    public static User getFactory(UserType userType) throws InvalidObjectFactoryException {
        switch (userType){
            case ADMIN: return new Administrator();
            case CLIENT: return new Client();
            default: throw new InvalidObjectFactoryException("INVALID_OBJECT_FACTORY", "Для текущего типа UserType фабрика не может создать объект");
        }
    }

    public static User getFactory(String userType) throws InvalidObjectFactoryException {
        return getFactory(UserType.valueOf(userType));
    }

}
