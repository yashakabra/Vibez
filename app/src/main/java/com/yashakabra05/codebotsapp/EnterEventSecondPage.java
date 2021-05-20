package com.yashakabra05.codebotsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EnterEventSecondPage extends AppCompatActivity {
ImageView ivEmergencySymbol,ivGuideMap;
TextView tvEmergencyMessage,tvPhoneNumber,tvMedicalAreYouSure,tvSuspiciousAreYouSure,tvHarrasmentAreYouSure;
Button btnMedicalYes,btnMedicalNo,btnSuspiciousNo,btnSuspiciousYes,btnHarrasmentYes,btnHarrasmentNo,btnExitEvent;
Button btnMedical,btnSuspicious,btnHarrasment;
ImageButton btnCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_event_second_page);
        ivEmergencySymbol=findViewById(R.id.ivEmergencySymbol);
        ivGuideMap=findViewById(R.id.ivGuideMap);
        tvEmergencyMessage=findViewById(R.id.tvEmergencyMessage);
        tvPhoneNumber=findViewById(R.id.tvPhoneNumber);
        tvMedicalAreYouSure=findViewById(R.id.tvMedicalAreYouSure);
        tvSuspiciousAreYouSure=findViewById(R.id.tvSuspiciousAreYouSure);
        tvHarrasmentAreYouSure=findViewById(R.id.tvHarrasmentAreYouSure);
        btnCall=findViewById(R.id.btnCall);
        btnMedicalYes=findViewById(R.id.btnMedicalYes);
        btnMedicalNo=findViewById(R.id.btnMedicalNo);
        btnSuspiciousNo=findViewById(R.id.btnSuspiciousNo);
        btnSuspiciousYes=findViewById(R.id.btnSuspiciousYes);
        btnHarrasmentNo=findViewById(R.id.btnHarrasmentNo);
        btnHarrasmentYes=findViewById(R.id.btnHarrasmentYes);
        btnMedical=findViewById(R.id.btnMedical);
        btnHarrasment=findViewById(R.id.btnHarrasment);
        btnSuspicious=findViewById(R.id.btnSuspicious);
        btnExitEvent=findViewById(R.id.btnExitEvent);

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
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9838337433"));
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
                    }
                });

                btnHarrasmentNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnSuspiciousYes.setVisibility(View.GONE);
                        btnSuspiciousNo.setVisibility(View.GONE);
                        tvHarrasmentAreYouSure.setVisibility(View.GONE);
                    }
                });
            }

        });
btnExitEvent.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});


    }
}