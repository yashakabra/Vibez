package com.yashakabra05.codebotsapp;

import java.util.ArrayList;

public class event_details {

    private String name;
    private String date;
    private String time;
    private String info;
    private String t_cost;
    private String location;
    private String image;

    public event_details(String name, String date, String time, String info, String t_cost, String location,String images) {

        this.name = name;
        this.date = date;
        this.time = time;
        this.info = info;
        this.t_cost = t_cost;
        this.location = location;
        this.image = images;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getT_cost() {
        return t_cost;
    }

    public void setT_cost(String t_cost) {
        this.t_cost = t_cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
