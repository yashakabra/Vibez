package com.yashakabra05.codebotsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyParticularEvent extends AppCompatActivity implements MyParticularEventAdapter.Itemselec3 {

    RecyclerView rv_myparticular_event;
    RecyclerView.Adapter myparticulareventadapter;
    RecyclerView.LayoutManager layoutManager;

    Button btn_queries;

    FirebaseAuth auth;

    public static ArrayList<LocationInfo> particular_event_list = new ArrayList<LocationInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_particular_event);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btn_queries = findViewById(R.id.btn_queries);

        rv_myparticular_event = findViewById(R.id.rv_myparticular_event);
        rv_myparticular_event.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        auth = FirebaseAuth.getInstance();

        String event_name = getIntent().getStringExtra("event name");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("emergency").child(event_name);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                particular_event_list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    LocationInfo d = snapshot1.getValue(LocationInfo.class);
                    particular_event_list.add(d);
                }
                myparticulareventadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });

        myparticulareventadapter = new MyParticularEventAdapter(this,particular_event_list);
        rv_myparticular_event.setAdapter(myparticulareventadapter);
        rv_myparticular_event.setLayoutManager(layoutManager);

        btn_queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent it = new Intent(MyParticularEvent.this,com.yashakabra05.codebotsapp.ManangerQuery.class);
              it.putExtra("event name",event_name);
              startActivity(it);
            }
        });

    }
}