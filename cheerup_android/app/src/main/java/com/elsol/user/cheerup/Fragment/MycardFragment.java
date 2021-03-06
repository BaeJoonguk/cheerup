package com.elsol.user.cheerup.Fragment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elsol.user.cheerup.GetnSet.MainFragListItem;
import com.elsol.user.cheerup.R;
import com.elsol.user.cheerup.adapter.MainFragAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.elsol.user.cheerup.Activity.WASIPAddress.getmycard_link;

/**
 * Created by user on 2017-01-26.
 */


/* 클래스 이름 : public class MycardFragment extends Fragment
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
        * protected void showList()
        *  매개변수 :
        * 주요 기능 : 데이터값을 디비로부터 가져와 출력
        */

public class MycardFragment extends Fragment {

    String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String TAG_CARDNUMBER = "CardNumber";
    private static final String TAG_CONTENTS ="Contents";
    private static final String TAG_WRITER ="Writer";
    private static final String TAG_PROS="Pros";
    private static final String TAG_CONS="Cons";

    JSONArray cards = null;

    RecyclerView recyclerView;

    public MycardFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.mycard_frag,null);
        recyclerView=(RecyclerView) v.findViewById(R.id.mycard_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences("UserInfo", getActivity().getApplicationContext().MODE_PRIVATE);
        String writer = prefs.getString("EmailAddress", "");

        // 사용자가 작성한 카드 데이터만 가져오기
        getData(getmycard_link, writer);

        return v;
    }

    // 서버에서 가져온 카드 데이터 recyclerView에 적용
    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            cards = jsonObj.getJSONArray(TAG_RESULTS);

            //collection of cardData
            ArrayList<MainFragListItem> ListData =new ArrayList<>();
            MainFragListItem cardData;

            for(int i=0;i<cards.length();i++){

                JSONObject c = cards.getJSONObject(i);
                int cardnumber = c.getInt(TAG_CARDNUMBER);
                String contents = c.getString(TAG_CONTENTS);
                String writer = c.getString(TAG_WRITER);
                String pros = c.getString(TAG_PROS);
                String cons = c.getString(TAG_CONS);

                cardData=new MainFragListItem(cardnumber, contents, writer, Integer.parseInt(pros), Integer.parseInt(cons));

                //add to collection
                ListData.add(cardData);
            }

            recyclerView.setAdapter(new MainFragAdapter(this.getActivity(),ListData));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    // 사용자가 작성한 카드 데이터만 가져오기
    public void getData(String url, String writer){

        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];
                String EmailAddress = (String)params[1];

                BufferedReader bufferedReader = null;

                try {

                    String data  = URLEncoder.encode("EmailAddress", "UTF-8") + "=" + URLEncoder.encode(EmailAddress, "UTF-8");
                    data += "&" + URLEncoder.encode("EmptyData", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");

                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    con.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

                    wr.write( data );
                    wr.flush();

                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;

                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url, writer);
    }

    //set Tab title for main_fragment
    @Override
    public String toString () {
        return "내 질문함";
    }
}