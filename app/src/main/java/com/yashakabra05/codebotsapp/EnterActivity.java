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

    FirebaseAuth auth;
    FirebaseDatabase database;
    EditText etlocation3, etphone3, etotp3;
    // Button btngetotp3, btnverify3;
    Button btntest3;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        etlocation3 = findViewById(R.id.etlocation3);
        etphone3 = findViewById(R.id.etphone3);
        etotp3 = findViewById(R.id.etotp3);
        //     btngetotp3 = findViewById(R.id.btngetotp3);
        //     btnverify3 = findViewById(R.id.btnverify3);
        btntest3 = findViewById(R.id.btntest3);

        btntest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences getshared = getSharedPreferences("data", Context.MODE_PRIVATE);
                String name = getshared.getString("name","not known");
                String email = getshared.getString("email", "not known");
                String uid = auth.getCurrentUser().getUid();
                String location = etlocation3.getText().toString();
                String phone = etphone3.getText().toString();

                Users users = new Users(name, email, uid, location, phone);
                database.getReference().child("users").child(auth.getCurrentUser().getUid()).setValue(users);

                Intent i = new Intent(EnterActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    /*    btngetotp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is for checking weather the user
                // has entered his mobile number or not.
                if (TextUtils.isEmpty(etphone3.getText().toString())) {
                    // when mobile number text field is empty
                    // displaying a toast message.
                    Toast.makeText(EnterActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    // if the text field is not empty we are calling our
                    // send OTP method for getting OTP from Firebase.
                    String phone = "+91" + etphone3.getText().toString();
                    sendVerificationCode(phone);
                }
            }
        });

        // initializing on click listener
        // for verify otp button
        btnverify3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the OTP text field is empty or not.
                if (TextUtils.isEmpty(etotp3.getText().toString())) {
                    // if the OTP text field is empty display
                    // a message to user to enter OTP
                    Toast.makeText(EnterActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    // if OTP field is not empty calling
                    // method to verify the OTP.
                    verifyCode(etotp3.getText().toString());
                }
            }
        });
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            SharedPreferences getshared = getSharedPreferences("data", Context.MODE_PRIVATE);
                            String name = getshared.getString("name","not known");
                            String email = getshared.getString("email", "not known");
                            String uid = auth.getCurrentUser().getUid();
                            String location = etlocation3.getText().toString();
                            String phone = etphone3.getText().toString();

                            Users users = new Users(name, email, uid, location, phone);
                            database.getReference().child("users").child(auth.getCurrentUser().getUid()).setValue(users);

                            Intent i = new Intent(EnterActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(EnterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                30,
                TimeUnit.SECONDS,
                (Activity) TaskExecutors.MAIN_THREAD, // this task will be excuted on Main thread.
                mCallBack
        );
    }

    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                etotp3.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(EnterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);

    }
**/

    }
}