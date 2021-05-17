package com.yashakabra05.codebotsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EventCalled extends AppCompatActivity {
public static ArrayList<Images> selected=new ArrayList<Images>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_called);
    }
}