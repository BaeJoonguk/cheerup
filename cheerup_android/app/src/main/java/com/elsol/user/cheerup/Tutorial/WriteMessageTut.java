package com.elsol.user.cheerup.Tutorial;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elsol.user.cheerup.R;

public class WriteMessageTut extends Fragment {

    public static WriteMessageTut newInstance() {
        WriteMessageTut fragment = new WriteMessageTut();
        return fragment;
    }

    public WriteMessageTut() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.write_message_tutorial, null);
        return root;
    }
}
