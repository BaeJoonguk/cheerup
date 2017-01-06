package com.example.user.cheerup.Tutorial;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.cheerup.R;

public class GetMessageTut extends Fragment {

    public static GetMessageTut newInstance() {
        GetMessageTut fragment = new GetMessageTut();
        return fragment;
    }

    public GetMessageTut() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.get_message_tutorial, null);
        return root;
    }
}
