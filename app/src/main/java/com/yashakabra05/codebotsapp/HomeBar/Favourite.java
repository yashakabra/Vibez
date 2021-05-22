package com.yashakabra05.codebotsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Favourite extends AppCompatActivity implements FavouriteCustom.itemselec8 {
    public static  SharedPreferences.Editor editor;
    public static final String favouriteDataStore="favouriteData";
    final int HOME_RETURN = 1;
    final int SEARCH_RETURN = 2;
    final int CALENDAR_RETURN= 3;

    ListView lv;



    public static  ArrayList<Event> items=new ArrayList<Event>();
    ArrayList<Event> items2=new ArrayList<Event>();
    ImageView ivHome,ivSearch,ivFavourite,ivCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        lv=findViewById(R.id.lv);
        ivHome=findViewById(R.id.ivHome);
        ivSearch=findViewById(R.id.ivSearch);
        ivFavourite=findViewById(R.id.ivFavourite);
        ivCalendar=findViewById(R.id.ivCalendar);
        ivFavourite.setImageResource(R.drawable.favouritec);
        SharedPreferences prefrences=getSharedPreferences(favouriteDataStore, MODE_PRIVATE);
        for(int i=0;i<HomePage.list.size();i++)
        {
            String nameOfEvent=prefrences.getString(HomePage.list.get(i).getEvent_name(),null);
            if(nameOfEvent!=null)
            {
                items2.add(HomePage.list.get(i));
            }

        }
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSearch.setImageResource(R.drawable.search);
                ivHome.setImageResource(R.drawable.homec);
                ivFavourite.setImageResource(R.drawable.favourite);
                ivCalendar.setImageResource(R.drawable.calendar);
                Intent intentHome=new Intent(Favourite.this,HomePage.class);
                startActivityForResult(intentHome,HOME_RETURN);
            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSearch.setImageResource(R.drawable.search);
                ivHome.setImageResource(R.drawable.home);
                ivFavourite.setImageResource(R.drawable.favouritec);
                ivCalendar.setImageResource(R.drawable.calendar);

                Intent intentSearch=new Intent(Favourite.this,Search.class);
                startActivityForResult(intentSearch,SEARCH_RETURN);

            }
        });

        ivCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ivSearch.setImageResource(R.drawable.search);
                ivHome.setImageResource(R.drawable.home);
                ivFavourite.setImageResource(R.drawable.favourite);
                ivCalendar.setImageResource(R.drawable.calendarc);
                Intent intentCalendar=new Intent(Favourite.this, Calendar.class);
                startActivityForResult(intentCalendar,CALENDAR_RETURN);
            }
        });



            FavouriteCustom adapter=new FavouriteCustom(this, items2);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case HOME_RETURN:
                if (resultCode == RESULT_CANCELED)
                    ivHome.setImageResource(R.drawable.home);
                ivFavourite.setImageResource(R.drawable.favouritec);
                break;
            case SEARCH_RETURN:
                if (resultCode == RESULT_CANCELED)
                    ivSearch.setImageResource(R.drawable.search);
                ivFavourite.setImageResource(R.drawable.favouritec);
                break;
            case CALENDAR_RETURN:
                if (resultCode == RESULT_CANCELED)
                    ivCalendar.setImageResource(R.drawable.calendar);
                ivFavourite.setImageResource(R.drawable.favouritec);
                break;
        }
    }
}