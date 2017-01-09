package com.example.user.cheerup.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.cheerup.R;
import com.firebase.client.Firebase;

public class WriteMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button choose_button;
    private Button send_message;
    private EditText edit_message;

    //firebase 사용
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);

        //Setup code
        Firebase.setAndroidContext(this);

        initview();

        //firebase console에서 데이터베이스 링크
        firebase = new Firebase("https://cheerup-3e52b.firebaseio.com//test");

    }

    public void initview() {
        choose_button = (Button) findViewById(R.id.choose_button);
        send_message = (Button) findViewById(R.id.send_message);

        choose_button.setOnClickListener(this);
        send_message.setOnClickListener(this);

        edit_message = (EditText) findViewById(R.id.edit_message);
    }


    public void onClick(View v) {
        if (v == choose_button) {
            if (edit_message.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "글을 입력해주세요", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(getApplicationContext(), ChooseSendActivity.class);
                startActivity(intent);
                finish();
            }
        }
        else if(v == send_message)
        {
            String text = edit_message.getText().toString();
            firebase.setValue(text);
        }
    }
}
