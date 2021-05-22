package com.yashakabra05.codebotsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyEventsList extends AppCompatActivity implements MyEventsListAdapter.Itemselec {

    RecyclerView rv_myevents_list;
    RecyclerView.Adapter MyEventsListAdapter;
    RecyclerView.LayoutManager layoutManager;

    FirebaseAuth auth;

    ArrayList<Images> event_list = new ArrayList<Images>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events_list);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rv_myevents_list = findViewById(R.id.rv_myevents_list);
        rv_myevents_list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        auth =  FirebaseAuth.getInstance();

        MyEventsListAdapter = new MyEventsListAdapter(MyEventsList.this,event_list);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("my created events").child(auth.getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                event_list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Images d = snapshot1.getValue(Images.class);
                    event_list.add(d);
                }
                MyEventsListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });


        rv_myevents_list.setAdapter(MyEventsListAdapter);
        rv_myevents_list.setLayoutManager(layoutManager);
    }
}