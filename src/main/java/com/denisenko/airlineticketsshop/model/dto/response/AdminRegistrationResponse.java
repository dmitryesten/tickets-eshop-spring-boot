package com.denisenko.airlineticketsshop.model.dto.response;

import com.denisenko.airlineticketsshop.model.entity.UserType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdminRegistrationResponse extends AbstractUserResponseDto {

    @NotNull(message = "Position mustn't be null")
    @NotEmpty (message = "Position mustn't be empty")
    private String position;


    public AdminRegistrationResponse(){
        super();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public static Builder builder() {
        return new AdminRegistrationResponse().new Builder();
    }

    public class Builder {
        private  Builder(){}

        public Builder setId(@NotEmpty @NotNull long id){
            AdminRegistrationResponse.super.setId(id);
            return this;
        }
        public Builder setFirstName(@NotEmpty @NotNull String fistName){
            AdminRegistrationResponse.super.setFirstName(fistName);
            return this;
        }
        public Builder setLastName(@NotEmpty @NotNull String lastName){
            AdminRegistrationResponse.super.setLastName(lastName);
            return this;
        }
        public Builder setPatronymicName(@NotNull String patronymicName){
            AdminRegistrationResponse.super.setPatronymic(patronymicName);
            return this;
        }
        public Builder setUserType(@NotEmpty @NotNull UserType userType){
            AdminRegistrationResponse.super.setUserType(userType.getTypeEntityString());
            return this;
        }
        public Builder setUserType(@NotEmpty @NotNull String userType){
            AdminRegistrationResponse.super.setUserType(userType);
            return this;
        }
        public Builder setPosition(@NotEmpty @NotNull String position){
            AdminRegistrationResponse.this.setPosition(position);
            return this;
        }

        public AdminRegistrationResponse build(){
            return AdminRegistrationResponse.this;
        }
    }

}
