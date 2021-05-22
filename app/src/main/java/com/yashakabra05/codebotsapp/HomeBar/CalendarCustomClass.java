package com.yashakabra05.codebotsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CalendarCustomClass extends ArrayAdapter<Event> {

    ArrayList<Event> calendarElements;

    TextView tvName,tvDate,tvTime;

    Context context;

    Button btn_enterevent;

    itemselec5 activi;

    public interface itemselec5 {
    }

    CalendarCustomClass(Context context,ArrayList<Event> calendarElements)
    {
        super(context,R.layout.calendar_particular_element,calendarElements);
        activi = (itemselec5) context;
        this.context = context;
        this.calendarElements=calendarElements;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.calendar_particular_element, parent, false);
        tvName=v.findViewById(R.id.tvNameCalendar);
        tvDate=v.findViewById(R.id.tvDateOfEvent);
        tvTime=v.findViewById(R.id.tvTimeOfEvent);
        btn_enterevent = v.findViewById(R.id.btn_enterevent);
        tvName.setText(calendarElements.get(position).getEvent_name());
        tvDate.setText(calendarElements.get(position).getDate());
        tvTime.setText(calendarElements.get(position).getTime());

        btn_enterevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent((Context) activi,AskQuery.class);
                it .putExtra("event name",tvName.getText().toString());
                it.putExtra("event loc", calendarElements.get(position).location);
                it.putExtra("guide map url",calendarElements.get(position).getGuide_pic());
                it.putExtra("tel1",calendarElements.get(position).getManager_num());
                ((Context) activi).startActivity(it);
            }
        });

        return v;
    }


}