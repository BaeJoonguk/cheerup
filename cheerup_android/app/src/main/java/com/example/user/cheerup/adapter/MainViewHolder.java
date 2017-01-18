package com.example.user.cheerup.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.cheerup.R;

/**
 * Created by user on 2017-01-17.
 */

public class  MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView sender;
    TextView question;
    ImageButton pros_button; //찬
    ImageButton cons_button; //반
    ItemClickListener itemClickListener;
    View container;


    public MainViewHolder(View itemView) {
        super(itemView);


        question = (TextView) itemView.findViewById(R.id.mainQ_text);
        pros_button = (ImageButton) itemView.findViewById(R.id.pros_button);
        cons_button = (ImageButton) itemView.findViewById(R.id.cons_button);
        container=(View) itemView.findViewById(R.id.maincard_container);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());//현재위치에 해당하는 뷰로
    }
}



