package com.yashakabra05.codebotsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CreateActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText etname2, etmail2, etpswd2, etconfirmpswd2;
    Button btnnext2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        auth = FirebaseAuth.getInstance();

        etname2 = findViewById(R.id.etname2);
        etmail2 = findViewById(R.id.etmail2);
        etpswd2 = findViewById(R.id.etpswd2);
        etconfirmpswd2 = findViewById(R.id.etconfirmpswd2);
        btnnext2 = findViewById(R.id.btnnext2);

        btnnext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword(etmail2.getText().toString(),etpswd2.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                              /*      auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){

                                                Users user= new Users(binding.etname.getText().toString() , binding.spnbatch.getSelectedItem().toString(), binding.spnsection.getSelectedItem().toString(),
                                                        binding.etemail.getText().toString(),binding.etpswd.getText().toString());
                                                Intent intent1 = new Intent(SignupActivity.this, SigninActivity.class);


                                                editor.putString("name", binding.etname.getText().toString());
                                                editor.apply(); SharedPreferences shrd = getSharedPreferences("data", MODE_PRIVATE);
                                                SharedPreferences.Editor editor = shrd.edit();

                                                editor.putString("batch", binding.spnbatch.getSelectedItem().toString());
                                                editor.putString("section", binding.spnsection.getSelectedItem().toString());


                                                database.getReference().child("Users").child( binding.spnbatch.getSelectedItem().toString()).child( binding.spnsection.getSelectedItem().toString()).child(auth.getUid())
                                                        .setValue(user);

                                                binding.pbsignup.setVisibility(View.INVISIBLE);   **/

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
            }
        });

    }
}