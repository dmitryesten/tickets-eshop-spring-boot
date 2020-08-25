package com.denisenko.airlineticketsshop.model.entity;


public class Administrator extends User {

    private String position;

    public Administrator(){
        super();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public static class AdministratorBuilder {
        private Administrator user;

        public AdministratorBuilder(){
            this.user = new Administrator();
        }
        public AdministratorBuilder setId(long id){
            user.setId(id);
            return this;
        }
        public AdministratorBuilder setFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }
        public AdministratorBuilder setLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }
        public AdministratorBuilder setPatronymicName(String patronymicName) {
            user.setPatronymicName(patronymicName);
            return this;
        }
        public AdministratorBuilder setLogin(Login login){
            user.setLogin(login);
            return this;
        }
        public AdministratorBuilder setUserType(EntitySystem userType){
            user.setUserType(userType);
            return this;
        }
        public AdministratorBuilder setPosition(String position){
            this.user.setPosition(position);
            return this;
        }
        public Administrator build(){
            return user;
        }
    }

}
