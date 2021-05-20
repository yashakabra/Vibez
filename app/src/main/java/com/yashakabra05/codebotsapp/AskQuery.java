package com.yashakabra05.codebotsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.yashakabra05.codebotsapp.Class.UserQuery;

public class AskQuery extends AppCompatActivity {

    EditText etenterquery;
    Button btnsubmitquery;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_query);

        etenterquery = findViewById(R.id.etenterquery);
        btnsubmitquery = findViewById(R.id.btnsubmitquery);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        btnsubmitquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserQuery query = new UserQuery(etenterquery.getText().toString(), auth.getCurrentUser().getEmail());

                database.getReference().child("query").child(getIntent().getStringExtra("event name")).push()
                        .setValue(query);
                Intent intent = new Intent(AskQuery.this, enter_in_event.class);
                startActivity(intent);
                finish();
            }
        });

    }
}