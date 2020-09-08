package com.denisenko.airlineticketsshop.model.dto.response.factory;

import com.denisenko.airlineticketsshop.model.entity.Administrator;
import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryResponseDtoTest {

    @Test
    void getUserResponseDtoAdmin() throws InvalidObjectFactoryException {
        User administrator = new Administrator();
        UserFactoryResponseDto.getUserResponseDto(administrator);

    }
}