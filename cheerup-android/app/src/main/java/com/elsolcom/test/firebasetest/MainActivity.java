package com.elsolcom.test.firebasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    //firebase 사용
    Firebase firebase;

    TextView textview;
    TextView textviewtitle;
    EditText edittext;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup code
        Firebase.setAndroidContext(this);

        textview = (TextView)findViewById(R.id.textview);
        textviewtitle = (TextView)findViewById(R.id.textviewtitle);

        edittext = (EditText)findViewById(R.id.edittext);
        button = (Button)findViewById(R.id.button);

        //firebase console에서 데이터베이스 링크
        firebase = new Firebase("https://fir-test-fb80b.firebaseio.com/test");

/*
        firebase.addValueEventListener(new ValueEventListener(){

            //데이터가 바뀌었을 때 dataSnapshot에서 값을 받아와 textview에 set
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue().toString();
                textview.setText(text);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
*/

        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseInstanceId.getInstance().getToken();




        button.setOnClickListener(new View.OnClickListener(){

            //버튼이 클릭하게 되면 edittext에서 text를 불러와 firebase에 set
            @Override
            public void onClick(View view) {
                String text = edittext.getText().toString();
                firebase.setValue(text);

//                String token = FirebaseInstanceId.getInstance().getToken();
//                firebase.setValue(token);

 //               String token = FirebaseInstanceId.getInstance().getToken();
 //               Toast.makeText(getApplicationContext(), token, Toast.LENGTH_LONG).show();

            }
        });
    }
}
