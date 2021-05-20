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

import com.squareup.picasso.Picasso;

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
        tvName.setText(values.get(position).getEvent_name());
        tvDate.setText(values.get(position).getDate());
        Picasso.get().load(values.get(position).getEvent_pic()).placeholder(R.mipmap.ic_event).into(iv);

        return v;
    }
}
