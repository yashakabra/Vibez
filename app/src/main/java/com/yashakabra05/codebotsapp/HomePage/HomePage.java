package com.yashakabra05.codebotsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements AdapterPopularEvents.ItemSelected, AdapterAllEvents.ItemSelected2  {

    ImageView ivHome,ivSearch,ivFavourite,ivCalendar;

    //For using Home bar
    final int FILTER_RETURN=1;
    final int SEARCH_RETURN=2;
    final int FAVOURITE_RETURN=3;
    final int CALENDAR_RETURN=4;

    public static ArrayList<Event> list ;
    ArrayList<Event> popularEvents,filteredPopularEvents,filteredAllEvents,favouriteEvents;

    RecyclerView rvPopularEvents,rvAllEvents;
    RecyclerView.Adapter myAdapterPopular,myAdapterAll;
    RecyclerView.LayoutManager layoutmanagerPopular,layoutmanagerAll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ivHome=findViewById(R.id.ivHome);
        ivSearch=findViewById(R.id.ivSearch);
        ivFavourite=findViewById(R.id.ivFavourite);
        ivCalendar=findViewById(R.id.ivCalendar);
        Favourite.editor = getSharedPreferences(Favourite.favouriteDataStore, MODE_PRIVATE).edit();
        rvAllEvents=findViewById(R.id.recyclerview2);
        rvPopularEvents=findViewById(R.id.recyclerview1);
        rvPopularEvents.setHasFixedSize(true);


        rvAllEvents.setHasFixedSize(true);

        ivHome.setImageResource(R.drawable.homec);

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSearch.setImageResource(R.drawable.searchc);
                ivHome.setImageResource(R.drawable.home);
                ivFavourite.setImageResource(R.drawable.favourite);
                ivCalendar.setImageResource(R.drawable.calendar);

                Intent intentSearch=new Intent(HomePage.this,Search.class);
                startActivityForResult(intentSearch,SEARCH_RETURN);
            }
        });

        ivFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSearch.setImageResource(R.drawable.search);
                ivHome.setImageResource(R.drawable.home);
                ivFavourite.setImageResource(R.drawable.favouritec);
                ivCalendar.setImageResource(R.drawable.calendar);

             Intent   intentFavourite=new Intent(HomePage.this,com.yashakabra05.codebotsapp.Favourite.class);
                startActivityForResult(intentFavourite,FAVOURITE_RETURN);

            }
        });

        ivCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSearch.setImageResource(R.drawable.search);
                ivHome.setImageResource(R.drawable.home);
                ivFavourite.setImageResource(R.drawable.favourite);
                ivCalendar.setImageResource(R.drawable.calendarc);

                Intent intentCalendar=new Intent(HomePage.this,Calendar.class);
                startActivityForResult(intentCalendar,CALENDAR_RETURN);
            }
        });

        list=new ArrayList<Event>();       //for all events in home page
        popularEvents=new ArrayList<Event>();      //for all popular events
        filteredPopularEvents=new ArrayList<Event>();     //filter for all events in home page
        filteredAllEvents=new ArrayList<Event>();     //filter for popular events
        favouriteEvents=new ArrayList<Event>(); //for all favorite events

        //refrencing our data from firebase and adding to our array list

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("events");

        ref.addValueEventListener(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(@NonNull DataSnapshot snapshot) {

                                          list.clear();
                                          for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                              Event obj = snapshot1.getValue(Event.class);
                                              list.add(obj);
                                              if(obj.getEvent_pro().equals("yes"))
                                              {
                                                  popularEvents.add(obj);
                                              }
                                          }
                                          myAdapterPopular.notifyDataSetChanged();
                                          myAdapterAll.notifyDataSetChanged();
                                      }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // setting adapters for home feed and popular events

        myAdapterAll=new AdapterAllEvents(HomePage.this,list);
        rvAllEvents.setAdapter(myAdapterAll);

        myAdapterPopular=new AdapterPopularEvents(this,popularEvents);
        rvPopularEvents.setAdapter(myAdapterPopular);

        layoutmanagerPopular=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rvPopularEvents.setLayoutManager(layoutmanagerPopular);


        layoutmanagerAll=new LinearLayoutManager(HomePage.this);
        rvAllEvents.setLayoutManager(layoutmanagerAll);
    }

    //connects action bar to home page

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //on clicking elements of action bar the following intents are called-

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.filter:
                startActivityForResult(new Intent(HomePage.this, com.yashakabra05.codebotsapp.Filter.class),FILTER_RETURN);
                break;
            case R.id.profile:startActivity(new Intent(HomePage.this, ProfileFirstPage.class));
                break;
            case R.id.help:startActivity(new Intent(HomePage.this, com.yashakabra05.codebotsapp.Helpmain.class));
                break;
            case R.id.ContactUs:startActivity(new Intent(HomePage.this, AboutUs.class));
                break;
            case R.id.feedback:startActivity(new Intent(HomePage.this, com.yashakabra05.codebotsapp.Feedback.class));
                break;
            case R.id.event:startActivity(new Intent(HomePage.this, CreateEventFirstActivity.class));
                break;
            case R.id.myevents:startActivity(new Intent(HomePage.this,com.yashakabra05.codebotsapp.MyEventsList.class));
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    // this is the result for the intent called for filter

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String type;
        switch(requestCode)
        {
            case FILTER_RETURN:

            if(resultCode==RESULT_OK)
            {
                filteredPopularEvents.clear();
                filteredAllEvents.clear();

                type=data.getStringExtra("cateogary");
                for(int i=0;i<popularEvents.size();i++)
                {
                    if((popularEvents.get(i).getEvent_type().equals(type)))
                    {
                        filteredPopularEvents.add(list.get(i));
                    }
                }
                for(int i=0;i<list.size();i++)
                {
                    if((list.get(i).getEvent_type().equals(type)))
                    {
                        filteredAllEvents.add(list.get(i));
                    }
                }
                myAdapterPopular.notifyDataSetChanged();

                myAdapterPopular=new AdapterPopularEvents(this,filteredPopularEvents);
                rvPopularEvents.setAdapter(myAdapterPopular);

                myAdapterPopular.notifyDataSetChanged();
                myAdapterAll=new AdapterAllEvents(this,filteredAllEvents);
                rvAllEvents.setAdapter(myAdapterAll);

            }
            else if(resultCode==RESULT_CANCELED)
            {
                Toast.makeText(this, "yod did not filter you choice!", Toast.LENGTH_SHORT).show();
            }
            break;
            case SEARCH_RETURN:if(resultCode==RESULT_CANCELED)
                ivHome.setImageResource(R.drawable.homec);
                ivSearch.setImageResource(R.drawable.search);
                break;
            case FAVOURITE_RETURN:if(resultCode==RESULT_CANCELED)
                ivHome.setImageResource(R.drawable.homec);
                ivFavourite.setImageResource(R.drawable.favourite);
                break;
            case CALENDAR_RETURN:if(resultCode==RESULT_CANCELED)
                ivHome.setImageResource(R.drawable.homec);
                ivCalendar.setImageResource(R.drawable.calendar);
                break;
        }
    }

}
