package com.example.user.cheerup.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.cheerup.Activity.LoginActivity;
import com.example.user.cheerup.Activity.WriteMessageActivity;
import com.example.user.cheerup.R;


/**
 * Created by user on 2017-01-02.
 */

public class WriteMessageFragment extends Fragment {

    public WriteMessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.writemessage_frag, container, false);
        Button write_button=(Button)root.findViewById(R.id.write_button) ;
        write_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), WriteMessageActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return root;
    }



}
