package com.example.user.cheerup.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.cheerup.GetnSet.MyQFragListItem;
import com.example.user.cheerup.R;
import com.example.user.cheerup.adapter.MyQFragAdapter;


import java.util.ArrayList;

/**
 * Created by user on 2017-01-02.
 */

public class MyQFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.myq_frag,null);

            //Recyclerview
            RecyclerView recyclerView=(RecyclerView) v.findViewById(R.id.myq_recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            recyclerView.setAdapter(new MyQFragAdapter(this.getActivity(),getMyView()));

            return v;
        }


    //임시 data
    private ArrayList<MyQFragListItem> getMyView() {
        //collection of cardData
        ArrayList<MyQFragListItem> ListData =new ArrayList<>();

        //single cardData
        MyQFragListItem cardData=new MyQFragListItem(0,"회사갈까말까","디디");//(id,내용,글쓴이)

        //add to collection
        ListData.add(cardData);

        cardData=new MyQFragListItem(1,"짬뽕vs짜장","디디");
        ListData.add(cardData);

        cardData=new MyQFragListItem(2,"헤어질까말까","디디");
        ListData.add(cardData);

        cardData=new MyQFragListItem(3,"살까말까","디디");
        ListData.add(cardData);

        return ListData;

    }

    //set Tab title for main_fragment
    @Override
    public String toString() {
        return "내질문함";
    }

}
