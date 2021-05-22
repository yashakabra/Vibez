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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmergencyTab extends AppCompatActivity {

    TextView tv_person_name, tv_difficulty, tv_phone_no;
    Button btn_call, btn_ambulance;
    SupportMapFragment victimmap;
    GoogleMap map;

    ArrayList<LocationInfo> info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_tab);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        victimmap = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.victimmap);
        tv_difficulty = findViewById(R.id.tv_difficulty);
        tv_person_name = findViewById(R.id.tv_person_name);
        tv_phone_no = findViewById(R.id.tv_phone_no);
        btn_call = findViewById(R.id.btn_call);
        btn_ambulance = findViewById(R.id.btn_ambulance);

        String victim_name = getIntent().getStringExtra("victim name");
        String type_emer = getIntent().getStringExtra("emergency called");
        String lat_loc = getIntent().getStringExtra("lat loc");
        String long_loc =  getIntent().getStringExtra("long loc");
        String victim_tel =  getIntent().getStringExtra("victim tel");

        tv_phone_no.setText(victim_tel);
        tv_person_name.setText(victim_name);
        tv_difficulty.setText(type_emer);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + victim_tel));
                startActivity(intent);
            }
        });

        btn_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String g = "108";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + g));
                startActivity(intent);
            }
        });

        List<Address>[] addressList = new List[]{new ArrayList<Address>()};

        victimmap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                map = googleMap;
                if(lat_loc!=null || !lat_loc.equals("") || long_loc!=null || !long_loc.equals("")){


                    double latfield = Double.valueOf(lat_loc);
                    double longfield = Double.valueOf(long_loc);

                    LatLng latLng = new LatLng(latfield, longfield);
                    Toast.makeText(EmergencyTab.this, String.valueOf(latfield), Toast.LENGTH_SHORT).show();

                    map.addMarker(new MarkerOptions().position(latLng));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));

                }
            }
        });



    }
}