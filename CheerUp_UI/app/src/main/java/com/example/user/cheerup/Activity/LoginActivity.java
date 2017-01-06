package com.example.user.cheerup.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.cheerup.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button login_button;
    private Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        initview();
    }

    public void initview() {

        login_button = (Button) findViewById(R.id.login_button); //서적등록버튼
        register_button = (Button) findViewById(R.id.register_button); //도서검색버튼

        login_button.setOnClickListener(this);
        register_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==login_button)
        {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
        else if(v==register_button)
        {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
