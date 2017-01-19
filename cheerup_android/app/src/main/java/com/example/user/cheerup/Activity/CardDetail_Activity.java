package com.example.user.cheerup.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.cheerup.R;

public class CardDetail_Activity extends AppCompatActivity {

    private static final String BUNDLE_EXTRAS="BUNDLE_EXTRAS";
    private static final String EXTRA_QUESTION="EXTRA_QUESTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail_);

        initview();
    }

    private void initview() {
        //testData
        Bundle extras=getIntent().getBundleExtra(BUNDLE_EXTRAS);
        ((TextView) findViewById(R.id.myQ_text)).setText(extras.getString(EXTRA_QUESTION));
        //
    }

}
