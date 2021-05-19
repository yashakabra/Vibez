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

public class PersonAdapter2 extends RecyclerView.Adapter<PersonAdapter2.ViewHolder>
{
    Context context;
    ArrayList<Images> event2;
    ItemSelected2 activity;
    public interface ItemSelected2
    {
        void onItemSelectedEvent(int index);
        void onItemImage(int index);
    }
    public PersonAdapter2(Context context, ArrayList<Images> list)
    {   super();
        activity=(ItemSelected2)context;
        event2=list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder

    {
        ImageView ivEventPhoto;
        ImageView vector;
        TextView tvName,tvDate,tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivEventPhoto= itemView.findViewById(R.id.ivRv2);
            vector= itemView.findViewById(R.id.vectorRv2);
            tvName= itemView.findViewById(R.id.tvName);
            tvDate= itemView.findViewById(R.id.tvDate);
            tvPrice=itemView.findViewById(R.id.tvPrice);

vector.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        vector.setImageResource(R.drawable.ic_baseline_favorite_24);
        activity.onItemSelectedEvent(event2.indexOf(v.getTag()));

    }

});
ivEventPhoto.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       activity.onItemImage(event2.indexOf(v.getTag()));

    }
});
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemSelectedEvent(event2.indexOf(v.getTag()));
                }
            });*/

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.particular_element2,parent,false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // holder.itemView.setTag(event2.get(position));
        holder.vector.setTag(event2.get(position));
        holder.ivEventPhoto.setTag(event2.get(position));
        holder.tvName.setText(event2.get(position).getEvent_name());
        holder.tvDate.setText(event2.get(position).getDate());
        holder.tvPrice.setText(event2.get(position).getT_cost());
        //Glide.with(getA).load("https://images.unsplash.com/photo-1552519507-da3b142c6e3d?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2Fyc3xlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80").placeholder(R.drawable.event2).into(holder.ivEventPhoto);
        Picasso.get().load(event2.get(position).getEvent_pic()).placeholder(R.mipmap.ic_event).into(holder.ivEventPhoto);
        //event2.get(position).getEvent_pic()



    }




    @Override
    public int getItemCount() {
        return event2.size();
    }
}
