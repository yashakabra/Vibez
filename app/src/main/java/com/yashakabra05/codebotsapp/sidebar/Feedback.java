package com.yashakabra05.codebotsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {

    ImageView iv_terrible, iv_okay, iv_excellent;
    Button btn_submit_feedback;
    String Mood = "null";
    TextView tv_feedback;

    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        iv_excellent = findViewById(R.id.iv_excellent);
        iv_okay = findViewById(R.id.iv_okay);
        iv_terrible = findViewById(R.id.iv_terrible);
        btn_submit_feedback = findViewById(R.id.btn_submit_feedback);
        tv_feedback = findViewById(R.id.tv_feedback);

        iv_excellent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mood = "excellent";
            }
        });
        iv_terrible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mood = "terrible";
            }
        });
        iv_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mood = "okay";
            }
        });

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        btn_submit_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getReference().child("feedback").child(auth.getCurrentUser().getUid()).push().setValue("Your work is: " +Mood +"and my feed back is: "+ tv_feedback.getText().toString());
                Toast.makeText(Feedback.this, "Thank you for your feedback", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Feedback.this,com.yashakabra05.codebotsapp.HomePage.class));
            }
        });
    }
}