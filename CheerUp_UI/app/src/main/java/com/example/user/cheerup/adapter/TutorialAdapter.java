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
//        else if(position==4)
//            return MessageTutorial.newInstance();
//        else if(position==5)
//            return EndTutorial.newInstance();
        return null;
    }
}
