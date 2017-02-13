package com.example.user.cheerup.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.cheerup.GetnSet.CommentFragListItem;
import com.example.user.cheerup.GetnSet.MainFragListItem;
import com.example.user.cheerup.R;
import com.example.user.cheerup.adapter.CommentFragAdapter;

import java.util.ArrayList;

/**
 * Created by user on 2017-01-26.
 */

public class CommentFragment extends Fragment {

    RecyclerView recyclerView;

    public CommentFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.comment_frag, container, false);

        recyclerView=(RecyclerView) root.findViewById(R.id.topic_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(new CommentFragAdapter(this.getActivity(),getCommentView()));

        return root;
    }

    //토론주제
    private ArrayList<CommentFragListItem> getCommentView() {
        //collection of cardData
        ArrayList<CommentFragListItem> ListData =new ArrayList<>();

        //single cardData
        CommentFragListItem cardData=new CommentFragListItem("#친구");
        //add to collection
        ListData.add(cardData);

        cardData=new CommentFragListItem("#공부");
        ListData.add(cardData);

        cardData=new CommentFragListItem("#부모님과 갈등");
        ListData.add(cardData);

        cardData=new CommentFragListItem("#사랑");
        ListData.add(cardData);

        return ListData;

    }

    //set Tab title for main_fragment
    @Override
    public String toString () {
        return "이야기";
    }

}


