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

public class tvShowAdapter extends RecyclerView.Adapter<tvShowAdapter.MyViewHolder> {

    ArrayList<tvShowModel> arrayList;
    Context context;

    public tvShowAdapter(ArrayList<tvShowModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public tvShowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(context).inflate(R.layout.tvshowitems,parent,false);
        return new MyViewHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull tvShowAdapter.MyViewHolder holder, int position) {

        tvShowModel listing = arrayList.get(position);
        holder.movienames.setText(listing.getName());
        Picasso.get().load(listing.getImage_thumbnail_path()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView movienames;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.showingmovies);
            movienames = itemView.findViewById(R.id.movienames);

        }
    }
}
