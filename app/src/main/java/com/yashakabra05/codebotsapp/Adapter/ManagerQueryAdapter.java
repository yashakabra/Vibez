package com.yashakabra05.codebotsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yashakabra05.codebotsapp.Class.UserQuery;

import java.util.ArrayList;

public class ManagerQueryAdapter extends RecyclerView.Adapter<ManagerQueryAdapter.ViewHolder>{

    ArrayList<UserQuery> list;
    Context context;

    public ManagerQueryAdapter(ArrayList<UserQuery> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.manager_query_layout,parent,false);
        return new ManagerQueryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerQueryAdapter.ViewHolder holder, int position) {

        holder.tvquery.setText(list.get(position).getQuery());
        holder.tvMail.setText(list.get(position).getUseremail());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvquery, tvMail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMail = itemView.findViewById(R.id.tvMail);
            tvquery = itemView.findViewById(R.id.tvQuery);

        }
    }

}