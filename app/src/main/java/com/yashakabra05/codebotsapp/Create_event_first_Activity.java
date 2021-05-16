package com.yashakabra05.codebotsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Create_event_first_Activity extends AppCompatActivity {

    EditText event_name, event_type, event_location, event_date, event_time, event_info, event_contact;
    ImageView event_map, event_pic;
    Button btnnextsec;
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_first);

       event_name = findViewById(R.id.event_name);
        event_type = findViewById(R.id.event_type);
        event_location = findViewById(R.id.event_location);
        event_date = findViewById(R.id.event_date);
        event_time = findViewById(R.id.event_time);
        event_info = findViewById(R.id.event_info);
        event_contact = findViewById(R.id.event_contact);
        event_map = findViewById(R.id.event_map);
        event_pic = findViewById(R.id.event_pic);
        btnnextsec = findViewById(R.id.btnnextsec);

        btnnextsec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences shrd = getSharedPreferences("manager", MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd.edit();

                editor.putString("event_name", event_name.getText().toString());
                editor.putString("event_type", event_type.getText().toString());
                editor.putString("event_location", event_location.getText().toString());
                editor.putString("event_date", event_date.getText().toString());
                editor.putString("event_time", event_time.getText().toString());
                editor.putString("event_info", event_info.getText().toString());
                editor.putString("event_contact", event_contact.getText().toString());


                editor.apply();

                Intent intent = new Intent(Create_event_first_Activity.this, Create_event_sec_Activity.class);
                startActivity(intent);
                finish();

            }
        });

        event_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,32);
            }
        });

        event_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,32);
            }
        });

    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data.getData()!= null){

            Uri imguri1 = data.getData();
            event_pic.setImageURI(imguri1);

            final StorageReference ref = storage.getReference().child("event").child(auth.getCurrentUser().getUid()).child(event_name.getText().toString());
            ref.putFile(imguri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    database.getReference().child("my created event").child(auth.getCurrentUser().getUid()).child(event_name.getText().toString())
                            .child("event_pic").setValue(storage.getReference().child("User").child(auth.getCurrentUser().getUid()).getFile(imguri1).toString());
                }
            });

        }
    }*/
}