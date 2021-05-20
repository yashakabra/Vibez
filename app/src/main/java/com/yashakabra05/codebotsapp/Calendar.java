package com.yashakabra05.codebotsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class Calendar extends AppCompatActivity {
    ListView lv;
    ArrayList<Images> elementsInCalendar=new ArrayList<Images>();
    ImageView home,search,favourite,calendar;
    final int homeReturn = 1;
    final int favReturn = 2;
    final int searchReturn=3;
    SharedPreferences preferences;
    final String file_name="Calendar";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        lv=findViewById(R.id.lvCalendar);
        home=findViewById(R.id.ivHome);
        search=findViewById(R.id.ivSearch);
        favourite=findViewById(R.id.ivFavourite);
        calendar=findViewById(R.id.ivCalendar);
        calendar.setImageResource(R.drawable.calendarc);

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
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                home.setImageResource(R.drawable.homec);
                favourite.setImageResource(R.drawable.favourite);
                calendar.setImageResource(R.drawable.calendar);
                Intent intentSearch = new Intent(Calendar.this, HomePage.class);
                startActivityForResult(intentSearch,homeReturn);
            }
        });
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                home.setImageResource(R.drawable.home);
                favourite.setImageResource(R.drawable.favouritec);
                calendar.setImageResource(R.drawable.calendar);

                Intent intentSearch = new Intent(Calendar.this, Favourite.class);
                startActivityForResult(intentSearch,favReturn);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                home.setImageResource(R.drawable.home);
                favourite.setImageResource(R.drawable.favourite);
                calendar.setImageResource(R.drawable.calendarc);
                Intent intentSearch = new Intent(Calendar.this, Search.class);
                startActivityForResult(intentSearch,searchReturn);
            }
        });

     /*   String eventname = getIntent().getStringExtra("nameofevent");
        for(int t=0;t<HomePage.list.size();t++)
        {
            if(HomePage.list.get(t).getEvent_name().equals(eventname))
            {
                elementsInCalendar.add(HomePage.list.get(t));
            }

        }*/

        //elementsInCalendar.add(new Images("lucknow","eid","11112001","500","song","img1","yes"));//this was for demo dont use it

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case homeReturn:
                if (resultCode == RESULT_CANCELED)
                    home.setImageResource(R.drawable.home);
                calendar.setImageResource(R.drawable.calendarc);
                break;
            case favReturn:
                if (resultCode == RESULT_CANCELED)
                    calendar.setImageResource(R.drawable.calendarc);
                favourite.setImageResource(R.drawable.favourite);
                break;
            case searchReturn:
                if (resultCode == RESULT_CANCELED)
                    search.setImageResource(R.drawable.search);
                calendar.setImageResource(R.drawable.calendarc);
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