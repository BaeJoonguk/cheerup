package com.example.user.cheerup.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.user.cheerup.R;

public class MyMailboxDetail_Activity extends AppCompatActivity {

    //TEST DATA EXTRA
    private static final String BUNDLE_EXTRAS="BUNDLE_EXTRAS";
    private static final String EXTRA_SENDER="EXTRA_SENDER";
    private static final String EXTRA_CONTENT="EXTRA_CONTENT";

    private TextView senderV;
    private TextView contentV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_mailbox_detail_);

        initview();
    }

    private void initview() {

        //testData
        Bundle extras=getIntent().getBundleExtra(BUNDLE_EXTRAS);
        ((TextView) findViewById(R.id.lbl_Mitem_sender)).setText(extras.getString(EXTRA_SENDER));
        ((TextView) findViewById(R.id.lbl_Mitem_content)).setText(extras.getString(EXTRA_CONTENT));
        //

        /*senderV = (TextView) findViewById(R.id.lbl_Mitem_sender);
        contentV=(TextView) findViewById(R.id.lbl_Mitem_content);*/
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
