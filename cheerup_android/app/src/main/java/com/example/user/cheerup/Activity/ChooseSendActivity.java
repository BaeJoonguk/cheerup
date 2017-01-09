package com.example.user.cheerup.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.user.cheerup.R;

public class ChooseSendActivity extends AppCompatActivity implements View.OnClickListener{

    private Button send_button;
    private RadioButton rb;
    private RadioGroup rg_type;
    private int radio;

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
        rg_type=(RadioGroup)findViewById(R.id.radio_group);
        rb=(RadioButton)findViewById(radio);//정수부여해서 아이디 찾아 올
    }



    public void onClick(View v) {

        if (v == send_button) {
            int Type = -1; //초기화
            radio = rg_type.getCheckedRadioButtonId();
            if (radio != -1) {
                rb=(RadioButton)findViewById(radio);
                String Types = rb.getText().toString();
                if (Types.contains("사랑"))
                    Type = 0;
                else if (Types.contains("취업"))
                    Type = 1;
                else if (Types.contains("공부"))
                    Type = 2;

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "고마워요 토닥토닥", Toast.LENGTH_SHORT).show();
                finish();
            }
            else if (radio == -1)//라디오버튼 선택 안됏을 때
                Toast.makeText(getApplicationContext(), "응원 대상을 선택해주세요", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, WriteMessageActivity.class);
        startActivity(intent);
        finish();
    }
}
