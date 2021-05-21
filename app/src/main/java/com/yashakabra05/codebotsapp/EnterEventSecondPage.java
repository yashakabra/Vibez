package com.yashakabra05.codebotsapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.yashakabra05.codebotsapp.Class.Users;

import java.util.ArrayList;

public class EnterEventSecondPage extends AppCompatActivity {
    ImageView ivEmergencySymbol, ivGuideMap;
    TextView tvEmergencyMessage, tvPhoneNumber, tvMedicalAreYouSure, tvSuspiciousAreYouSure, tvHarrasmentAreYouSure;
    Button btnMedicalYes, btnMedicalNo, btnSuspiciousNo, btnSuspiciousYes, btnHarrasmentYes, btnHarrasmentNo, btnExitEvent;
    Button btnMedical, btnSuspicious, btnHarrasment;
    ImageButton btnCall;
    FirebaseDatabase database;
    FirebaseAuth auth;
    ArrayList<Users> users;
    String latitudefield, longitudefield;
    FusedLocationProviderClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_event_second_page);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        client = LocationServices.getFusedLocationProviderClient(this);

        users = new ArrayList<>();

        ivEmergencySymbol = findViewById(R.id.ivEmergencySymbol);
        ivGuideMap = findViewById(R.id.ivGuideMap);
        tvEmergencyMessage = findViewById(R.id.tvEmergencyMessage);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvMedicalAreYouSure = findViewById(R.id.tvMedicalAreYouSure);
        tvSuspiciousAreYouSure = findViewById(R.id.tvSuspiciousAreYouSure);
        tvHarrasmentAreYouSure = findViewById(R.id.tvHarrasmentAreYouSure);
        btnCall = findViewById(R.id.btnCall);
        btnMedicalYes = findViewById(R.id.btnMedicalYes);
        btnMedicalNo = findViewById(R.id.btnMedicalNo);
        btnSuspiciousNo = findViewById(R.id.btnSuspiciousNo);
        btnSuspiciousYes = findViewById(R.id.btnSuspiciousYes);
        btnHarrasmentNo = findViewById(R.id.btnHarrasmentNo);
        btnHarrasmentYes = findViewById(R.id.btnHarrasmentYes);
        btnMedical = findViewById(R.id.btnMedical);
        btnHarrasment = findViewById(R.id.btnHarrasment);
        btnSuspicious = findViewById(R.id.btnSuspicious);
        btnExitEvent = findViewById(R.id.btnExitEvent);

        ivEmergencySymbol.setVisibility(View.GONE);
        tvEmergencyMessage.setVisibility(View.GONE);
        tvMedicalAreYouSure.setVisibility(View.GONE);
        tvSuspiciousAreYouSure.setVisibility(View.GONE);
        tvHarrasmentAreYouSure.setVisibility(View.GONE);
        btnMedicalYes.setVisibility(View.GONE);
        btnMedicalNo.setVisibility(View.GONE);
        btnSuspiciousYes.setVisibility(View.GONE);
        btnSuspiciousNo.setVisibility(View.GONE);
        btnHarrasmentYes.setVisibility(View.GONE);
        btnHarrasmentNo.setVisibility(View.GONE);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9838337433"));
                startActivity(intent);
            }
        });
        btnMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMedicalAreYouSure.setVisibility(View.VISIBLE);
                btnMedicalYes.setVisibility(View.VISIBLE);
                btnMedicalNo.setVisibility(View.VISIBLE);
                btnMedicalYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LocationInfo locationInfo = new LocationInfo(latitudefield, longitudefield, users.get(0).getName(), users.get(0).getPhone(), "Medical emergency");
                        database.getReference().child("emergency").child(getIntent().getStringExtra("event name")).push().setValue(locationInfo);
                        ivEmergencySymbol.setVisibility(View.VISIBLE);
                        tvEmergencyMessage.setVisibility(View.VISIBLE);
                        tvMedicalAreYouSure.setVisibility(View.GONE);
                        btnMedicalYes.setVisibility(View.GONE);
                        btnMedicalNo.setVisibility(View.GONE);
                    }
                });

                btnMedicalNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnMedicalYes.setVisibility(View.GONE);
                        btnMedicalNo.setVisibility(View.GONE);
                        tvMedicalAreYouSure.setVisibility(View.GONE);
                    }
                });
            }

        });

        btnSuspicious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSuspiciousAreYouSure.setVisibility(View.VISIBLE);
                btnSuspiciousYes.setVisibility(View.VISIBLE);
                btnSuspiciousNo.setVisibility(View.VISIBLE);
                btnSuspiciousYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LocationInfo locationInfo = new LocationInfo(latitudefield, longitudefield, users.get(0).getName(), users.get(0).getPhone(), "Found suspicious activity");
                        database.getReference().child("emergency").child(getIntent().getStringExtra("event name")).push().setValue(locationInfo);
                        ivEmergencySymbol.setVisibility(View.VISIBLE);
                        tvEmergencyMessage.setVisibility(View.VISIBLE);
                        btnSuspiciousYes.setVisibility(View.GONE);
                        btnSuspiciousNo.setVisibility(View.GONE);
                        tvSuspiciousAreYouSure.setVisibility(View.GONE);
                    }
                });

                btnSuspiciousNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnSuspiciousYes.setVisibility(View.GONE);
                        btnSuspiciousNo.setVisibility(View.GONE);
                        tvSuspiciousAreYouSure.setVisibility(View.GONE);
                    }
                });
            }

        });

        btnHarrasment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHarrasmentAreYouSure.setVisibility(View.VISIBLE);
                btnHarrasmentYes.setVisibility(View.VISIBLE);
                btnHarrasmentNo.setVisibility(View.VISIBLE);
                btnHarrasmentYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ivEmergencySymbol.setVisibility(View.VISIBLE);
                        tvEmergencyMessage.setVisibility(View.VISIBLE);
                        btnHarrasmentYes.setVisibility(View.GONE);
                        btnHarrasmentNo.setVisibility(View.GONE);
                        tvHarrasmentAreYouSure.setVisibility(View.GONE);

                        LocationInfo locationInfo = new LocationInfo(latitudefield, longitudefield, users.get(0).getName(), users.get(0).getPhone(), "Harrasment");
                        database.getReference().child("emergency").child(getIntent().getStringExtra("event name")).push().setValue(locationInfo);

                    }
                });

                btnHarrasmentNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnHarrasmentYes.setVisibility(View.GONE);
                        btnHarrasmentNo.setVisibility(View.GONE);
                        tvHarrasmentAreYouSure.setVisibility(View.GONE);
                    }
                });
            }

        });

        database.getReference().child("users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users d = snapshot.getValue(Users.class);
                users.add(d);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



      /*  locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            onLocationChanged(location);
        } else {
           latitudefield = "Location not available";
            longitudefield = "Location not available";
        }

       */
        btnExitEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnterEventSecondPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        if (ActivityCompat.checkSelfPermission(EnterEventSecondPage.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(EnterEventSecondPage.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        if (client.getLastLocation() != null) {
                            client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {


                                    longitudefield = String.valueOf(location.getLongitude());
                                    latitudefield = String.valueOf(location.getLatitude());

                                }
                            });
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }


}