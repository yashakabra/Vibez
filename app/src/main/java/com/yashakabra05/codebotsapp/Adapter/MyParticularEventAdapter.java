package com.yashakabra05.codebotsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyParticularEventAdapter extends RecyclerView.Adapter<MyParticularEventAdapter.ViewHolder> {

    ArrayList<LocationInfo> my_particular_event_list;

    Itemselec3 acti;

    public interface Itemselec3
    {

    }

    public MyParticularEventAdapter(Context context, ArrayList<LocationInfo> list) {
        super();
        my_particular_event_list = list;
        acti = (Itemselec3) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_victim_emer, tv_victim_name;
        Button btn_reach_victim;

        public ViewHolder( View itemView) {
            super(itemView);
            tv_victim_emer = itemView.findViewById(R.id.tv_victim_emer);
            tv_victim_name = itemView.findViewById(R.id.tv_victim_name);
            btn_reach_victim = itemView.findViewById(R.id.btn_reach_victim);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_particular_event_list,parent,false);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder( MyParticularEventAdapter.ViewHolder holder, int position) {
        holder.tv_victim_name.setText(my_particular_event_list.get(position).getName());
        holder.tv_victim_emer.setText(my_particular_event_list.get(position).getTypeofdifficulty());
        holder.btn_reach_victim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent((Context)acti,EmergencyTab.class);

                it.putExtra("victim name",my_particular_event_list.get(position).getName());
                it.putExtra("emergency called",my_particular_event_list.get(position).getTypeofdifficulty());
                it.putExtra("victim tel",my_particular_event_list.get(position).getPhone());
                it.putExtra("lat loc",my_particular_event_list.get(position).getLatloc());
                it.putExtra("long loc",my_particular_event_list.get(position).getLongloc());

                ((Context) acti).startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return my_particular_event_list.size();
    }
}
