package com.example.samadlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    Context context;
    ArrayList<Users> userList;
    ImageView imageView;

    public UserAdapter(Context context, ArrayList<Users> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {

        Users user = userList.get(position);

        holder.showname.setText(user.getFullname());
        holder.showemail.setText(user.getEmail());
        holder.showaddress.setText(user.getAddress());
        holder.showpassword.setText(user.getPassword());
        holder.showconform_password.setText(user.getConform_password());

        Picasso.get().load(user.getImageid()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {

        return userList.size();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        TextView showname,showemail,showaddress,showpassword,showconform_password;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            showname = itemView.findViewById(R.id.showname);
            showemail = itemView.findViewById(R.id.showemail);
            showaddress = itemView.findViewById(R.id.showaddress);
            showpassword = itemView.findViewById(R.id.showpassword);
            showconform_password = itemView.findViewById(R.id.showcpassword);


            imageView = itemView.findViewById(R.id.showing);

        }

    }
}
