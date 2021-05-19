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
    Context context;
    TextView tvName,tvDate,tvTime;
    Button btn_enterevent;
    CalendarCustomClass(Context context,ArrayList<Images> calendarElements)
    {
        super(context,R.layout.calendar_particular_element,calendarElements);
        this.context=context;
        this.calendarElements=calendarElements;
    }

    public class ViewHolder
    {
        public ViewHolder(@NonNull View v) {
            super();
            tvName=v.findViewById(R.id.tvNameCalendar);
            tvDate=v.findViewById(R.id.tvDateOfEvent);
            tvTime=v.findViewById(R.id.tvTimeOfEvent);
            btn_enterevent = v.findViewById(R.id.btn_enterevent);
            btn_enterevent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.calendar_particular_element, parent, false);
        return v;
    }
}