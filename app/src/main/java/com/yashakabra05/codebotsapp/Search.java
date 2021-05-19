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
    ArrayList<String> names;
    ImageView home, search, favourite, calendar;
    ImageView iv;
    HomePage hm;
    final int homeReturn = 1;
    final int favReturn = 2;
    final int CalendarReturn=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        etEventName = findViewById(R.id.etSearch);
        iv = findViewById(R.id.vcSearch);
        home = findViewById(R.id.ivHome);
        search = findViewById(R.id.ivSearch);
        favourite = findViewById(R.id.ivFavourite);
        calendar = findViewById(R.id.ivCalendar);
        search.setImageResource(R.drawable.searchc);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                home.setImageResource(R.drawable.homec);
                favourite.setImageResource(R.drawable.favourite);
                calendar.setImageResource(R.drawable.calendar);
                Intent intentSearch = new Intent(Search.this, HomePage.class);
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

                Intent intentSearch = new Intent(Search.this, Favourite.class);
                startActivityForResult(intentSearch,favReturn);

            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                home.setImageResource(R.drawable.home);
                favourite.setImageResource(R.drawable.favourite);
                calendar.setImageResource(R.drawable.calendarc);
                Intent intentSearch = new Intent(Search.this, Calendar.class);
                startActivityForResult(intentSearch,CalendarReturn);
            }
        });
        names = new ArrayList<String>();
        hm = new HomePage();

        for (int i = 0; i < HomePage.list.size(); i++) {
            names.add(HomePage.list.get(i).getEventName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.search_autocomplete_element, names);
        etEventName.setThreshold(1);
        etEventName.setAdapter(adapter);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etEventName.getText().toString();
                if (s.isEmpty()) {
                    Toast.makeText(Search.this, "please enter something first", Toast.LENGTH_SHORT).show();
                } else {
                    int count = 0;
                    for (int i = 0; i < HomePage.list.size(); i++) {
                        if ((HomePage.list.get(i).getEventName()).equals(s)) ;
                        {
                            count++;
                            Intent searchIntent = new Intent(Search.this, EventCalled.class);
                            searchIntent.putExtra("name of event", HomePage.list.get(i).getEventName());
                            startActivity(searchIntent);
                            break;

                        }
                    }
                    if (count == 0) {
                        Toast.makeText(hm, "sorry! there is no such event", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case homeReturn:
                if (resultCode == RESULT_CANCELED)
                    home.setImageResource(R.drawable.home);
                search.setImageResource(R.drawable.searchc);
                break;
            case favReturn:
                if (resultCode == RESULT_CANCELED)
                    search.setImageResource(R.drawable.searchc);
                favourite.setImageResource(R.drawable.favourite);
                break;
            case CalendarReturn:
                if (resultCode == RESULT_CANCELED)
                    search.setImageResource(R.drawable.searchc);
                calendar.setImageResource(R.drawable.calendar);
                break;
        }
    }
}