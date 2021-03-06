package com.elsol.user.cheerup.Tutorial;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.elsol.user.cheerup.Activity.LoginActivity;
import com.elsol.user.cheerup.R;

public class EndTut extends Fragment {

    public static  EndTut newInstance() {
        EndTut fragment = new  EndTut();
        return fragment;
    }

    public  EndTut(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.end_tutorial, null);
        Button start_button=(Button)root.findViewById(R.id.start_button) ;
        start_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return root;
    }
}
