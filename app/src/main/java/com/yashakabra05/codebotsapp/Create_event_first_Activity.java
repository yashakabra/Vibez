package com.yashakabra05.codebotsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import static java.security.AccessController.getContext;

public class Create_event_first_Activity extends AppCompatActivity {

    EditText event_name, event_location, event_date, event_time, event_info, event_contact;
    AutoCompleteTextView event_type;
    ImageView event_map, event_pic;
    Button btnnextsec;
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseStorage storage;
    ArrayList<String> types = new ArrayList<String>();
    String eventpic,eventguide;


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
        auth = FirebaseAuth.getInstance();
        storage  = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        types.add("songs");
        types.add("dance");
        types.add("sports");
        types.add("comedy");
        types.add("award");
        types.add("theatre");
        types.add("dance");

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.search_autocomplete_element,types);
        event_type.setThreshold(0);
        event_type.setAdapter(adapter);


       /* event_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder;
            }
        });

        */
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
                editor.putString("event_pic",eventpic);
                editor.putString("event_guide",eventguide);

//  Images event = new Images(event_date,event_name, event_pro.getText().toString(), event_type, event_info, event_location,
                //        event_contact,event_tprice.getText().toString(), event_ticket.getText().toString(),event_time,event_fav);

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
                startActivityForResult(intent,30);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==32 && resultCode==RESULT_OK) {
            if (data.getData() != null) {

                Uri imguri1 = data.getData();
                event_pic.setImageURI(imguri1);

                final StorageReference ref = storage.getReference().child("event").child(auth.getCurrentUser().getUid()).child(event_name.getText().toString()).child("event_pic");
                ref.putFile(imguri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                eventpic = uri.toString();
                                Toast.makeText(Create_event_first_Activity.this, "IMAGE SUCESSFULLY ADDED", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Create_event_first_Activity.this, "IMAGE NOT ADDED", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        }
        if(requestCode==30 && resultCode == RESULT_OK) {
            if (data.getData() != null) {

                Uri imguri2 = data.getData();
                event_map.setImageURI(imguri2);

                final StorageReference ref = storage.getReference().child("event").child(auth.getCurrentUser().getUid()).child(event_name.getText().toString()).child("event_map");
                ref.putFile(imguri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                eventguide = uri.toString();
                                Toast.makeText(Create_event_first_Activity.this,"IMAGE SUCESSFULLY ADDED", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Create_event_first_Activity.this, "IMAGE NOT ADDED", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }
    }
}