package com.yashakabra05.codebotsapp;

public class Images {

    String date,event_name,event_pic,event_pro,event_type,guide_pic,info,location,manager_num,t_cost,t_num,time,event_fav = "f";

    public Images() {
    }

    public Images(String date,String event_fav, String event_name, String event_pic, String event_pro, String event_type, String guide_pic, String info, String location, String manager_num, String t_cost, String t_num, String time) {
        this.date = date;
        this.event_fav = event_fav;
        this.event_name = event_name;
        this.event_pic = event_pic;
        this.event_pro = event_pro;
        this.event_type = event_type;
        this.guide_pic = guide_pic;
        this.info = info;
        this.location = location;
        this.manager_num = manager_num;
        this.t_cost = t_cost;
        this.t_num = t_num;
        this.time = time;

    }

    public Images(String date, String event_fav, String event_name, String event_pro, String event_type, String info, String location, String manager_num, String t_cost, String t_num,String time) {
        this.date = date;
        this.event_fav = event_fav;
        this.event_name = event_name;
        this.event_pro = event_pro;
        this.event_type = event_type;
        this.info = info;
        this.location = location;
        this.manager_num = manager_num;
        this.t_cost = t_cost;
        this.t_num = t_num;
        this.time = time;
        ;
    }

    public String getEvent_fav() {
        return event_fav;
    }

    public void setEvent_fav(String event_fav) {
        this.event_fav = event_fav;
    }

    public String getEvent_pic() {
        return event_pic;
    }

    public void setEvent_pic(String event_pic) {
        this.event_pic = event_pic;
    }

    public String getGuide_pic() {
        return guide_pic;
    }

    public void setGuide_pic(String guide_pic) {
        this.guide_pic = guide_pic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_pro() {
        return event_pro;
    }

    public void setEvent_pro(String event_pro) {
        this.event_pro = event_pro;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManager_num() {
        return manager_num;
    }

    public void setManager_num(String manager_num) {
        this.manager_num = manager_num;
    }

    public String getT_cost() {
        return t_cost;
    }

    public void setT_cost(String t_cost) {
        this.t_cost = t_cost;
    }

    public String getT_num() {
        return t_num;
    }

    public void setT_num(String t_num) {
        this.t_num = t_num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}