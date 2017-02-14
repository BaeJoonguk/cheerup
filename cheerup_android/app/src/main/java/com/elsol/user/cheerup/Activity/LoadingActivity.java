package com.elsol.user.cheerup.Activity;

/**
 * Created by user on 2017-01-03.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.elsol.user.cheerup.R;


public class LoadingActivity extends AppCompatActivity {

    private  Handler hd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        hd = new Handler();
        hd.postDelayed(new starthandler() ,2000); // 2초 후에 hd Handler 실행
    }

    private class starthandler implements Runnable{
        public void run() {
            startActivity(new Intent(LoadingActivity.this, TutorialActivity.class)); // 로딩이 끝난후 이동할 Activity
            finish(); // 로딩페이지 Activity Stack에서 제거
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

}
