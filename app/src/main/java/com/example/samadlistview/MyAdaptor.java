package com.example.samadlistview;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyViewHolder>{

    Context context;
    String[] name;
    String[] desc;
    int[] noimage;

    public MyAdaptor(Context context, String[] name, String[] desc, int[] noimage) {
        this.context = context;
        this.name = name;
        this.desc = desc;
        this.noimage = noimage;
    }

    @NonNull
    @Override
    public MyAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview;
        myview = LayoutInflater.from(context).inflate(R.layout.items,parent, false);
        return new MyViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdaptor.MyViewHolder holder, int position) {

        holder.nameString.setText(name[position]);
        holder.descString.setText(desc[position]);
        holder.noimages.setImageResource(noimage[position]);

    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameString;
        TextView descString;
        ImageView noimages;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameString = itemView.findViewById(R.id.name);
            descString = itemView.findViewById(R.id.desc);
            noimages = itemView.findViewById(R.id.noimage);
        }
    }
}
