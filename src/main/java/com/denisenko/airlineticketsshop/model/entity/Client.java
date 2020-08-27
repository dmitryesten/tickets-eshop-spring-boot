package com.denisenko.airlineticketsshop.model.entity;


public class Client extends User {

    private String email;

    private String phone;

    public Client(){
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmailClient(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhoneClient(String phone) {
        this.phone = phone;
    }

    public static class ClientBuilder {
        private Client user;

        public ClientBuilder(){
            this.user = new Client();
        }

        public ClientBuilder setId(long id){
            user.setId(id);
            return this;
        }
        public ClientBuilder setFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }
        public ClientBuilder setLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }
        public ClientBuilder setPatronymicName(String patronymicName) {
            user.setPatronymicName(patronymicName);
            return this;
        }
        public ClientBuilder setLogin(Login login){
            user.setLoginObject(login);
            return this;
        }
        public ClientBuilder setUserType(UserType userType){
            user.setUserType(userType);
            return this;
        }
        public ClientBuilder setEmail(String email){
            this.user.setEmailClient(email);
            return this;
        }
        public ClientBuilder setPhone(String phone){
            this.user.setPhoneClient(phone);
            return this;
        }
        public Client build(){
            return user;
        }
    }

}
