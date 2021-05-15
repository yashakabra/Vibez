package com.yashakabra05.codebotsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hsalf.smilerating.SmileRating;
import com.hsalf.smileyrating.SmileyRating;

public class smily_feedback extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {//humei cheeze show krni h isliye hum override wali mei likhenge jo aayega
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        //message string ko nikalo main activity se msg ke througj
    }
}
