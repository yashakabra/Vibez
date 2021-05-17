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

public class PersonAdapter2 extends RecyclerView.Adapter<PersonAdapter2.ViewHolder>
{
    ArrayList<Images> event2;
    ItemSelected2 activity;
    public interface ItemSelected2
    {
        void onItemSelectedEvent(int index);
        void onItemImage(int index);
    }
    public PersonAdapter2(Context context, ArrayList<Images> list)
    {
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
        holder.tvName.setText(event2.get(position).getEventName());
        holder.tvDate.setText(event2.get(position).getDate());
        holder.tvPrice.setText(event2.get(position).getPrice());
        if(event2.get(position).getImage().equals("eve1"))
        {
            holder.ivEventPhoto.setImageResource(R.drawable.event1);
        }
        else  if(event2.get(position).getImage().equals("eve2"))
        {
            holder.ivEventPhoto.setImageResource(R.drawable.event2);
        }
        else   if(event2.get(position).getImage().equals("eve3"))
        {
            holder.ivEventPhoto.setImageResource(R.drawable.event3);
        }
        else   if(event2.get(position).getImage().equals("eve4"))
        {
            holder.ivEventPhoto.setImageResource(R.drawable.event4);
        }
        else  if(event2.get(position).getImage().equals("eve5"))
        {
            holder.ivEventPhoto.setImageResource(R.drawable.event5);
        }
        else  if(event2.get(position).getImage().equals("eve6"))
        {
            holder.ivEventPhoto.setImageResource(R.drawable.event6);
        }
        else  if(event2.get(position).getImage().equals("eve7"))
        {
            holder.ivEventPhoto.setImageResource(R.drawable.event7);
        }
        else  if(event2.get(position).getImage().equals("eve8"))
        {
            holder.ivEventPhoto.setImageResource(R.drawable.event8);
        }
        else  if(event2.get(position).getImage().equals("eve9"))
        {
            holder.ivEventPhoto.setImageResource(R.drawable.event9);
        }
        else
        {
            holder.ivEventPhoto.setImageResource(R.drawable.event9);
        }

    }



    @Override
    public int getItemCount() {
        return event2.size();
    }
}
