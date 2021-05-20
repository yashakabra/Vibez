package com.yashakabra05.codebotsapp.Class;

//class for storing user details

public class Users {

    String email,location,name,phone,uid;

    public Users() {
    }

    public Users(String email, String location, String name, String phone, String uid) {
        this.email = email;
        this.location = location;
        this.name = name;
        this.phone = phone;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
