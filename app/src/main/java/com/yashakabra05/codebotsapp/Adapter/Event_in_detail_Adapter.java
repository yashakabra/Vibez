package com.yashakabra05.codebotsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.yashakabra05.codebotsapp.Class.event_details;
import com.yashakabra05.codebotsapp.Manager_on_event_Activity;
import com.yashakabra05.codebotsapp.R;

import java.util.ArrayList;

public class Event_in_detail_Adapter extends RecyclerView.Adapter<Event_in_detail_Adapter.ViewHolder> {

    ArrayList<event_details> list = new ArrayList<>();
    Context context;
    FirebaseAuth auth;
    FirebaseDatabase database;

    public Event_in_detail_Adapter(ArrayList<event_details> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_in_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Event_in_detail_Adapter.ViewHolder holder, int position) {
        event_details details = list.get(position);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        holder.event_name3.setText(details.getEvent_name());
        holder.event_location3.setText(details.getLocation());
        holder.event_time3.setText(details.getTime());
        holder.event_date3.setText(details.getDate());
        holder.event_type3.setText(details.getEvent_type());
        holder.event_info3.setText(details.getInfo());

        holder.btn_buy_ticket3.setText("Pay Rs. "+details.getT_cost());
        holder.btn_buy_ticket3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Payment transition successfull", Toast.LENGTH_SHORT).show();
                holder.btn_buy_ticket3.setText("Paid");
                database.getReference().child("booked event").child(auth.getCurrentUser().getUid()).push().setValue(details);
            }
        });

        Picasso.get().load(details.getEvent_pic()).placeholder(R.mipmap.ic_event).into(holder.event_pic3);
        Picasso.get().load(details.getGuide_pic()).placeholder(R.mipmap.ic_event).into(holder.event_map3);

        



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView event_pic3, event_map3;
        FrameLayout google_map3;
        Button btn_buy_ticket3;
        TextView event_name3, event_type3, event_date3, event_time3, event_info3, event_location3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

         event_pic3 = itemView.findViewById(R.id.event_pic3);
         event_map3 = itemView.findViewById(R.id.event_map3);
         google_map3 = itemView.findViewById(R.id.google_map3);
         btn_buy_ticket3 = itemView.findViewById(R.id.btn_buy_ticket3);
         event_name3 = itemView.findViewById(R.id.event_name3);
         event_type3 = itemView.findViewById(R.id.event_type3);
         event_date3 = itemView.findViewById(R.id.event_date3);
         event_time3 = itemView.findViewById(R.id.event_time3);
         event_info3 = itemView.findViewById(R.id.event_info3);
         event_location3 = itemView.findViewById(R.id.event_location3);

        }
    }
}
