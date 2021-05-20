package com.yashakabra05.codebotsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yashakabra05.codebotsapp.Class.Images;
import com.yashakabra05.codebotsapp.Class.UserQuery;
import com.yashakabra05.codebotsapp.adapter.ManagerQueryAdapter;

import java.util.ArrayList;

public class ManangerQuery extends AppCompatActivity {

    FirebaseDatabase database;
    ArrayList<UserQuery> queries;
    RecyclerView rvquery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mananger_query);

        rvquery = findViewById(R.id.rvquery);
        queries = new ArrayList<>();
        database = FirebaseDatabase.getInstance();

        rvquery.setHasFixedSize(true);
        ManagerQueryAdapter adapter =new ManagerQueryAdapter(queries, ManangerQuery.this);
        rvquery.setAdapter(adapter);

        LinearLayoutManager layoutManager =new LinearLayoutManager(ManangerQuery.this);
        rvquery.setLayoutManager(layoutManager);

        database.getReference().child("query").child(getIntent().getStringExtra("event name")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                queries.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    UserQuery d = snapshot1.getValue(UserQuery.class);
                    queries.add(d);
            }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}