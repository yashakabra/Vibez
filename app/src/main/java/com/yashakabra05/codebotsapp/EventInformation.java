package com.yashakabra05.codebotsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventInformation  extends AppCompatActivity  {

    ArrayList<event_details> ed = new ArrayList<>();

     TextView name,date,time,info,t_cost;
     ImageView ivimage;
     event_details obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_information);

        name = findViewById(R.id.tvname);
        date = findViewById(R.id.tvdate);
        time = findViewById(R.id.tvtime);
        info = findViewById(R.id.tvinfo);
        t_cost = findViewById(R.id.tvprice);
        ivimage = findViewById(R.id.ivimage);

        ed.add(new event_details("MUSIC CONCERT","12/05/2021","5.00 PM","ARJIT SINGH IS COMING","500 RS","PARADE GROUND , AGRA","pic1"));

        name.setText(ed.get(0).getName());
        date.setText(ed.get(0).getDate());
        time.setText(ed.get(0).getTime());
        info.setText(ed.get(0).getInfo());
        t_cost.setText(ed.get(0).getT_cost());
        ivimage.setImageResource(R.drawable.mercedes);



    }





}