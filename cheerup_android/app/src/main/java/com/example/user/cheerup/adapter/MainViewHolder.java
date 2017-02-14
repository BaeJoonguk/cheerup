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

/**
 클래스명 : public class  MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
 주된기능 : 보여지는 텍스트와 이미지뷰를 컨테이너에 묶고 이를 클릭했을 시 반응한다.
 멤버변수 :
 TextView sender;
 TextView question;
 ImageButton pros_button; //찬
 ImageButton cons_button; //반
 int pros_count;
 int cons_count;
 ItemClickListener itemClickListener;
 CardView container;

 메소드 :
 public MainViewHolder(View itemView)
 * 매개변수 : View itemView
 * 기능 : 컨테이너의 뷰들과 listitem xml파일의 객체들을 연결시킨다.
 public void onClick(View v)
 * 매개변수 : View v
 * 기능 : 버튼을 클릭했을때 현위치에 해당하는 뷰로 이동
 */

public class  MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView sender;
    TextView question;
    ImageButton pros_button; //찬
    ImageButton cons_button; //반
    int pros_count;
    int cons_count;
    ItemClickListener itemClickListener;
    CardView container;


    public MainViewHolder(View itemView) {
        super(itemView);


        question = (TextView) itemView.findViewById(R.id.mainQ_text);
        pros_button = (ImageButton) itemView.findViewById(R.id.pros_button);
        cons_button = (ImageButton) itemView.findViewById(R.id.cons_button);
        container=(CardView) itemView.findViewById(R.id.main_cardview);

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



