package com.elsol.user.cheerup.Activity;

/**
 * Created by user on 2017-01-03.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.elsol.user.cheerup.R;

/* 클래스 이름 : public class LoadingActivity extends AppCompatActivity
        * 주요 기능 :로딩에 관한 기능
        * 멤버 변수
        *  private  Handler hd;
        * 메소드
        * protected void onCreate(Bundle savedInstanceState)
        * 매개변수 : Bundle savedInstanceState
        * 주요 기능 : 뷰의 생성자
        * private class starthandler implements Runnable
        * 매개변수 :
        * 주요 기능 : 로딩 완료시 메인액티비티로 이동
        */

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
