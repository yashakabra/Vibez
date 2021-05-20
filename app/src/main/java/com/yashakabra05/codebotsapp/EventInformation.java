package com.yashakabra05.codebotsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EventInformation  extends AppCompatActivity  {

     TextView name,date,time,info,t_cost,tvlocation;

     ImageView ivimage, ivlocation;

     Button btn_buyticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_information);

        name = findViewById(R.id.tvname);
        date = findViewById(R.id.tvdate);
        time = findViewById(R.id.tvtime);
        info = findViewById(R.id.tvinfo);
        t_cost = findViewById(R.id.tvprice);
        ivimage = findViewById(R.id.ivimage);
        tvlocation = findViewById(R.id.tvlocation);
        ivlocation = findViewById(R.id.ivlocation);
        btn_buyticket = findViewById(R.id.btn_queries);

        name.setText(getIntent().getStringExtra("event_name"));
        date.setText(getIntent().getStringExtra("event_date"));
        time.setText(getIntent().getStringExtra("event_time"));
        info.setText(getIntent().getStringExtra("event_info"));
        t_cost.setText(getIntent().getStringExtra("event_tcost"));
        tvlocation.setText(getIntent().getStringExtra("event_location"));

        Picasso.get().load(getIntent().getStringExtra("event_pic")).placeholder(R.mipmap.ic_event).into(ivimage);

        ivlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentb = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + tvlocation.getText().toString()));
                startActivity(intentb);;
            }
        });

        btn_buyticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(EventInformation.this,com.yashakabra05.codebotsapp.Calendar.class);
                it.putExtra("nameofevent",name.getText().toString());
                startActivity(it);
            }
        });
    }
}