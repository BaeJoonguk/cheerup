package com.example.user.cheerup.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.cheerup.Activity.WriteQActivity;
import com.example.user.cheerup.R;


/**
 * Created by user on 2017-01-02.
 */

public class WriteQFragment extends Fragment {

    public WriteQFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.writeq_frag, container, false);
        Button write_button=(Button)root.findViewById(R.id.write_button) ;
        write_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), WriteQActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return root;
    }

    //set Tab title for main_fragment
    @Override
    public String toString() {
        return "질문하기";
    }

}
