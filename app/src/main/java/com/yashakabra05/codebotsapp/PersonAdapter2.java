package com.yashakabra05.codebotsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class PersonAdapter2 extends RecyclerView.Adapter<PersonAdapter2.ViewHolder>
{
    ArrayList<Images> event2;

    Context context;
    ItemSelected2 activity;

    FirebaseDatabase database;
    FirebaseAuth auth;

    //creating interface for homepage and adapter class

   public interface ItemSelected2
    {

    }
    public PersonAdapter2(Context context, ArrayList<Images> list)
    {
        super();
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

        holder.vector.setTag(event2.get(position));
        holder.tvName.setText(event2.get(position).getEvent_name());
        holder.tvDate.setText(event2.get(position).getDate());
        holder.tvPrice.setText(event2.get(position).getT_cost());
        Picasso.get().load(event2.get(position).getEvent_pic()).placeholder(R.mipmap.ic_event).into(holder.ivEventPhoto);

        holder.ivEventPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               Intent it = new Intent((Context) activity,EventInformation.class);
                it.putExtra("event_pic",event2.get(position).getEvent_pic());
                it.putExtra("event_name",event2.get(position).getEvent_name());
                it.putExtra("event_date",event2.get(position).getDate());
                it.putExtra("event_time",event2.get(position).getTime());
                it.putExtra("event_info",event2.get(position).getInfo());
                it.putExtra("event_location",event2.get(position).getLocation());
                it.putExtra("event_tcost",event2.get(position).getT_cost());
                ((Context) activity).startActivity(it);
            }
        });
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        if(holder.vector.getDrawable().equals(R.drawable.favourite)) {

            holder.vector.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    holder.vector.setImageResource(R.drawable.ic_baseline_favorite_24);
                    database.getReference().child("my favs").child(auth.getCurrentUser().getUid()).push().setValue(event2.get(position));
                }
            });
        }

        if(holder.vector.getDrawable().equals(R.drawable.ic_baseline_favorite_24)){

            holder.vector.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    holder.vector.setImageResource(R.drawable.favourite);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return event2.size();
    }
}
