package com.example.projetofinal;

public class User {

    public String fullname;
    public String username;
    public String email;
    public String password;
    public String phoneNumber;
    public String gender;

    public User(){

    }

    public User(String fullname, String username, String email, String password, String phoneNumber, String gender) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

}
