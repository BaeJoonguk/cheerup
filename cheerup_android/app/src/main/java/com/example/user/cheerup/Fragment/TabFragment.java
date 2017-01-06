package com.example.user.cheerup.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.cheerup.R;

/**
 * Created by user on 2017-01-02.
 *
 * 탭레이아웃과 뷰페이저요소를 설정
 * 뷰페이저에 내용표시를 위해 어댑터필요
 * 1) getCount() : It returns the total number of tabs to be bind with the view pager.
 2) getItem(int position)  : It returns the tab-specific fragment wrt to its position.
 3) getPageTitle(int position) : It returns name of the title according to the position.

 setupWithViewPager(viewPager)함수로 탭레이아웃을 뷰페이저에 붙인다
 *
 */

public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;

    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x =  inflater.inflate(R.layout.tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);


        //Set an Apater for the View Pager
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        //Return fragment with respect to Position .
        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new TotalCountFragment();
                case 1 : return new WorryCountFragment();
                case 2 : return new WriteMessageFragment();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;
        }

        //This method returns the title of the tab according to the position.
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "응원지수";
                case 1 :
                    return "고민지수";
                case 2 :
                    return "응원하기";
            }
            return null;
        }
    }
}
