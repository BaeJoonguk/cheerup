package com.example.user.cheerup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.cheerup.GetnSet.MyQFragListItem;
import com.example.user.cheerup.R;
import java.util.ArrayList;

/**
 * Created by user on 2017-01-16.
 */


public class MyQFragAdapter extends RecyclerView.Adapter<MyQViewHolder> {

    Context c;
    ArrayList<MyQFragListItem> listData;

    public MyQFragAdapter(Context c, ArrayList<MyQFragListItem> listData) {
        this.listData = listData;
        this.c = c;
    }

    //create view holder object
    //initialize holder
    @Override
    public MyQViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_card, null);
        MyQViewHolder holder = new MyQViewHolder(view);
        return holder;
    }

    //bind data to views
    @Override
    public void onBindViewHolder(MyQViewHolder holder, int position) {
        MyQFragListItem item = listData.get(position);
        // holder.sender.setText(item.getsender());
        holder.question.setText(item.getQcontent());
        //holder.pros_button.setImage(item.getImage)

        //listener
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //Toast.makeText(c, listData.get(pos).getsender(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}


