package com.yashakabra05.codebotsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavouriteCustom extends ArrayAdapter<Images> {

    private ArrayList<Images> values;

    private Context context;

    ImageView iv;

    itemselec8 activ;

    TextView tvName,tvDate;

    FavouriteCustom(Context context, ArrayList<Images> list) {

        super(context, R.layout.list_view_element, list);
        values = list;
        this.context = context;
        activ = (itemselec8) context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_view_element, parent, false);

        iv=v.findViewById(R.id.ivList);
        tvName=v.findViewById(R.id.tvNameList);
        tvDate=v.findViewById(R.id.tvDateList);
        tvName.setText(values.get(position).getEvent_name());
        tvDate.setText(values.get(position).getDate());
        Picasso.get().load(values.get(position).getEvent_pic()).placeholder(R.mipmap.ic_event).into(iv);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent((Context) activ,EventInformation.class);
                it.putExtra("event_pic",values.get(position).getEvent_pic());
                it.putExtra("event_name",values.get(position).getEvent_name());
                it.putExtra("event_date",values.get(position).getDate());
                it.putExtra("event_time",values.get(position).getTime());
                it.putExtra("event_info",values.get(position).getInfo());
                it.putExtra("event_location",values.get(position).getLocation());
                it.putExtra("event_tcost",values.get(position).getT_cost());
                ((Context) activ).startActivity(it);
            }
        });

        return v;
    }

    public interface itemselec8 {
    }

}
