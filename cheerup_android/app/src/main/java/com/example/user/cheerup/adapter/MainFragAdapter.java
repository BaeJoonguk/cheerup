package com.example.user.cheerup.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.cheerup.Activity.CardDetail_Activity;
import com.example.user.cheerup.GetnSet.MainFragListItem;
import com.example.user.cheerup.GetnSet.MyQFragListItem;
import com.example.user.cheerup.R;

import java.util.ArrayList;

/**
 * Created by user on 2017-01-16.
 */

public class MainFragAdapter extends RecyclerView.Adapter<MainViewHolder> {

    Context c;
    ArrayList<MainFragListItem> listData;

    public MainFragAdapter(Context c, ArrayList<MainFragListItem> listData) {
        this.listData = listData;
        this.c = c;
    }

    //create view holder object
    //initialize holder
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_card, null);
        MainViewHolder holder = new MainViewHolder(view);
        return holder;
    }

    //bind data to views
    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        MainFragListItem item = listData.get(position);
       // holder.sender.setText(item.getsender());
        holder.question.setText(item.getQcontent());

        //버튼 애니메이션션
       final Animation animScale= AnimationUtils.loadAnimation(c,R.anim.scale);

        //찬반button
        holder.pros_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                Toast.makeText(c, "찬성합니다", Toast.LENGTH_SHORT).show();
            }

        });
        holder.cons_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                Toast.makeText(c, "반대합니다", Toast.LENGTH_SHORT).show();
            }

        });

        //listener--card 누르면인가?
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //Toast.makeText(c, "찬성"+listData.get(pos).getPros_count()+"개", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}


