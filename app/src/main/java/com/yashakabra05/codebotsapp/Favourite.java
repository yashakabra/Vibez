package com.yashakabra05.codebotsapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Favourite extends AppCompatActivity {
ListView lv;
  public static  ArrayList<Images> items=new ArrayList<Images>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        lv=findViewById(R.id.lv);



        FavouriteCustom adapter=new FavouriteCustom(this, items);
        lv.setAdapter(adapter);
    }
}