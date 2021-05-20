package com.yashakabra05.codebotsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yashakabra05.codebotsapp.Class.Users;

import java.util.ArrayList;

import static com.yashakabra05.codebotsapp.R.layout.activity_profile_firstpage;

public class Profile_firstpage extends AppCompatActivity {

    ImageView iv_profileimage;
    TextView tv_profilename, tv_profiletel, tv_profileemail, tv_profilecity;
    Button btn_logout;
    ArrayList<Users> user = new ArrayList<>();
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_profile_firstpage);

       iv_profileimage = findViewById(R.id.iv_profileimage);
       tv_profilename = findViewById(R.id.et_profilename);
       tv_profilecity = findViewById(R.id.et_profilecity);
       tv_profiletel = findViewById(R.id.et_profiletel);
       tv_profileemail = findViewById(R.id.et_profileemail);
       btn_logout = findViewById(R.id.btn_logout);

       /*
       auth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                user.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    Users users = snapshot1.getValue(Users.class);
                    if( users.equals(auth.getCurrentUser().getUid())) {
                        user.add(users);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        */
        SharedPreferences sp2 = getSharedPreferences("profile",MODE_PRIVATE);

        tv_profilename.setText(sp2.getString("name","NP"));
        tv_profilecity.setText(sp2.getString("location","NP"));
        tv_profiletel.setText(sp2.getString("phone","NP"));
        tv_profileemail.setText(sp2.getString("email","NP"));

       btn_logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FirebaseAuth.getInstance().signOut();
               Intent it = new Intent(Profile_firstpage.this,com.yashakabra05.codebotsapp.LoginActivity.class);
               startActivity(it);
               finish();
           }
       });

    }
}