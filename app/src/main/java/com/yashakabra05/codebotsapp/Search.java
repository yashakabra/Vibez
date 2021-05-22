package com.yashakabra05.codebotsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    AutoCompleteTextView etEventName;

    ArrayList<String> namesOfEvent;

    ImageView ivHome, ivSearch, ivFavourite, ivCalendar,iv;

    HomePage hm= new HomePage();

    final int HOME_RETURN = 1;
    final int FAV_RETURN = 2;
    final int CALENDAR_RETURN=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etEventName = findViewById(R.id.etSearch);
        iv = findViewById(R.id.vcSearch);
        ivHome = findViewById(R.id.ivHome);
        ivSearch = findViewById(R.id.ivSearch);
        ivFavourite = findViewById(R.id.ivFavourite);
        ivCalendar = findViewById(R.id.ivCalendar);

        ivSearch.setImageResource(R.drawable.searchc);

        ivHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ivSearch.setImageResource(R.drawable.search);
                ivHome.setImageResource(R.drawable.homec);
                ivFavourite.setImageResource(R.drawable.favourite);
                ivCalendar.setImageResource(R.drawable.calendar);
                Intent intentHome = new Intent(Search.this, HomePage.class);
                startActivityForResult(intentHome,HOME_RETURN);
            }
        });

       ivFavourite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ivSearch.setImageResource(R.drawable.search);
                ivHome.setImageResource(R.drawable.home);
                ivFavourite.setImageResource(R.drawable.favouritec);
                ivCalendar.setImageResource(R.drawable.calendar);

                Intent intentFavourite = new Intent(Search.this, Favourite.class);
                startActivityForResult(intentFavourite,FAV_RETURN);
            }
        });

        ivCalendar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ivSearch.setImageResource(R.drawable.search);
                ivHome.setImageResource(R.drawable.home);
                ivFavourite.setImageResource(R.drawable.favourite);
                ivCalendar.setImageResource(R.drawable.calendarc);
                Intent intentCalendar= new Intent(Search.this, Calendar.class);
                startActivityForResult(intentCalendar,CALENDAR_RETURN);
            }
        });

        namesOfEvent = new ArrayList<String>();

        for (int i = 0; i < HomePage.list.size(); i++) {

            namesOfEvent.add(HomePage.list.get(i).getEvent_name());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.search_autocomplete_element, namesOfEvent);
        etEventName.setThreshold(1);
        etEventName.setAdapter(adapter);

        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String s = etEventName.getText().toString();
                int t = 0;

                for (int i = 0; i < HomePage.list.size(); i++) {

                    if (s.equals(HomePage.list.get(i).getEvent_name())) {

                        t = 1;
                        Intent searchIntent = new Intent(Search.this, EventInformation.class);
                        searchIntent.putExtra("event_pic", HomePage.list.get(i).getEvent_pic());
                        searchIntent.putExtra("event_name", HomePage.list.get(i).getEvent_name());
                        searchIntent.putExtra("event_date", HomePage.list.get(i).getDate());
                        searchIntent.putExtra("event_time", HomePage.list.get(i).getTime());
                        searchIntent.putExtra("event_info", HomePage.list.get(i).getInfo());
                        searchIntent.putExtra("event_location", HomePage.list.get(i).getLocation());
                        searchIntent.putExtra("event_tcost", HomePage.list.get(i).getT_cost());
                        startActivity(searchIntent);
                        break;
                    }
                }

                if(t==0)
                {
                    Toast.makeText(hm, "sorry! there is no such event", Toast.LENGTH_SHORT).show();
                }
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
                ivSearch.setImageResource(R.drawable.searchc);
                break;
            case FAV_RETURN:
                if (resultCode == RESULT_CANCELED)
                    ivSearch.setImageResource(R.drawable.searchc);
                ivFavourite.setImageResource(R.drawable.favourite);
                break;
            case CALENDAR_RETURN:
                if (resultCode == RESULT_CANCELED)
                    ivSearch.setImageResource(R.drawable.searchc);
                ivCalendar.setImageResource(R.drawable.calendar);
                break;
        }
    }
}