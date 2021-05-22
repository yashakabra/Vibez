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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterAllEvents extends RecyclerView.Adapter<AdapterAllEvents.ViewHolder>
{
    ArrayList<Event> allEvents;

    Context context;
    ItemSelected2 activity;

    FirebaseDatabase database;
    FirebaseAuth auth;

    //creating interface for homepage and adapter class

   public interface ItemSelected2
    {

    }
    public AdapterAllEvents(Context context, ArrayList<Event> list)
    {
        super();
        activity=(ItemSelected2)context;
        allEvents=list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder

    {
        ImageView ivEventPhoto;
        ImageView vectorFavourite;
        TextView tvName,tvDate,tvPrice;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            ivEventPhoto= itemView.findViewById(R.id.ivRv2);
            vectorFavourite= itemView.findViewById(R.id.vectorRv2);
            tvName= itemView.findViewById(R.id.tvName);
            tvDate= itemView.findViewById(R.id.tvDate);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            vectorFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vectorFavourite.setImageResource(R.drawable.ic_baseline_favorite_24);
                    Favourite.items.add(HomePage.list.get(HomePage.list.indexOf(vectorFavourite.getTag())));

                    Favourite.editor.putString(HomePage.list.get(HomePage.list.indexOf(vectorFavourite.getTag())).getEvent_name(),HomePage.list.get(HomePage.list.indexOf(vectorFavourite.getTag())).getEvent_name());
                    Favourite.editor.commit();
                }
            });

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

        holder.vectorFavourite.setTag(allEvents.get(position));
        holder.tvName.setText(allEvents.get(position).getEvent_name());
        holder.tvDate.setText(allEvents.get(position).getDate());
        holder.tvPrice.setText(allEvents.get(position).getT_cost());
        Picasso.get().load(allEvents.get(position).getEvent_pic()).placeholder(R.mipmap.ic_event).into(holder.ivEventPhoto);

        holder.ivEventPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               Intent it = new Intent((Context) activity,EventInformation.class);
                it.putExtra("event_pic",allEvents.get(position).getEvent_pic());
                it.putExtra("event_name",allEvents.get(position).getEvent_name());
                it.putExtra("event_date",allEvents.get(position).getDate());
                it.putExtra("event_time",allEvents.get(position).getTime());
                it.putExtra("event_info",allEvents.get(position).getInfo());
                it.putExtra("event_location",allEvents.get(position).getLocation());
                it.putExtra("event_tcost",allEvents.get(position).getT_cost());
                ((Context) activity).startActivity(it);
            }
        });
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        if(holder.vectorFavourite.getDrawable().equals(R.drawable.favourite)) {

            holder.vectorFavourite.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    holder.vectorFavourite.setImageResource(R.drawable.ic_baseline_favorite_24);
                    database.getReference().child("my favs").child(auth.getCurrentUser().getUid()).push().setValue(allEvents.get(position));
                }
            });
        }

        if(holder.vectorFavourite.getDrawable().equals(R.drawable.ic_baseline_favorite_24)){

            holder.vectorFavourite.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    holder.vectorFavourite.setImageResource(R.drawable.favourite);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return allEvents.size();
    }
}
