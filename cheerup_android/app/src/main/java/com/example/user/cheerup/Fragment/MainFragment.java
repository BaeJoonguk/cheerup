package com.example.user.cheerup.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.cheerup.GetnSet.MainFragListItem;
import com.example.user.cheerup.R;
import com.example.user.cheerup.adapter.MainFragAdapter;

import java.util.ArrayList;

/**
 * Created by user on 2017-01-02.
 *
 */

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.main_frag,null);

        //Recyclerview 띄우기
        RecyclerView recyclerView=(RecyclerView) v.findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(new MainFragAdapter(this.getActivity(),getMainView()));

        return v;
    }

    //임시 data
    private ArrayList<MainFragListItem> getMainView() {
        //collection of cardData
        ArrayList<MainFragListItem> ListData =new ArrayList<>();

        //single cardData
        MainFragListItem cardData=new MainFragListItem("회사갈까말까");//(id,내용,글쓴이)
        //add to collection
        ListData.add(cardData);

        cardData=new MainFragListItem("짬뽕vs짜장");
        ListData.add(cardData);

        cardData=new MainFragListItem("헤어질까말까");
        ListData.add(cardData);

        cardData=new MainFragListItem("살까말까");
        ListData.add(cardData);

        return ListData;

    }

    //set Tab title for main_fragment
    @Override
    public String toString() {
        return "홈";
    }

}

