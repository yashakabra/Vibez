package com.yashakabra05.codebotsapp;

public class Images {
    String cityName,eventName,date,price,cateogary,image,popularity;

    public Images(String cityName, String eventName, String date, String price,String cateogary,String image,String popularity) {
        this.cityName = cityName;
        this.eventName = eventName;
        this.date = date;
        this.price = price;
        this.cateogary=cateogary;
        this.image=image;
        this.popularity=popularity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCateogary() {
        return cateogary;
    }

    public void setCateogary(String cateogary) {
        this.cateogary = cateogary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }
}