package com.yashakabra05.codebotsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FavouriteCustom extends ArrayAdapter<Images> {
    private ArrayList<Images> values;
    private Context context;
    ImageView iv;
    TextView tvName,tvDate;


    FavouriteCustom(Context context, ArrayList<Images> list) {
        super(context, R.layout.list_view_element, list);
        values = list;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_view_element, parent, false);
        iv=v.findViewById(R.id.ivList);
        tvName=v.findViewById(R.id.tvNameList);
        tvDate=v.findViewById(R.id.tvDateList);
        tvName.setText(values.get(position).getEventName());
        tvDate.setText(values.get(position).getDate());

        if(values.get(position).getImage().equals("eve1"))
        {
            iv.setImageResource(R.drawable.event1);
        }
        else if(values.get(position).getImage().equals("eve2"))
        {
            iv.setImageResource(R.drawable.event2);
        }
        else if(values.get(position).getImage().equals("eve3"))
        {
            iv.setImageResource(R.drawable.event3);
        }
        else if(values.get(position).getImage().equals("eve4"))
        {
            iv.setImageResource(R.drawable.event4);
        }
        else if(values.get(position).getImage().equals("eve5"))
        {
            iv.setImageResource(R.drawable.event5);
        }
        else if(values.get(position).getImage().equals("eve6"))
        {
            iv.setImageResource(R.drawable.event5);
        }
        else if(values.get(position).getImage().equals("eve7"))
        {
            iv.setImageResource(R.drawable.event7);
        }
        else if(values.get(position).getImage().equals("eve8"))
        {
            iv.setImageResource(R.drawable.event8);
        }
        else if(values.get(position).getImage().equals("eve9"))
        {
            iv.setImageResource(R.drawable.event9);
        }
        return v;
    }
}
