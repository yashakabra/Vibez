package com.yashakabra05.codebotsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CalendarCustomClass extends ArrayAdapter<Images> {
    ArrayList<Images> calendarElements;
    Context context;
    TextView tvName,tvDate,tvTime;
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


        return v;
}}
