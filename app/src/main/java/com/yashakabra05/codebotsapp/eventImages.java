package com.yashakabra05.codebotsapp;

public class eventImages {
    String image;
    String date;
    String  name;
    String price;

    public eventImages(String image, String date, String name, String price) {
        this.image = image;
        this.date = date;
        this.name = name;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
