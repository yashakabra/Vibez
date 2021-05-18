package com.yashakabra05.codebotsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.GridLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yashakabra05.codebotsapp.Adapter.HomeAdapter;
import com.yashakabra05.codebotsapp.Class.event_details;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rv_event_pro, rv_event;
    FirebaseDatabase database;
    FirebaseAuth auth;
    ArrayList<event_details> detail;
    ArrayList<event_details> detailpro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rv_event = findViewById(R.id.rv_event);
        rv_event_pro = findViewById(R.id.rv_event_pro);
        detail = new ArrayList<>();
        detailpro = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        rv_event_pro.setHasFixedSize(true);
        rv_event.setHasFixedSize(true);

        HomeAdapter adapter = new HomeAdapter(detail, HomeActivity.this);
        rv_event.setAdapter(adapter);
        rv_event_pro.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this);

        rv_event.setLayoutManager(layoutManager);

        rv_event_pro.setLayoutManager(linearLayoutManager);


        database.getReference().child("events").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    event_details eventDetails = dataSnapshot.getValue(event_details.class);
                    detail.add(eventDetails);
                    if (eventDetails.getEvent_pro().equals("Y")){
                        detailpro.add(eventDetails);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}