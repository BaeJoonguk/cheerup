package com.example.user.cheerup.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.cheerup.R;

public class ChooseSendActivity extends AppCompatActivity implements View.OnClickListener{

    private Button send_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_send);

        initview();
    }

    public void initview()
    {
        send_button=(Button)findViewById(R.id.send_button);
        send_button.setOnClickListener(this);
    }



    public void onClick(View v){

        if(v==send_button){
            Intent intent =new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "고마워요 토닥토닥", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, WriteMessageActivity.class);
        startActivity(intent);
        finish();
    }
}
