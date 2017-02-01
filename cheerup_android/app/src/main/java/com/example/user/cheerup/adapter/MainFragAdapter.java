package com.example.user.cheerup.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.user.cheerup.Activity.CardDetail_Activity;
import com.example.user.cheerup.GetnSet.MainFragListItem;
import com.example.user.cheerup.R;

import java.util.ArrayList;

/**
 * Created by user on 2017-01-16.
 */

public class MainFragAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private static final String BUNDLE_EXTRAS="BUNDLE_EXTRAS";
    private static final String EXTRA_QUESTION="EXTRA_QUESTION";
    private static final String EXTRA_PROS="EXTRA_PROS";
    private static final String EXTRA_CONSS="EXTRA_CONS";

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
    public void onBindViewHolder(final MainViewHolder holder, final int position) {
        final MainFragListItem item = listData.get(position);
       // holder.sender.setText(item.getsender());
        holder.question.setText(item.getQcontent());



        //버튼 애니메이션션
       final Animation animScale= AnimationUtils.loadAnimation(c,R.anim.scale);

        //찬성button
        holder.pros_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                Toast.makeText(c, "찬성합니다", Toast.LENGTH_SHORT).show();

            }

        });
        //반대button
        holder.cons_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                Toast.makeText(c, "반대합니다", Toast.LENGTH_SHORT).show();
            }

        });

        //listener card 누르면
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "카드뷰", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent().setClass(v.getContext(),CardDetail_Activity.class);

                //test data transition
                Bundle extras=new Bundle();
                extras.putString(EXTRA_QUESTION,item.getQcontent());
                intent.putExtra(BUNDLE_EXTRAS,extras);

                v.getContext().startActivity(intent);
            }
        });

        //listener
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


