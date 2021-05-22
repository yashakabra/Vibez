package com.yashakabra05.codebotsapp;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class contactus extends AppCompatActivity {

    private ImageView iv_yash, iv_harsh, iv_anuj, iv_krishna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        iv_yash = findViewById(R.id.iv_yash);
        iv_krishna = findViewById(R.id.iv_krishna);
        iv_harsh = findViewById(R.id.iv_harsh);
        iv_anuj = findViewById(R.id.iv_anuj);

        iv_yash.setImageResource(R.drawable.yash);
        iv_krishna.setImageResource(R.drawable.krishan);
        iv_anuj.setImageResource(R.drawable.anuj);
        iv_harsh.setImageResource(R.drawable.harsh);
    }
}