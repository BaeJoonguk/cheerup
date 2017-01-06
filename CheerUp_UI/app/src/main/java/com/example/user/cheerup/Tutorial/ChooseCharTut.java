package com.example.user.cheerup.Tutorial;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.cheerup.R;

public class ChooseCharTut extends Fragment {

    public static ChooseCharTut newInstance() {
        ChooseCharTut fragment = new ChooseCharTut();
        return fragment;
    }

    public ChooseCharTut(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.choose_char_tutorial, null);
        return root;
    }
}
