package com.yashakabra05.codebotsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Create_event_sec_Activity extends AppCompatActivity {

    private EditText  event_tprice, event_pro, event_ticket;

    private TextView total_cost,tv_pay;

    private Button event_sub,btn_pay;

    private String total, event_fav = "F";



    private FirebaseAuth auth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_sec);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        event_ticket = findViewById(R.id.event_ticket);
        event_tprice = findViewById(R.id.event_tprice);
        event_pro = findViewById(R.id.event_pro);
        event_sub = findViewById(R.id.event_sub);
        total_cost = findViewById(R.id.total_cost);
        tv_pay = findViewById(R.id.tv_pay);
        btn_pay = findViewById(R.id.btn_pay);

        tv_pay.setVisibility(View.GONE);
        btn_pay.setVisibility(View.GONE);
        total_cost.setVisibility(View.GONE);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        event_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(event_tprice.getText().toString().isEmpty() ||  event_ticket.getText().toString().isEmpty() ||  event_pro.getText().toString().isEmpty())
                {
                    Toast.makeText(Create_event_sec_Activity.this, "Please enter all fields !", Toast.LENGTH_SHORT).show();
                }



                else {

                    if (event_pro.getText().toString().equals("yes")) {
                        total = "3000rs";
                    } else {
                        total = "2000rs";
                    }

                    total_cost.setText(total);

                    tv_pay.setVisibility(View.VISIBLE);
                    btn_pay.setVisibility(View.VISIBLE);
                    total_cost.setVisibility(View.VISIBLE);
                }

            }
        });

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences getshared = getSharedPreferences("manager", Context.MODE_PRIVATE);
                String event_name  = getshared.getString("event_name","not known");
                String event_type  = getshared.getString("event_type","not known");
                String  event_location= getshared.getString("event_location","not known");
                String  event_date = getshared.getString("event_date","not known");
                String event_time  = getshared.getString("event_time","not known");
                String  event_info = getshared.getString("event_info","not known");
                String  event_contact = getshared.getString("event_contact","not known");
                String event_pic = getshared.getString("event_pic","not known");//here add default pics
                String event_guide = getshared.getString("event_guide","not known");

                Images event = new Images(event_date,event_fav,event_name, event_pro.getText().toString(), event_type, event_info, event_location,
                         event_contact,event_tprice.getText().toString(), event_ticket.getText().toString(),event_time);
                event.setEvent_pic(event_pic);
                event.setGuide_pic(event_guide);

                database.getReference().child("my created events").child(auth.getCurrentUser().getUid()).push().setValue(event);
                database.getReference().child("events").push().setValue(event);

                Intent intent = new Intent(Create_event_sec_Activity.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

    }
}