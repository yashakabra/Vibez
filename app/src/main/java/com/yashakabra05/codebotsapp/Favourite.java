package com.yashakabra05.codebotsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class Favourite extends AppCompatActivity {
ListView lv;
    final int homeReturn = 1;
    final int searchReturn = 2;
    public static final String favouriteDataStore="favouriteDataStore";
  public static  ArrayList<Images> items=new ArrayList<Images>();
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
        items.clear();
        SharedPreferences prefrences=getSharedPreferences(favouriteDataStore,MODE_PRIVATE);
        for(int i=0;i<HomePage.list.size();i++)
        {
        String nameOfEvent=prefrences.getString(HomePage.list.get(i).getEvent_name(),null);
        if(nameOfEvent!=null)
        {

                    items.add(HomePage.list.get(i));
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
                startActivity(intentSearch);
            }
        });



        FavouriteCustom adapter=new FavouriteCustom(this, items);
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
        }
    }
}