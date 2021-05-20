package com.yashakabra05.codebotsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
{
    ArrayList<Images> events;

    Context context;
    ItemSelected activity;

    //created interface for adapter and home page

    public interface ItemSelected
    {

    }

    public PersonAdapter(Context context,ArrayList<Images> list)
    {
        activity=(ItemSelected)context;
        events=list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv;
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv= itemView.findViewById(R.id.iv);
            tv= itemView.findViewById(R.id.tv);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.particular_element,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(events.get(position));
        Picasso.get().load(events.get(position).getEvent_pic()).placeholder(R.mipmap.ic_event).into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent((Context) activity,EventInformation.class);
                it.putExtra("event_pic",events.get(position).getEvent_pic());
                it.putExtra("event_name",events.get(position).getEvent_name());
                it.putExtra("event_date",events.get(position).getDate());
                it.putExtra("event_time",events.get(position).getTime());
                it.putExtra("event_info",events.get(position).getInfo());
                it.putExtra("event_location",events.get(position).getLocation());
                it.putExtra("event_tcost",events.get(position).getT_cost());
                ((Context) activity).startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}

