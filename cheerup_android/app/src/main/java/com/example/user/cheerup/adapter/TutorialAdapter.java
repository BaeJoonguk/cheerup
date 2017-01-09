package com.example.user.cheerup.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.user.cheerup.Tutorial.ChooseCharTut;
import com.example.user.cheerup.Tutorial.EndTut;
import com.example.user.cheerup.Tutorial.GetMessageTut;
import com.example.user.cheerup.Tutorial.WriteMessageTut;

/**
 * Created by user on 2017-01-04.
 */

/**
 * 클래스 이름 : public class TutorialAdapter extends FragmentPagerAdapter
 * 주요 기능 : 튜토리얼에 해당되는 각 프레그먼트들을 하나로 모아 덩어리 화하는 클래스
 *
 * 멤버변수  private final int NUM_ITEMS = 4; //뷰페이저공간을 할당
 *
 * 메소드
 * public TutorialAdapter(FragmentManager fm)
 * 매개변수 : FragmentManager fm
 * 주요기능 : 생성자
 * public int getCount()
 * 매개변수 :
 * 주요기능 : 아이템의 갯수를 얻는것
 * public Fragment getItem(int position)
 * 매개변수 : int position
 * 주요기능 : 실제프레그먼트들을 하나로 묶는 메소드
 */

public class TutorialAdapter extends FragmentPagerAdapter {
    private final int NUM_ITEMS = 4;//페이저공간4개 만들어주고

    public TutorialAdapter(FragmentManager fm) {
        super(fm);
    } //생성자

    public int getCount() {
        return NUM_ITEMS;
    } // getter

    public Fragment getItem(int position) { // 연속된수를가지고 튜토리얼 진행

        if (position == 0)
            return WriteMessageTut.newInstance();
        else if (position == 1)
            return ChooseCharTut.newInstance();
        else if (position == 2)
            return GetMessageTut.newInstance();
        else if (position == 3)
            return EndTut.newInstance();

        return null;
    }
}
