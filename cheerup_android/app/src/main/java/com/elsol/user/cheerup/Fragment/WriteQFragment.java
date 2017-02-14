package com.elsol.user.cheerup.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.elsol.user.cheerup.Activity.MainActivity;
import com.elsol.user.cheerup.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static com.elsol.user.cheerup.Activity.WASIPAddress.insertdata_link;


/**
 * Created by user on 2017-01-02.
 */

/* 클래스 이름 : public class WriteQFragment extends Fragment
        * 주요 기능 :탭슬라이드 구조의 메인페이지에 관한 기능
        * 멤버 변수
        *String myJSON;
         private static final String TAG_RESULTS="result";
         private static final String TAG_CARDNUMBER = "CardNumber";
         private static final String TAG_CONTENTS ="Contents";
         private static final String TAG_WRITER ="Writer";
         private static final String TAG_PROS="Pros";
         private static final String TAG_CONS="Cons";
        JSONArray cards = null;
         RecyclerView recyclerView;
        * 메소드
        * public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
        * 매개변수 : LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
        * 주요 기능 : 뷰의 생성자
        * public String toString()
        * 매개변수 :
        * 주요 기능 : 탭슬라이드 제목 부여
        * private void insertToDatabase(String contents, String writer)
        *  매개변수 :String contents, String writer
        * 주요 기능 : 데이터값을 디비에 저장
        */

public class WriteQFragment extends Fragment {

    private EditText editTextContents;

    public WriteQFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.writeq_frag, container, false);
        Button write_button=(Button)root.findViewById(R.id.write_button) ;

        editTextContents = (EditText) root.findViewById(R.id.Q_editText);

        write_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                String contents = editTextContents.getText().toString();

                SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences("UserInfo", getActivity().getApplicationContext().MODE_PRIVATE);
                String writer = prefs.getString("EmailAddress", "");

                // 서버에 카드 내용과 작성자 이메일 주소 전송
                insertToDatabase(contents, writer);

                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return root;
    }

    // 서버에 카드 내용과 작성자 이메일 주소 전송
    private void insertToDatabase(String contents, String writer){

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(), "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String Contents = (String)params[0];
                    String Writer = (String)params[1];

                    String data  = URLEncoder.encode("Contents", "UTF-8") + "=" + URLEncoder.encode(Contents, "UTF-8");
                    data += "&" + URLEncoder.encode("Writer", "UTF-8") + "=" + URLEncoder.encode(Writer, "UTF-8");

                    URL url = new URL(insertdata_link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        task.execute(contents,writer);
    }

    //set Tab title for main_fragment
    @Override
    public String toString() {
        return "질문하기";
    }
}