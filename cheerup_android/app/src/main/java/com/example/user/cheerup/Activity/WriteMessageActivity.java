package com.example.user.cheerup.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.cheerup.R;

public class WriteMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button choose_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);
        initview();
    }

    public void initview()
    {
        choose_button=(Button)findViewById(R.id.choose_button);
        choose_button.setOnClickListener(this);
    }


    public void onClick(View v){
        if(v==choose_button){
            Intent intent =new Intent(getApplicationContext(),ChooseSendActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
