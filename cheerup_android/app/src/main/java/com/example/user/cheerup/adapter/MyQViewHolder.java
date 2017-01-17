package com.example.user.cheerup.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.cheerup.R;

/**
 * Created by user on 2017-01-17.
 */

public class MyQViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView sender;
    TextView question;
    ImageButton pros_button; //찬
    ImageButton cons_button; //반
    ItemClickListener itemClickListener;


    public MyQViewHolder(View itemView) {
        super(itemView);

        question = (TextView) itemView.findViewById(R.id.mainQ_text);
        pros_button = (ImageButton) itemView.findViewById(R.id.pros_button);
        cons_button = (ImageButton) itemView.findViewById(R.id.cons_button);
        //container=itemView.findViewById(R.id.cont_Mymail_root);

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


