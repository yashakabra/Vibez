package com.yashakabra05.codebotsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class enter_in_event extends AppCompatActivity {

    Button btnquery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_in_event);

        btnquery = findViewById(R.id.btnquery);

        btnquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(enter_in_event.this, AskQuery.class);
                intent.putExtra("event name",getIntent().getStringExtra("event name"));
                startActivity(intent);
            }
        });

    }
}