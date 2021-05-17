package com.yashakabra05.codebotsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.yashakabra05.codebotsapp.Class.event_details;

public class Create_event_sec_Activity extends AppCompatActivity {

    EditText  event_tprice, event_pro;
    EditText event_ticket;
    TextView event_fee;
    Button event_sub;
    FirebaseAuth auth;
    FirebaseDatabase database;
    int price=0, noofticket=0, feeproY, feeproN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_sec);

        event_ticket = findViewById(R.id.event_ticket);
        event_tprice = findViewById(R.id.event_tprice);
        event_pro = findViewById(R.id.event_pro);
        event_fee = findViewById(R.id.event_fee);
        event_sub = findViewById(R.id.event_sub);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

 /*       if (event_ticket != null || event_tprice != null){
        price = Integer.valueOf(event_tprice.getText().toString());
        noofticket = Integer.valueOf((event_ticket.getText().toString()));
        feeproY = (int)( price*noofticket*(0.1));
        feeproN =(int) (price*noofticket*(0.12));


       if (event_pro.getText().toString().equals("Y")){

            event_fee.setText(Integer.toString(feeproY));
        }

        if (event_pro.getText().toString().equals("N")){
            event_fee.setText(Integer.toString(feeproN));
        }
        }
**/
        event_sub.setOnClickListener(new View.OnClickListener() {
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

                event_details event = new event_details(event_name, event_date, event_time, event_type, event_info,
                        event_tprice.getText().toString(), event_ticket.getText().toString(), event_location, event_contact, event_pro.getText().toString());


                database.getReference().child("my created events").child(auth.getCurrentUser().getUid()).push().setValue(event);
                database.getReference().child("events").push().setValue(event);
                Intent intent = new Intent(Create_event_sec_Activity.this, Manage_eventActivity.class);
                startActivity(intent);
                finish();

            }
        });





    }
}