package com.yashakabra05.codebotsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
{
    ArrayList<Images> events;
    ItemSelected activity;
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

        if(events.get(position).getImage().equals("eve1"))
        {
            holder.iv.setImageResource(R.drawable.event1);
        }
        else  if(events.get(position).getImage().equals("eve2"))
        {
            holder.iv.setImageResource(R.drawable.event2);
        }
        else  if(events.get(position).getImage().equals("eve3"))
        {
            holder.iv.setImageResource(R.drawable.event3);
        }
        else  if(events.get(position).getImage().equals("eve4"))
        {
            holder.iv.setImageResource(R.drawable.event4);
        }
        else
        {
            holder.iv.setImageResource(R.drawable.event5);
        }

    }



    @Override
    public int getItemCount() {
        return events.size();
    }
}

