package com.yashakabra05.codebotsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.yashakabra05.codebotsapp.R.layout.activity_profile_firstpage;

public class Profile_firstpage extends AppCompatActivity {

    ImageView iv_profileimage;
    TextView tv_profilename, tv_profiletel, tv_profileemail, tv_profilecity;
    Button btn_profileedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_profile_firstpage);

       iv_profileimage = findViewById(R.id.iv_profileimage);
       tv_profilename = findViewById(R.id.et_profilename);
       tv_profilecity = findViewById(R.id.et_profilecity);
       tv_profiletel = findViewById(R.id.et_profiletel);
       tv_profileemail = findViewById(R.id.et_profileemail);
       btn_profileedit = findViewById(R.id.btn_profileedit);

    }
}