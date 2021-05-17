package com.yashakabra05.codebotsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Filter extends AppCompatActivity {
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Button btn_song,btn_sports,btn_dance,btn_comedy;
        btn_song=findViewById(R.id.btnSong);
        btn_sports=findViewById(R.id.btnSports);
        btn_comedy=findViewById(R.id.btnComedy);
        btn_dance=findViewById(R.id.btnDance);



        btn_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="song";
                Intent intent=new Intent();
                intent.putExtra("cateogary",type);
                setResult(RESULT_OK,intent);
                Filter.this.finish();

            }
        });

        btn_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="sports";
                Intent intent=new Intent();
                intent.putExtra("cateogary",type);
                setResult(RESULT_OK,intent);
                Filter.this.finish();

            }
        });
        btn_comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="comedy";
                Intent intent=new Intent();
                intent.putExtra("cateogary",type);
                setResult(RESULT_OK,intent);
                Filter.this.finish();

            }
        });
        btn_dance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="dance";
                Intent intent=new Intent();
                intent.putExtra("cateogary",type);
                setResult(RESULT_OK,intent);
                Filter.this.finish();

            }
        });



    }
}