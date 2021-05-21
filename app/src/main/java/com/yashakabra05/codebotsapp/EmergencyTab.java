package com.yashakabra05.codebotsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EmergencyTab extends AppCompatActivity {

    TextView tv_person_name, tv_difficulty, tv_phone_no;
    Button btn_call;

    ArrayList<LocationInfo> info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_tab);

        tv_difficulty = findViewById(R.id.tv_difficulty);
        tv_person_name = findViewById(R.id.tv_person_name);
        tv_phone_no = findViewById(R.id.tv_phone_no);
        btn_call = findViewById(R.id.btn_call);

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





    }
}