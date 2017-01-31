package com.example.user.cheerup.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.user.cheerup.R;
import com.example.user.cheerup.adapter.CommentAdapter;

import java.util.ArrayList;

public class CardCommentActivity extends AppCompatActivity {

    private RecyclerView recView;
    private CommentAdapter adapter;
    private ArrayList listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_comment);

        initview();
    }

    private void initview() {
        recView = (RecyclerView) findViewById(R.id.cardcomment_rec_list);
        recView.setLayoutManager(new LinearLayoutManager(this));
        setRecyclerView();

    }

    public void setRecyclerView()
    {
        // adapter=new CommentAdapter(TestData.getListData(),this);//testdata
        recView.setAdapter(adapter);
    }


}
