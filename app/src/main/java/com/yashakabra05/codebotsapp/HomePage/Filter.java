package com.yashakabra05.codebotsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Filter extends AppCompatActivity {

    private String type;

    private TextView tvSong,tvDance,tvSports,tvComedy,tvAward,tvTheatre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_filter);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvSong=findViewById(R.id.tvSongs);
        tvDance=findViewById(R.id.tvDance);
        tvSports=findViewById(R.id.tvSports);
        tvComedy=findViewById(R.id.tvComedy);
        tvAward=findViewById(R.id.tvAwards);
        tvTheatre=findViewById(R.id.tvTheatre);

        tvSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type="song";
                Intent intent=new Intent();
                intent.putExtra("cateogary",type);
                setResult(RESULT_OK,intent);
                Filter.this.finish();
            }
        });

        tvSports.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                type="sports";
                Intent intent=new Intent();
                intent.putExtra("cateogary",type);
                setResult(RESULT_OK,intent);
                Filter.this.finish();
            }
        });

        tvComedy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                type="comedy";
                Intent intent=new Intent();
                intent.putExtra("cateogary",type);
                setResult(RESULT_OK,intent);
                Filter.this.finish();
            }
        });

        tvAward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                type="award";
                Intent intent=new Intent();
                intent.putExtra("cateogary",type);
                setResult(RESULT_OK,intent);
                Filter.this.finish();
            }
        });

        tvDance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                type="dance";
                Intent intent=new Intent();
                intent.putExtra("cateogary",type);
                setResult(RESULT_OK,intent);
                Filter.this.finish();
            }
        });

        tvTheatre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                type="theatre";
                Intent intent=new Intent();
                intent.putExtra("cateogary",type);
                setResult(RESULT_OK,intent);
                Filter.this.finish();
            }
        });
    }
}