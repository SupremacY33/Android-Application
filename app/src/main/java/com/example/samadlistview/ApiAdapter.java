package com.example.samadlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ApiAdapter extends RecyclerView.Adapter<ApiAdapter.MyViewHolder> {

    Context context;
    ArrayList<Models> arrayList;

    public ApiAdapter(Context context, ArrayList<Models> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.api_items, parent ,  false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Models list = arrayList.get(position);
        holder.id12.setText(list.getId());
        holder.title.setText(list.getTitle());
        holder.decs.setText(list.getBody());
        Picasso.get().load(list.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id12, title , decs;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            id12 = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            decs = itemView.findViewById(R.id.desc123);
        }
    }
}
