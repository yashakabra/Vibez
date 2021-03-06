package com.yashakabra05.codebotsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.yashakabra05.codebotsapp.Class.Users;

import java.util.concurrent.TimeUnit;

import static com.google.firebase.auth.PhoneAuthProvider.getCredential;

public class EnterActivity extends AppCompatActivity {

    EditText etlocation3, etphone3;

    Button btnenter3;

    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        etlocation3 = findViewById(R.id.etlocation3);
        etphone3 = findViewById(R.id.etphone3);
        btnenter3 = findViewById(R.id.btnenter3);

        SharedPreferences getshared = getSharedPreferences("data", Context.MODE_PRIVATE);
        String name = getshared.getString("name","not known");
        String email = getshared.getString("email", "not known");
        String pswd =  getshared.getString("password", "not known");
        String uid = auth.getCurrentUser().getUid();


        btnenter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signInWithEmailAndPassword(email,pswd ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            if (auth.getCurrentUser().isEmailVerified()) {
                                 String location = etlocation3.getText().toString();
                                 String phone = etphone3.getText().toString();
                                SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("name", name);
                                editor.putString("email", email);
                                editor.putString("location", location);
                                editor.putString("tel", phone);
                                editor.apply();

                                Users users = new Users(email,location,name,phone,uid);
                                database.getReference().child("users").child(auth.getCurrentUser().getUid()).setValue(users);

                                Intent i = new Intent(EnterActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else
                                Toast.makeText(EnterActivity.this, "Verify your account first", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(EnterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        if (auth.getCurrentUser().isEmailVerified()){
            Intent intent = new Intent(EnterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}