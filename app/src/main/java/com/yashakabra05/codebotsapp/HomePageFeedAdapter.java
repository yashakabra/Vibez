
/*
package com.yashakabra05.codebotsapp;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class HomePageFeedAdapter extends FirebaseRecyclerAdapter<Images,HomePageFeedAdapter.myviewholder> {

    itemselec act;
    ArrayList<Images> image;
    public interface itemselec
    {
        void onItemSelectedEvent(int index);
        void onItemImage(int index);    }

    public HomePageFeedAdapter(Context context, @NonNull FirebaseRecyclerOptions<Images> options) {
        super(options);
        act = (itemselec) context;
    }

    @Override
    protected void onBindViewHolder(@NonNull HomePageFeedAdapter.myviewholder holder, int position, @NonNull Images model) {

        image.add(model);
        holder.tvDate.setText(model.getEvent_date());
        holder.tvName.setText(model.getEvent_name());
        holder.tvPrice.setText(model.getEvent_tprice());
        holder.ivEventPhoto.setImageResource(R.drawable.mercedes);
    }

    @Override
    public HomePageFeedAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.particular_element2,parent,false);
        return new HomePageFeedAdapter.myviewholder(v);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView ivEventPhoto;
        ImageView vector;
        TextView tvName,tvDate,tvPrice;

        public myviewholder( View itemView) {
            super(itemView);

            ivEventPhoto= itemView.findViewById(R.id.ivRv2);
            vector= itemView.findViewById(R.id.vectorRv2);
            tvName= itemView.findViewById(R.id.tvName);
            tvDate= itemView.findViewById(R.id.tvDate);
            tvPrice=itemView.findViewById(R.id.tvPrice);

        }
    }
}

 */
