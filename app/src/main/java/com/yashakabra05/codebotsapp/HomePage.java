package com.yashakabra05.codebotsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements PersonAdapter.ItemSelected,PersonAdapter2.ItemSelected2 {
    ImageView home,search,favourite,calendar;
    final int filt=1;




    public static ArrayList<Images> list;
    ArrayList<Images> list1,list11;
    ArrayList<Images> list2,list22;
    ArrayList<Images> favourites;
    RecyclerView rv,rv2;
    ListView lv;
    RecyclerView.Adapter myadapter,myadapter2;
    RecyclerView.LayoutManager layoutmanager,layoutmanager2;
    Intent intent,intentEventCalled;

    public  static ArrayList<String> song,comedy,dance,sports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        home=findViewById(R.id.ivHome);
        search=findViewById(R.id.ivSearch);
        favourite=findViewById(R.id.ivFavourite);
        calendar=findViewById(R.id.ivCalendar);

        //lv=findViewById(R.id.lv;
        home.setImageResource(R.drawable.homec);
        intentEventCalled=new Intent(HomePage.this,com.yashakabra05.codebotsapp.EventCalled.class);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.searchc);
                home.setImageResource(R.drawable.home);
                favourite.setImageResource(R.drawable.favourite);
                calendar.setImageResource(R.drawable.calendar);
                Intent intentSearch=new Intent(HomePage.this,Search.class);
                startActivity(intentSearch);
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

                startActivity(intent);

            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                home.setImageResource(R.drawable.home);
                favourite.setImageResource(R.drawable.favourite);
                calendar.setImageResource(R.drawable.calendarc);
            }
        });
        rv=findViewById(R.id.recyclerview1);
        rv.setHasFixedSize(true);
        String s="delhi";
        list=new ArrayList<Images>();
        list1=new ArrayList<Images>();
        list2=new ArrayList<Images>();
        list11=new ArrayList<Images>();
        list22=new ArrayList<Images>();
        favourites=new ArrayList<Images>();
        list.add(new Images("delhi","ganesh chaturthi","11/11/2001","price: 500","song","eve1","yes"));
        list.add(new Images("delhi","dipawali","11/11/2002","price: 500","dance","eve2","yes"));
        list.add(new Images("delhi","holi","11/11/2003","price: 500","sports","eve3","yes"));
        list.add(new Images("delhi","happy new year","11/11/2001","price: 500","song","eve1","yes"));
        for(int i=0;i<list.size();i++)
        {
            if((list.get(i).getCityName().equals(s)))
            {
                if(list.get(i).getPopularity().equals("yes"))
                {
                    list1.add(list.get(i));
                }
                list2.add(list.get(i));


            }
        }

        myadapter=new PersonAdapter(this,list1);
        rv.setAdapter(myadapter);



        layoutmanager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rv.setLayoutManager(layoutmanager);
        rv2=findViewById(R.id.recyclerview2);
        rv2.setHasFixedSize(true);


        myadapter2=new PersonAdapter2(this,list2);
        rv2.setAdapter(myadapter2);
        layoutmanager2=new LinearLayoutManager(this);
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
            case R.id.profile:startActivity(new Intent(HomePage.this, com.yashakabra05.codebotsapp.Profile.class));
                break;
            case R.id.Help:startActivity(new Intent(HomePage.this, com.yashakabra05.codebotsapp.help.class));
                break;
            case R.id.ContactUs:startActivity(new Intent(HomePage.this, com.yashakabra05.codebotsapp.contactus.class));
                break;
            case R.id.feedback:startActivity(new Intent(HomePage.this, com.yashakabra05.codebotsapp.Feedback.class));
                break;
            case R.id.event:startActivity(new Intent(HomePage.this, com.yashakabra05.codebotsapp.event.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemImage(int index) {
        intentEventCalled.putExtra("name of event",list2.get(index).getEventName());
        startActivity(intentEventCalled);
    }

    @Override
    public void onItemSelectedEvent(int index) {
        Favourite.items.add(list2.get(index));
//favourites.add(list2.get(i));



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String type;
        if(requestCode==filt)
        {
            if(resultCode==RESULT_OK)
            {
                list11.clear();
                list22.clear();

                type=data.getStringExtra("cateogary");
                for(int i=0;i<list1.size();i++)
                {
                    if((list1.get(i).getCateogary().equals(type)))
                    {
                        list11.add(list1.get(i));
                    }
                }
                for(int i=0;i<list2.size();i++)
                {
                    if((list2.get(i).getCateogary().equals(type)))
                    {
                        list22.add(list2.get(i));
                    }
                }
                myadapter.notifyDataSetChanged();

                myadapter=new PersonAdapter(this,list11);
                rv.setAdapter(myadapter);

                myadapter2.notifyDataSetChanged();
                myadapter2=new PersonAdapter2(this,list22);
                rv2.setAdapter(myadapter2);





            }
            else if(resultCode==RESULT_CANCELED)
            {
                Toast.makeText(this, "yod did not filter you choice!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
