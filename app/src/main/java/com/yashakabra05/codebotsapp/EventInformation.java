package com.yashakabra05.codebotsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventInformation  extends AppCompatActivity  {

    TextView name,date,time,info,t_cost,tvlocation;

    ImageView ivimage, ivlocation;

    Button btn_buyticket;

    SupportMapFragment fragmentmap;
    GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_information);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fragmentmap = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentmap);
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

        String eventlocation = tvlocation.getText().toString();

        // List<Address> addresses;
        // addresses = null;
        List<Address>[] addressList = new List[]{new ArrayList<Address>()};

        fragmentmap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //   mGoogleApiClient.connect();
                map = googleMap;
                if(eventlocation!=null || !eventlocation.equals("")){
                    Geocoder geocoder = new Geocoder(EventInformation.this);
                    try {
                        addressList[0] = geocoder.getFromLocationName(eventlocation, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Address address = (Address) addressList[0].get(0);

                    double latfield = address.getLatitude();
                    double longfield = address.getLongitude();

                    map.addMarker(new MarkerOptions().position(new LatLng(latfield, longfield)));
                    map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latfield, longfield)));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom((new LatLng(latfield, longfield)), 15.0f));


                }

            }
        });

    }
}