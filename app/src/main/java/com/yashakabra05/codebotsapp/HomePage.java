package com.yashakabra05.codebotsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
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

public class HomePage extends AppCompatActivity implements PersonAdapter.ItemSelected,PersonAdapter2.ItemSelected2  {
//,PersonAdapter2.ItemSelected2

    ImageView home,search,favourite,calendar;
    final int filt=1;
    final int searchReturn=2;
    final int favReturn=3;

final int calendarReturn=4;



    public static ArrayList<Images> list;
    ArrayList<Images> list1,list11;
    ArrayList<Images> list2,list22;
    ArrayList<Images> favourites;
    RecyclerView rv,rv2;
    ListView lv;
    RecyclerView.Adapter myadapter,myadapter2;
    RecyclerView.LayoutManager layoutmanager,layoutmanager2;
    Intent intent;//intentEventCalled;

    public  static ArrayList<String> song,comedy,dance,sports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        home=findViewById(R.id.ivHome);
        search=findViewById(R.id.ivSearch);
        favourite=findViewById(R.id.ivFavourite);
        calendar=findViewById(R.id.ivCalendar);

        rv2=findViewById(R.id.recyclerview2);
        rv2.setHasFixedSize(true);

        rv=findViewById(R.id.recyclerview1);
        rv.setHasFixedSize(true);

        //lv=findViewById(R.id.lv;
        home.setImageResource(R.drawable.homec);
        //intentEventCalled=new Intent(HomePage.this,com.yashakabra05.codebotsapp.EventInformation.class);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.searchc);
                home.setImageResource(R.drawable.home);
                favourite.setImageResource(R.drawable.favourite);
                calendar.setImageResource(R.drawable.calendar);
                Intent intentSearch=new Intent(HomePage.this,Search.class);
                startActivityForResult(intentSearch,searchReturn);
            }
        });

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                home.setImageResource(R.drawable.home);
                favourite.setImageResource(R.drawable.favouritec);
                calendar.setImageResource(R.drawable.calendar);

                intent=new Intent(HomePage.this,com.yashakabra05.codebotsapp.Favourite.class);
                //startActivity(intent);
           /* Bundle args=new Bundle();
            args.putSerializable("ARRAYLIST",favourites);
            intent.putExtra("BUNDLE",args);*/

                startActivityForResult(intent,favReturn);

            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                home.setImageResource(R.drawable.home);
                favourite.setImageResource(R.drawable.favourite);
                calendar.setImageResource(R.drawable.calendarc);
                Intent calendarActivity=new Intent(HomePage.this,Calendar.class);
                startActivityForResult(calendarActivity,calendarReturn);
            }
        });




        String s="delhi";
        list=new ArrayList<Images>();
        list1=new ArrayList<Images>();      //for popular events
        list2=new ArrayList<Images>();      //
        list11=new ArrayList<Images>();
        list22=new ArrayList<Images>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("events");

        ref.addValueEventListener(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(@NonNull DataSnapshot snapshot) {

                                          list.clear();
                                          for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                              Images d = snapshot1.getValue(Images.class);
                                              list.add(d);
                                              if(d.getEvent_pro().equals("yes"))
                                              {
                                                  list1.add(d);
                                              }
                                          }
                                          myadapter.notifyDataSetChanged();
                                          myadapter2.notifyDataSetChanged();
                                      }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        favourites=new ArrayList<Images>();
        /*
        for(int i=0;i<list.size();i++)
        {
                if(list.get(i).getEvent_pro().equals("yes"))
                {
                    list1.add(list.get(i));
                }
        }
         */
        myadapter2=new PersonAdapter2(HomePage.this,list);
        rv2.setAdapter(myadapter2);

        myadapter=new PersonAdapter(this,list1);
        rv.setAdapter(myadapter);

        layoutmanager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rv.setLayoutManager(layoutmanager);


        layoutmanager2=new LinearLayoutManager(HomePage.this);
        rv2.setLayoutManager(layoutmanager2);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.filter:
                startActivityForResult(new Intent(HomePage.this, com.yashakabra05.codebotsapp.Filter.class),filt);
                break;
            case R.id.profile:startActivity(new Intent(HomePage.this, com.yashakabra05.codebotsapp.Profile_firstpage.class));
                break;
            case R.id.Help:startActivity(new Intent(HomePage.this, com.yashakabra05.codebotsapp.Helpmain.class));
                break;
            case R.id.ContactUs:startActivity(new Intent(HomePage.this, com.yashakabra05.codebotsapp.contactus.class));
                break;
            case R.id.feedback:startActivity(new Intent(HomePage.this, com.yashakabra05.codebotsapp.Feedback.class));
                break;
            case R.id.event:startActivity(new Intent(HomePage.this, com.yashakabra05.codebotsapp.Create_event_first_Activity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    @Override
    public void onItemImage(int index) {
        intentEventCalled.putExtra("name of event",list2.get(index).getEvent_name());
        startActivity(intentEventCalled);
    }

    @Override
    public void onItemSelectedEvent(int index) {
        SharedPreferences.Editor editor=getSharedPreferences(Favourite.favouriteDataStore,MODE_PRIVATE).edit();

        editor.putString(list2.get(index).getEvent_name(),list2.get(index).getEvent_name());
        editor.commit();
       // i++;
     //  Favourite.items.add(list2.get(index));

//favourites.add(list2.get(i));
    }*/

    @Override       //this below programme is for filter!!
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String type;
        switch(requestCode)
        {


            case filt:

            if(resultCode==RESULT_OK)
            {
                list11.clear();
                list22.clear();

                type=data.getStringExtra("cateogary");
                for(int i=0;i<list1.size();i++)
                {
                    if((list1.get(i).getEvent_type().equals(type)))
                    {
                        list11.add(list.get(i));
                    }
                }
                for(int i=0;i<list.size();i++)
                {
                    if((list.get(i).getEvent_type().equals(type)))
                    {
                        list22.add(list.get(i));
                    }
                }
                myadapter.notifyDataSetChanged();

                myadapter=new PersonAdapter(this,list11);
                rv.setAdapter(myadapter);

                myadapter2.notifyDataSetChanged();
                myadapter2=new PersonAdapter2(this,list11);
                rv2.setAdapter(myadapter2);

            }
            else if(resultCode==RESULT_CANCELED)
            {
                Toast.makeText(this, "yod did not filter you choice!", Toast.LENGTH_SHORT).show();
            }
            break;
           case searchReturn:if(resultCode==RESULT_CANCELED)
                home.setImageResource(R.drawable.homec);
                search.setImageResource(R.drawable.search);
                break;
            case favReturn:if(resultCode==RESULT_CANCELED)
                home.setImageResource(R.drawable.homec);
                favourite.setImageResource(R.drawable.favourite);
                break;
            case calendarReturn:if(resultCode==RESULT_CANCELED)
                home.setImageResource(R.drawable.homec);
                calendar.setImageResource(R.drawable.calendar);
                break;
        }
    }

}
