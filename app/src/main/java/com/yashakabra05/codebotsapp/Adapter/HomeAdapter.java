package com.yashakabra05.codebotsapp.Adapter;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.yashakabra05.codebotsapp.Class.event_details;
import com.yashakabra05.codebotsapp.Event_in_detailActivity;
import com.yashakabra05.codebotsapp.Manager_on_event_Activity;
import com.yashakabra05.codebotsapp.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    ArrayList<event_details> list = new ArrayList<>();
    Context context;
    FirebaseAuth auth;
    FirebaseDatabase database;

    public HomeAdapter(ArrayList<event_details> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_layout, parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        event_details details = list.get(position);

        holder.event_name2.setText(details.getEvent_name());
        holder.event_location2.setText(details.getLocation());
        holder.event_time2.setText(details.getTime());
        holder.event_date2.setText(details.getDate());
        holder.event_type2.setText(details.getEvent_type());

        holder.btn_buy_ticket2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Event_in_detailActivity.class);
                context.startActivity(intent);
            }
        });

        holder.cv_event_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Event_in_detailActivity.class);
                context.startActivity(intent);
            }
        });

   //     Picasso.get().load(details.getEvent_pic()).placeholder(R.mipmap.ic_event).into(holder.event_pic2);

        holder.btn_event_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details.setFav(true);
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView cv_event_home;
        ImageView event_pic2;
        TextView event_name2, event_type2, event_date2, event_time2, event_location2;
        Button  btn_event_fav, btn_buy_ticket2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_event_home = itemView.findViewById(R.id.cv_event_home2);
            event_pic2 = itemView.findViewById(R.id.event_pic2);
            event_name2  = itemView.findViewById(R.id.event_name2);
             event_type2  = itemView.findViewById(R.id.event_type2);
             event_date2= itemView.findViewById(R.id.event_date2);
             event_time2 = itemView.findViewById(R.id.event_time2);
             event_location2 = itemView.findViewById(R.id.event_location2);
             btn_event_fav = itemView.findViewById(R.id.btn_event_fav);
             btn_buy_ticket2 = itemView.findViewById(R.id.btn_buy_ticket2);

        }
    }
}



