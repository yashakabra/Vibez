package com.yashakabra05.codebotsapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EventCalled extends AppCompatActivity {

TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_called);
        tv=findViewById(R.id.tvSearchEventName);

            tv.setText(getIntent().getStringExtra("name of event"));

    }
}