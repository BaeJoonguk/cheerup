package com.example.user.cheerup.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.cheerup.GetnSet.CommentListItem;
import com.example.user.cheerup.GetnSet.MyMailboxListItem;
import com.example.user.cheerup.R;
import com.example.user.cheerup.adapter.CommentAdapter;
import com.example.user.cheerup.model.TestData;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {

    private RecyclerView recView;
    private CommentAdapter adapter;
    private ArrayList listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //listData=(ArrayList)TestData.getListData();

        initview();
    }

    private void initview() {
        recView = (RecyclerView) findViewById(R.id.comment_rec_list);
        recView.setLayoutManager(new LinearLayoutManager(this));
        setRecyclerView();

    }

    public void setRecyclerView()
    {
       // adapter=new CommentAdapter(TestData.getListData(),this);//testdata
        recView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CommentActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
