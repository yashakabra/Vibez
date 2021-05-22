package com.yashakabra05.codebotsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import static com.yashakabra05.codebotsapp.R.layout.activity_profile_firstpage;

public class ProfileFirstPage extends AppCompatActivity {

    ImageView iv_profileimage;

    TextView tv_profilename, tv_profiletel, tv_profileemail, tv_profilecity;

    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(activity_profile_firstpage);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

       iv_profileimage = findViewById(R.id.iv_profileimage);
       tv_profilename = findViewById(R.id.et_profilename);
       tv_profilecity = findViewById(R.id.et_profilecity);
       tv_profiletel = findViewById(R.id.et_profiletel);
       tv_profileemail = findViewById(R.id.et_profileemail);
       btn_logout = findViewById(R.id.btn_logout);

       iv_profileimage.setImageResource(R.drawable.logo);

        SharedPreferences sp2 = getSharedPreferences("profile",MODE_PRIVATE);

        tv_profilename.setText(sp2.getString("name","NP"));
        tv_profilecity.setText(sp2.getString("location","NP"));
        tv_profiletel.setText(sp2.getString("tel","NP"));
        tv_profileemail.setText(sp2.getString("email","NP"));

       btn_logout.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               FirebaseAuth.getInstance().signOut();
               Intent it = new Intent(ProfileFirstPage.this,com.yashakabra05.codebotsapp.LoginActivity.class);
               startActivity(it);
               finish();
           }
       });

    }
}