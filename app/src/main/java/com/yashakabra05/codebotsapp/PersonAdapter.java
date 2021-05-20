package com.yashakabra05.codebotsapp;

import android.content.Context;
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
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
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}

