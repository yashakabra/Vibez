package com.yashakabra05.codebotsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyEventsListAdapter extends RecyclerView.Adapter<MyEventsListAdapter.ViewHolder> {

    ArrayList<Images> my_event_list;

    public MyEventsListAdapter(Context context, ArrayList<Images> list) {
        super();
        my_event_list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_event_image;
        TextView tv_event_name, tv_event_date, tv_nrtickets;

        public ViewHolder( View itemView) {
            super(itemView);
            iv_event_image = itemView.findViewById(R.id.iv_event_image);
            tv_event_name = itemView.findViewById(R.id.tv_event_name);
            tv_event_date = itemView.findViewById(R.id.tv_event_date);
            tv_nrtickets = itemView.findViewById(R.id.tv_nrtickets);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.myeventslistelement,parent,false);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder( MyEventsListAdapter.ViewHolder holder, int position) {
            holder.tv_nrtickets.setText(my_event_list.get(position).getT_num());
            holder.tv_event_name.setText(my_event_list.get(position).getEvent_name());
            Picasso.get().load(my_event_list.get(position).getEvent_pic()).placeholder(R.mipmap.ic_event).into(holder.iv_event_image);
            holder.tv_event_date.setText(my_event_list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return my_event_list.size();
    }
}
