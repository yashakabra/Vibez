package com.yashakabra05.codebotsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class Calendar extends AppCompatActivity implements CalendarCustomClass.itemselec5{

    final int HOME_RETURN = 1;
    final int FAV_RETURN= 2;
    final int SEARCH_RETURN=3;

    ListView lv;
    ArrayList<Event> elementsInCalendar=new ArrayList<Event>();
    ImageView ivHome,ivSearch,ivFavourite,ivCalendar;

    SharedPreferences preferences;
    final String file_name="Calendar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);
        lv=findViewById(R.id.lvCalendar);
        ivHome=findViewById(R.id.ivHome);
        ivSearch=findViewById(R.id.ivSearch);
        ivFavourite=findViewById(R.id.ivFavourite);
        ivCalendar=findViewById(R.id.ivCalendar);
        ivCalendar.setImageResource(R.drawable.calendarc);

        load();
        String eventname = getIntent().getStringExtra("nameofevent");

        if (eventname != null) {

            for (int t = 0; t < HomePage.list.size(); t++) {

                if (HomePage.list.get(t).getEvent_name().equals(eventname)) {

                    SharedPreferences.Editor editor=getSharedPreferences(file_name,MODE_PRIVATE).edit();

                    editor.putString(eventname,eventname);
                    editor.commit();
                }

            }
        }

        load();
        CalendarCustomClass  cc=new CalendarCustomClass(this,elementsInCalendar);
        lv.setAdapter(cc);

        ivHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ivSearch.setImageResource(R.drawable.search);
                ivHome.setImageResource(R.drawable.homec);
                ivFavourite.setImageResource(R.drawable.favourite);
                ivCalendar.setImageResource(R.drawable.calendar);
                Intent intentSearch = new Intent(Calendar.this, HomePage.class);
                startActivityForResult(intentSearch,HOME_RETURN);
            }
        });

        ivFavourite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               ivSearch.setImageResource(R.drawable.search);
                ivHome.setImageResource(R.drawable.home);
                ivFavourite.setImageResource(R.drawable.favouritec);
                ivCalendar.setImageResource(R.drawable.calendar);

                Intent intentSearch = new Intent(Calendar.this, Favourite.class);
                startActivityForResult(intentSearch,FAV_RETURN);
            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ivSearch.setImageResource(R.drawable.search);
                ivHome.setImageResource(R.drawable.home);
                ivFavourite.setImageResource(R.drawable.favourite);
                ivCalendar.setImageResource(R.drawable.calendarc);
                Intent intentSearch = new Intent(Calendar.this, Search.class);
                startActivityForResult(intentSearch,SEARCH_RETURN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case HOME_RETURN:
                if (resultCode == RESULT_CANCELED)
                    ivHome.setImageResource(R.drawable.home);
                ivCalendar.setImageResource(R.drawable.calendarc);
                break;
            case FAV_RETURN:
                if (resultCode == RESULT_CANCELED)
                    ivCalendar.setImageResource(R.drawable.calendarc);
                ivFavourite.setImageResource(R.drawable.favourite);
                break;
            case SEARCH_RETURN:
                if (resultCode == RESULT_CANCELED)
                    ivSearch.setImageResource(R.drawable.search);
                ivCalendar.setImageResource(R.drawable.calendarc);
                break;
        }
    }

    public   void load()
    {
        elementsInCalendar.clear();
        preferences=getSharedPreferences(file_name,MODE_PRIVATE);
        for(int i=0;i<HomePage.list.size();i++)
        {
            String nameOfEvent=preferences.getString(HomePage.list.get(i).getEvent_name(),null);
            if(nameOfEvent!=null)
            {
                elementsInCalendar.add(HomePage.list.get(i));
            }
        }
    }

}