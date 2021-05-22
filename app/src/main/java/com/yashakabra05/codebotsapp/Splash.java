package com.yashakabra05.codebotsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(){

            public void run()
            {
                try{
                    sleep(0);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    {
                        startActivity(new Intent(Splash.this,HomePage.class));
                    }
                }
            }
        };
    }
}