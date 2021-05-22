package com.yashakabra05.codebotsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText etemail1, etpswd1;

    TextView tvsignin1;

    Button btnsignin1;

    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etemail1 = findViewById(R.id.etemail1);
        etpswd1 = findViewById(R.id.etpswd1);
        tvsignin1 = findViewById(R.id.tvsignin1);
        btnsignin1 = findViewById(R.id.btnsignin1);

        database= FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();



        btnsignin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etemail1.getText().toString().isEmpty() || etpswd1.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Fill all details", Toast.LENGTH_SHORT).show();
                }

                auth.signInWithEmailAndPassword(etemail1.getText().toString(),etpswd1.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    if(auth.getCurrentUser()!= null) {
                                        if (auth.getCurrentUser().isEmailVerified()) {
                                            Intent intent = new Intent(LoginActivity.this, HomePage.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else
                                            Toast.makeText(LoginActivity.this, "Verify your account first", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                else
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        if (auth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }

        tvsignin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}