package com.yashakabra05.codebotsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarCustomClass extends ArrayAdapter<Images> {

    ArrayList<Images> calendarElements;

    TextView tvName,tvDate,tvTime;

    Context context;

    Button btn_enterevent;

    CalendarCustomClass(Context context,ArrayList<Images> calendarElements)
    {
        super(context,R.layout.calendar_particular_element,calendarElements);
        this.context=context;
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
        tvName.setText(calendarElements.get(position).getEvent_name());
        tvDate.setText(calendarElements.get(position).getDate());
        tvTime.setText(calendarElements.get(position).getTime());

        return v;
    }
}