package com.example.user.cheerup.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.cheerup.R;

public class CardDetail_Activity extends AppCompatActivity implements View.OnClickListener{

    //private Button button_cardcomment;
    private static final String BUNDLE_EXTRAS="BUNDLE_EXTRAS";
    private static final String EXTRA_QUESTION="EXTRA_QUESTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail_);

        initview();
    }

    private void initview() {

       // button_cardcomment = (Button) findViewById(R.id.button_cardcomment);
        //button_cardcomment.setOnClickListener(this);

        //testData
        Bundle extras=getIntent().getBundleExtra(BUNDLE_EXTRAS);
        ((TextView) findViewById(R.id.myQ_text)).setText(extras.getString(EXTRA_QUESTION));
        //

    }

    @Override
    public void onClick(View v) {
//        if(v==button_cardcomment)
//        {
//            Intent intent = new Intent(getApplicationContext(),CardCommentActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }
}
