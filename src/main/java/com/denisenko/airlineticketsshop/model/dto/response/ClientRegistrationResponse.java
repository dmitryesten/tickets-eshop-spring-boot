package com.denisenko.airlineticketsshop.model.dto.response;

import com.denisenko.airlineticketsshop.model.entity.UserType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClientRegistrationResponse extends AbstractUserResponseDto {

    @NotNull(message = "Email mustn't be null")
    @NotEmpty(message = "Email mustn't be empty")
    @Email(message = "Email doesn't match the format")
    private String email;

    @NotNull(message = "Phone mustn't be null")
    @NotEmpty (message = "Phone mustn't be empty")
    //@Pattern(message = "Phone doesn't match the format", regexp = "^\\d{10}$")
    private String phone;

    public ClientRegistrationResponse(){}
    public static ClientRegistrationResponse.Builder builder() {
        return new ClientRegistrationResponse().new Builder();
    }
    public class Builder {
        private  Builder(){}

        public ClientRegistrationResponse.Builder setId(@NotEmpty @NotNull long id){
            ClientRegistrationResponse.super.setId(id);
            return this;
        }
        public ClientRegistrationResponse.Builder setFirstName(@NotEmpty @NotNull String fistName){
            ClientRegistrationResponse.super.setFirstName(fistName);
            return this;
        }
        public ClientRegistrationResponse.Builder setLastName(@NotEmpty @NotNull String lastName){
            ClientRegistrationResponse.super.setLastName(lastName);
            return this;
        }
        public ClientRegistrationResponse.Builder setPatronymicName(@NotNull String patronymicName){
            ClientRegistrationResponse.super.setPatronymic(patronymicName);
            return this;
        }
        public ClientRegistrationResponse.Builder setUserType(@NotEmpty @NotNull UserType userType){
            ClientRegistrationResponse.super.setUserType(userType.getTypeEntityString());
            return this;
        }
        public ClientRegistrationResponse.Builder setUserType(@NotEmpty @NotNull String userType){
            ClientRegistrationResponse.super.setUserType(userType);
            return this;
        }
        public ClientRegistrationResponse.Builder setEmail(@NotEmpty @NotNull String email){
            ClientRegistrationResponse.this.setEmail(email);
            return this;
        }
        public ClientRegistrationResponse.Builder setPhone(@NotEmpty @NotNull String phone){
            ClientRegistrationResponse.this.setPhone(phone);
            return this;
        }
        public ClientRegistrationResponse build(){
            return ClientRegistrationResponse.this;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
