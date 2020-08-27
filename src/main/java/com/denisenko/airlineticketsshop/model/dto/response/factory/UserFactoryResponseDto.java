package com.denisenko.airlineticketsshop.model.dto.response.factory;

import com.denisenko.airlineticketsshop.model.dto.response.AbstractUserRegistrationResponseDto;
import com.denisenko.airlineticketsshop.model.dto.response.AdminRegistrationResponse;
import com.denisenko.airlineticketsshop.model.dto.response.ClientRegistrationResponse;
import com.denisenko.airlineticketsshop.model.entity.Administrator;
import com.denisenko.airlineticketsshop.model.entity.Client;
import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import org.modelmapper.ModelMapper;

public class UserFactoryResponseDto {

    public static AbstractUserRegistrationResponseDto getUserResponseDto(User user) throws InvalidObjectFactoryException {
        if(user instanceof Administrator) {
            return AdminRegistrationResponse.builder()
                    .setId(user.getId())
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setPatronymicName(user.getPatronymicName())
                    .setPosition(((Administrator) user).getPosition())
                    .setUserType(user.getUserType())
                    .build();
        } else if(user instanceof Client){
            return ClientRegistrationResponse.builder()
                    .setId(user.getId())
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setPatronymicName(user.getPatronymicName())
                    .setUserType(user.getUserType())
                    .setEmail(((Client) user).getEmail())
                    .setPhone(((Client) user).getPhone())
                    .build();
        } else
            throw new InvalidObjectFactoryException("INVALID_OBJECT_FACTORY_TYPE", "Невозможно создать объект типа " + user.getUserType() + " в текущй фабрикик ");
    }

}
