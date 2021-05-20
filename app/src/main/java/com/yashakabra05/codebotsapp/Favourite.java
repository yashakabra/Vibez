package com.yashakabra05.codebotsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yashakabra05.codebotsapp.Class.Images;

import java.util.ArrayList;

public class Favourite extends AppCompatActivity {
    public static  SharedPreferences.Editor editor;
    public static final String favouriteDataStore="favouriteData";
    final int homeReturn = 1;
    final int searchReturn = 2;
    final int calendarReturn = 3;

    ListView lv;



    public static  ArrayList<Images> items=new ArrayList<Images>();
    ArrayList<Images> items2=new ArrayList<Images>();
    ImageView home,search,favourite,calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        lv=findViewById(R.id.lv);
        home=findViewById(R.id.ivHome);
        search=findViewById(R.id.ivSearch);
        favourite=findViewById(R.id.ivFavourite);
        calendar=findViewById(R.id.ivCalendar);
        favourite.setImageResource(R.drawable.favouritec);
        SharedPreferences prefrences=getSharedPreferences(favouriteDataStore, MODE_PRIVATE);
        for(int i=0;i<HomePage.list.size();i++)
        {
            String nameOfEvent=prefrences.getString(HomePage.list.get(i).getEvent_name(),null);
            if(nameOfEvent!=null)
            {
                items2.add(HomePage.list.get(i));
            }

        }
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                home.setImageResource(R.drawable.homec);
                favourite.setImageResource(R.drawable.favourite);
                calendar.setImageResource(R.drawable.calendar);
                Intent intentSearch=new Intent(Favourite.this,HomePage.class);
                startActivityForResult(intentSearch,homeReturn);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                home.setImageResource(R.drawable.home);
                favourite.setImageResource(R.drawable.favouritec);
                calendar.setImageResource(R.drawable.calendar);

                Intent intentSearch=new Intent(Favourite.this,Search.class);
                startActivityForResult(intentSearch,searchReturn);

            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                home.setImageResource(R.drawable.home);
                favourite.setImageResource(R.drawable.favourite);
                calendar.setImageResource(R.drawable.calendarc);
                Intent intentSearch=new Intent(Favourite.this, Calendar.class);
                startActivityForResult(intentSearch,calendarReturn);
            }
        });

       /* DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("my favs");

        ref.addValueEventListener(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(@NonNull DataSnapshot snapshot) {

                                          items.clear();
                                          for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                              Images d = snapshot1.getValue(Images.class);
                                              items.add(d);
                                              }
                                          }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
                                      });*/

            FavouriteCustom adapter=new FavouriteCustom(this, items2);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case homeReturn:
                if (resultCode == RESULT_CANCELED)
                    home.setImageResource(R.drawable.home);
                favourite.setImageResource(R.drawable.favouritec);
                break;
            case searchReturn:
                if (resultCode == RESULT_CANCELED)
                    search.setImageResource(R.drawable.search);
                favourite.setImageResource(R.drawable.favouritec);
                break;
            case calendarReturn:
                if (resultCode == RESULT_CANCELED)
                    calendar.setImageResource(R.drawable.calendar);
                favourite.setImageResource(R.drawable.favouritec);
                break;
        }
    }
}