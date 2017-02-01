package com.example.user.cheerup.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.cheerup.Activity.CommentActivity;
import com.example.user.cheerup.GetnSet.CommentFragListItem;
import com.example.user.cheerup.R;

import java.util.ArrayList;

/**
 * Created by user on 2017-01-31.
 */

public class CommentFragAdapter extends RecyclerView.Adapter<CommentFragViewHolder>{

    Context c;
    ArrayList<CommentFragListItem> listData;

    public CommentFragAdapter(Context c, ArrayList<CommentFragListItem> listData) {
        this.listData = listData;
        this.c = c;
    }

    //create view holder object
    //initialize holder
    @Override
    public CommentFragViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_card, null);
        CommentFragViewHolder holder = new CommentFragViewHolder(view);
        return holder;
    }

    //bind data to views
    @Override
    public void onBindViewHolder(CommentFragViewHolder holder, int position) {
        CommentFragListItem item = listData.get(position);
        // holder.sender.setText(item.getsender());
        holder.topic.setText(item.getTopic());
        //holder.pros_button.setImage(item.getImage)

        //listener card 누르면
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "자유롭게 글을 남겨봐요", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent().setClass(v.getContext(),CommentActivity.class);

                v.getContext().startActivity(intent);
            }
        });

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

