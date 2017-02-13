package com.example.user.cheerup.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.user.cheerup.Fragment.MycardFragment;
import com.example.user.cheerup.Fragment.WriteQFragment;
import com.example.user.cheerup.R;
import com.example.user.cheerup.Fragment.MainFragment;
import com.example.user.cheerup.adapter.FragPagerAdapter;


public class MainActivity extends AppCompatActivity {

    public int userNumber;
    public String emailAddress;
    private static Boolean isGetUserInfo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.mtoolbar);
        setSupportActionBar(toolbar);

        //fragment 탑재
        ViewPager vp = (ViewPager) findViewById(R.id.mViewPager);
        this.addPages(vp);

        //tablayout 탑재
        TabLayout tabLayout=(TabLayout)findViewById(R.id.mtab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(vp);
        tabLayout.setOnTabSelectedListener(listener(vp));

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("UserInfo", getApplicationContext().MODE_PRIVATE);
        String text = prefs.getString("EmailAddress", "");

//        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();

    }

    //add all fragment pages
    private void addPages(ViewPager pager) {
        FragPagerAdapter adapter = new FragPagerAdapter(getSupportFragmentManager());
        adapter.addPage(new MainFragment());
        adapter.addPage(new WriteQFragment());
        adapter.addPage(new MycardFragment());

        //set adpapter to pager
        pager.setAdapter(adapter);
    }

    private TabLayout.OnTabSelectedListener listener(final ViewPager pager)
    {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }

}
