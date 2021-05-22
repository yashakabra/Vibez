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
import android.view.WindowManager;
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

    private EditText event_name, event_location, event_date, event_time, event_info, event_contact;

    private ArrayList<String> types = new ArrayList<String>();

    private String eventpic,eventguide;

    private AutoCompleteTextView event_type;

    private ImageView event_map, event_pic;

    private Button btnnextsec;

    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private FirebaseStorage storage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_first);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

                editor.apply();

                if( event_contact.getText().toString().isEmpty() || event_info.getText().toString().isEmpty() || event_time.getText().toString().isEmpty() ||event_name.getText().toString().isEmpty() || event_type.getText().toString().isEmpty() || event_location.getText().toString().isEmpty() || event_date.getText().toString().isEmpty() )
                {
                    Toast.makeText(Create_event_first_Activity.this, "Please enter all fields !", Toast.LENGTH_SHORT).show();
                }
                else{
                Intent intent = new Intent(Create_event_first_Activity.this, Create_event_sec_Activity.class);
                startActivity(intent);
                finish();
                }

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