package com.yashakabra05.codebotsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import static com.yashakabra05.codebotsapp.R.layout.activity_helpmain;

public class Helpmain extends AppCompatActivity {

    private EditText et_doubt;

    private Button btn_submit;

    private FirebaseDatabase database;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_helpmain);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        et_doubt = findViewById(R.id.et_doubt);
        btn_submit = findViewById(R.id.btn_submit);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_doubt.getText().toString().isEmpty()) {
                    Toast.makeText(Helpmain.this, "Please enter your doubt!!", Toast.LENGTH_SHORT).show();
                } else {

                    database.getReference().child("Help").child(auth.getCurrentUser().getUid()).push().setValue(et_doubt.getText().toString());

                    Toast.makeText(Helpmain.this, "We will reach you shortly!!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Helpmain.this, com.yashakabra05.codebotsapp.HomePage.class));
                }
            }
        });
    }
}