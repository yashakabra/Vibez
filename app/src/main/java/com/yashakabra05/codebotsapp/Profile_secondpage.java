package com.yashakabra05.codebotsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Profile_secondpage extends AppCompatActivity {

    EditText et_profilename, et_profiletel, et_profilecity, et_profileemail;
    Button btn_savechanges, btn_changeimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_secondpage);

        et_profilecity = findViewById(R.id.et_profilecity);
        et_profileemail = findViewById(R.id.et_profileemail);
        et_profilename = findViewById(R.id.et_profilename);
        et_profiletel = findViewById(R.id.et_profiletel);
        btn_changeimage = findViewById(R.id.btn_changeimage);
        btn_savechanges = findViewById(R.id.btn_savechanges);

    }


}