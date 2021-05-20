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
import com.yashakabra05.codebotsapp.Class.event_details;

import java.util.ArrayList;

public class EventInformation  extends AppCompatActivity  {

    ArrayList<event_details> ed = new ArrayList<>();

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
        btn_buyticket = findViewById(R.id.btn_buyticket);

        name.setText(getIntent().getStringExtra("event_name"));
        date.setText(getIntent().getStringExtra("event_date"));
        time.setText(getIntent().getStringExtra("event_time"));
        info.setText(getIntent().getStringExtra("event_info"));
        t_cost.setText(getIntent().getStringExtra("event_tcost"));
       // iv.setText(getIntent().getStringExtra("event_image"));
        Picasso.get().load(getIntent().getStringExtra("event_pic")).placeholder(R.mipmap.ic_event).into(ivimage);
        tvlocation.setText(getIntent().getStringExtra("event_location"));


    /*    ed.add(new event_details("MUSIC CONCERT","12/05/2021","5.00 PM","We present you here an event management cum assistance app which not only provides a\n" +
                "hassle free environment to the event manager to manage his event\n" +
                " but also embarks the user with full \n" +
                "assistance from the start of the event to the end.\n" +
                "here our user is not just provided with an enhanced ticket booking service but also\n" +
                "a complete  guidance and assistance throughout the event like cab booking service right \n" +
                "at a click, a complete map of the event spot for knowing the location of basic nessisities\n" +
                "and the most important services like emergeny ","500 RS","taj mahal AGRA","pic1"));

        name.setText(ed.get(0).getName());
        date.setText(ed.get(0).getDate());
        time.setText(ed.get(0).getTime());
        info.setText(ed.get(0).getInfo());
        t_cost.setText(ed.get(0).getT_cost());
        tvlocation.setText(ed.get(0).getLocation());
        ivimage.setImageResource(R.drawable.mercedes);
        **/

        ivlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentb = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + tvlocation));
                startActivity(intentb);
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