package com.elsol.user.cheerup.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by user on 2017-01-16.
 */

/**
 * 클래스 이름 : public class FragPagerAdapter extends FragmentPagerAdapter
 * 주요 기능 : 프래그먼트 페이지에대한 정보를 반환하는 기능
 * 멤버변수
 * 메소드
 *  public FragPagerAdapter(FragmentManager fm)
 * 매개변수 : FragmentManager fm
 * 주요기능 : 생성자
 * public int getCount()
 * 매개변수 :
 * 주요기능 : 아이템의 갯수를 얻는것
 * public Fragment getItem(int position)
 * 매개변수 : int position
 * 주요기능 : 실제프레그먼트들을 하나로 묶는 메소드
 */

public class FragPagerAdapter extends FragmentPagerAdapter{
    ArrayList<Fragment> pages=new ArrayList<>();

    public FragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    //Add pages
    public void addPage(Fragment f)
    {
        pages.add(f);
    }

    //set title for for tab
    public CharSequence getPageTitle(int position){
        return pages.get(position).toString();
    }
}
