package com.example.user.cheerup.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.cheerup.R;

/**
 * Created by user on 2017-01-26.
 */

public class CommentFragment extends Fragment {

    public CommentFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.comment_frag, container, false);

        return root;
    }

    //set Tab title for main_fragment
    @Override
    public String toString () {
        return "토론";
    }

}


