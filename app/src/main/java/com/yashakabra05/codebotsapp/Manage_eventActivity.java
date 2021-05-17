package com.yashakabra05.codebotsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yashakabra05.codebotsapp.Adapter.Manager_event_Adapter;
import com.yashakabra05.codebotsapp.Class.event_details;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class Manage_eventActivity extends AppCompatActivity {

    RecyclerView rv_manage_event;
    Button btn_create_event;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ArrayList<event_details> detaillist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_event);

        rv_manage_event = findViewById(R.id.rv_manager_event);
        btn_create_event = findViewById(R.id.btn_create_event);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        btn_create_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manage_eventActivity.this, Create_event_first_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        rv_manage_event.setHasFixedSize(true);
        detaillist = new ArrayList<>();
        Manager_event_Adapter adapter;
        adapter= new Manager_event_Adapter(detaillist, Manage_eventActivity.this);
        rv_manage_event.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(Manage_eventActivity.this);
        rv_manage_event.setLayoutManager(layoutManager);

        database.getReference().child("my created events").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    event_details eventDetails = dataSnapshot.getValue(event_details.class);
                    detaillist.add(eventDetails);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}