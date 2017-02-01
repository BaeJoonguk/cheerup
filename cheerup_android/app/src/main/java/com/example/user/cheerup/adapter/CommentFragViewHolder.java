package com.example.user.cheerup.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.cheerup.R;

/**
 * Created by user on 2017-01-31.
 */

public class CommentFragViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView topic;
    CardView container;
    ItemClickListener itemClickListener;

    public CommentFragViewHolder(View itemView) {
        super(itemView);

        topic = (TextView) itemView.findViewById(R.id.topic_text);
        container=(CardView) itemView.findViewById(R.id.topic_cardview);

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
