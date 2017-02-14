package com.elsol.user.cheerup.Activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.elsol.user.cheerup.R;
import com.elsol.user.cheerup.adapter.TutorialAdapter;

import me.relex.circleindicator.CircleIndicator;


/**
 * 클래스 이름 : public class TutorialActivity extends AppCompatActivity
 * 주요 기능 : 사용자가 이 어플리케이션에 대한 기본적인 정보를 수집할 수 있게 뷰페이저를 통해 사용법을 숙지할 수 있도록
 *           여러개의 프레그먼트를 하나로 묶어, 하나의 액티비티 구현.
 *
 * 멤버변수
 * TutorialAdapter adapter // 프레그먼트를 묶은, 즉 여러개의 튜토리얼 과정을 하나의 객체로 형성
 * ViewPager pager : 어텝터를 페이저에 장착시켜 뷰페이저를 실체화하기 위한 객체 형성
 *
 * 메소드
 * protected void onCreate(Bundle savedInstanceState)
 * 매개변수 :
 * 주요기능 : 뷰의 생성자
 */

public class TutorialActivity extends AppCompatActivity {

    TutorialAdapter adapter;
    ViewPager pager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        adapter = new TutorialAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);

    }
}
