package com.yashakabra05.codebotsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CreateActivity extends AppCompatActivity {


    EditText etname2, etmail2, etpswd2, etconfirmpswd2;

    Button btnnext2;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        auth = FirebaseAuth.getInstance();

        etname2 = findViewById(R.id.etname2);
        etmail2 = findViewById(R.id.etmail2);
        etpswd2 = findViewById(R.id.etpswd2);
        etconfirmpswd2 = findViewById(R.id.etconfirmpswd2);
        btnnext2 = findViewById(R.id.btnnext2);

        btnnext2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                auth.createUserWithEmailAndPassword(etmail2.getText().toString(),etpswd2.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull  Task<AuthResult> task) {
                                auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){

                                            SharedPreferences shrd = getSharedPreferences("data", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = shrd.edit();

                                            editor.putString("email", etmail2.getText().toString());
                                            editor.putString("name", etname2.getText().toString());
                                            editor.putString("password", etpswd2.getText().toString());
                                            editor.apply();

                                            Intent intent = new Intent(CreateActivity.this, EnterActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(CreateActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                    /*    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){

                                    SharedPreferences shrd = getSharedPreferences("data", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = shrd.edit();

                                    editor.putString("email", etmail2.getText().toString());
                                    editor.putString("name", etname2.getText().toString());
                                    editor.apply();

                                    Intent intent = new Intent(CreateActivity.this, EnterActivity.class);
                                    startActivity(intent);
                                    finish();
                                            }
                                else {
                                    Toast.makeText(CreateActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                     */
            }
        });

    }
}