package com.example.user.cheerup.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.cheerup.GetnSet.MyMailboxListItem;
import com.example.user.cheerup.R;
import com.example.user.cheerup.adapter.MyMailboxAdapter;
import com.example.user.cheerup.model.TestData;

import java.util.ArrayList;
import java.util.List;

public class MyMailboxActivity extends AppCompatActivity implements MyMailboxAdapter.ItemClickCallback{

    //TEST DATA EXTRA
    private static final String BUNDLE_EXTRAS="BUNDLE_EXTRAS";
    private static final String EXTRA_SENDER="EXTRA_SENDER";
    private static final String EXTRA_CONTENT="EXTRA_CONTENT";

    private RecyclerView recView;
    private MyMailboxAdapter adapter;
    private ArrayList listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_mailbox);

        listData=(ArrayList)TestData.getListData();

        initview();
    }

    private void initview() {
        recView = (RecyclerView) findViewById(R.id.rec_Mylist);
        recView.setLayoutManager(new LinearLayoutManager(this));
        setRecyclerView();

    }

    public void setRecyclerView()
    {
       // adapter=new MyMailboxAdapter(listData,this);
        adapter=new MyMailboxAdapter(TestData.getListData(),this);//testdata
        recView.setAdapter(adapter);
        adapter.setItemClickCallback(this);
    }



    @Override
    public void onItemClick(int p) {
        MyMailboxListItem item=(MyMailboxListItem)listData.get(p);
        Intent intent=new Intent(this,MyMailboxDetail_Activity.class);

        //test data transition
        Bundle extras=new Bundle();
        extras.putString(EXTRA_SENDER,item.getsender());
        extras.putString(EXTRA_CONTENT,item.getContent());
        intent.putExtra(BUNDLE_EXTRAS,extras);

        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyMailboxActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
