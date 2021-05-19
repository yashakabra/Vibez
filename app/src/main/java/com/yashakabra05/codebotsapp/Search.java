package com.yashakabra05.codebotsapp;

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
    ImageView iv;
    HomePage hm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        etEventName=findViewById(R.id.etSearch);
        iv=findViewById(R.id.vcSearch);
        names=new ArrayList<String>();
        hm=new HomePage();

        for(int i=0;i<HomePage.list.size();i++)
        {
            names.add(HomePage.list.get(i).getEvent_name());
        }
       ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.search_autocomplete_element,names);
        etEventName.setThreshold(1);
        etEventName.setAdapter(adapter);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=etEventName.getText().toString();
                if(s.isEmpty())
                {
                    Toast.makeText(Search.this, "please enter something first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int count =0;
                    for(int i=0;i<HomePage.list.size();i++)
                    {
                        if((HomePage.list.get(i).getEvent_name()).equals(s));
                        {
                            count++;
                            Intent searchIntent=new Intent(Search.this,EventCalled.class);
                            searchIntent.putExtra("name of event",HomePage.list.get(i).getEvent_name());
                            startActivity(searchIntent);
                            break;

                        }
                    }
                    if(count==0)
                    {
                        Toast.makeText(hm, "sorry! there is no such event", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}