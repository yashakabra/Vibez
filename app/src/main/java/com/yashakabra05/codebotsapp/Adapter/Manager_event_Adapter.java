package com.yashakabra05.codebotsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.yashakabra05.codebotsapp.Class.event_details;
import com.yashakabra05.codebotsapp.Manager_on_event_Activity;
import com.yashakabra05.codebotsapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Manager_event_Adapter extends RecyclerView.Adapter<Manager_event_Adapter.ViewHolder> {

    ArrayList<event_details> list = new ArrayList<>();
    Context context;
    FirebaseAuth auth;
    FirebaseDatabase database;

    public Manager_event_Adapter(ArrayList<event_details> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_created_event_layout, parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Manager_event_Adapter.ViewHolder holder, int position) {
       event_details details = list.get(position);

       holder.cv_event_name.setText(details.getEvent_name());
        Picasso.get().load(details.getEvent_pic()).placeholder(R.mipmap.ic_event).into(holder.cv_event_pic);


        holder.event_rv_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Manager_on_event_Activity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView event_rv_manager;
        ImageView cv_event_pic;
        TextView cv_event_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            event_rv_manager = itemView.findViewById(R.id.rv_manager_event);
            cv_event_pic = itemView.findViewById(R.id.cv_event_pic);
            cv_event_name = itemView.findViewById(R.id.cv_event_name);

        }
    }

}
