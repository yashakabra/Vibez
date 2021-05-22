package com.yashakabra05.codebotsapp;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.yashakabra05.codebotsapp.Class.UserQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AskQuery extends AppCompatActivity {

    EditText etenterquery;
    Button btnsubmitquery,btnReachedEvent;
    FirebaseDatabase database;
    FirebaseAuth auth;
    SupportMapFragment fragmentnav;
    GoogleMap mapgoogle;
    FusedLocationProviderClient client;

    String event_name;
    String event_location, user_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_query);

        fragmentnav = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentnav);

        etenterquery = findViewById(R.id.etenterquery);
        btnsubmitquery = findViewById(R.id.btnsubmitquery);
        btnReachedEvent = findViewById(R.id.btnReachedEvent);


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        event_name = getIntent().getStringExtra("event name");
        event_location = getIntent().getStringExtra("event loc");
        //     event_location = "kolkata";
        btnsubmitquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserQuery query = new UserQuery(etenterquery.getText().toString(), auth.getCurrentUser().getEmail());

                database.getReference().child("query").child(getIntent().getStringExtra("event name")).push()
                        .setValue(query);
                Toast.makeText(AskQuery.this, "Your query has been registered sucessfully !", Toast.LENGTH_SHORT).show();
                etenterquery.setText("");
                // Intent intent = new Intent(AskQuery.this, enter_in_event.class);
                //startActivity(intent);
                //finish();
            }
        });

        btnReachedEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(AskQuery.this,com.yashakabra05.codebotsapp.EnterEventSecondPage.class);
                it.putExtra("event name",getIntent().getStringExtra("event name"));
                it.putExtra("guide map",getIntent().getStringExtra("guide map url"));
                it.putExtra("tel",getIntent().getStringExtra("tel1"));
                startActivity(it);
            }
        });






        List<Address>[] addressList = new List[]{new ArrayList<Address>()};


        fragmentnav.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                mapgoogle = googleMap;
                if(event_location!=null || !event_location.equals("")){
                    Geocoder geocoder = new Geocoder(AskQuery.this);
                    try {
                        addressList[0] = geocoder.getFromLocationName(event_location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Address address = (Address) addressList[0].get(0);

                    double latfield = address.getLatitude();
                    double longfield = address.getLongitude();

                    mapgoogle.addMarker(new MarkerOptions().position(new LatLng(latfield, longfield)));
                    mapgoogle.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latfield, longfield)));
                    mapgoogle.animateCamera(CameraUpdateFactory.newLatLngZoom((new LatLng(latfield, longfield)), 15.0f));


                }
            }
        });


    }


}