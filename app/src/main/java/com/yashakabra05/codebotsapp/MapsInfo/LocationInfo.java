package com.yashakabra05.codebotsapp;

public class LocationInfo {

    String latloc,longloc,name,phone,typeofdifficulty;

    public LocationInfo(String latloc, String longloc, String name, String phone, String typeofdifficulty) {
        this.latloc = latloc;
        this.longloc = longloc;
        this.name = name;
        this.phone = phone;
        this.typeofdifficulty = typeofdifficulty;
    }

    public LocationInfo() {
    }

    public String getLatloc() {
        return latloc;
    }

    public void setLatloc(String latloc) {
        this.latloc = latloc;
    }

    public String getLongloc() {
        return longloc;
    }

    public void setLongloc(String longloc) {
        this.longloc = longloc;
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

    public String getTypeofdifficulty() {
        return typeofdifficulty;
    }

    public void setTypeofdifficulty(String typeofdifficulty) {
        this.typeofdifficulty = typeofdifficulty;
    }
}
