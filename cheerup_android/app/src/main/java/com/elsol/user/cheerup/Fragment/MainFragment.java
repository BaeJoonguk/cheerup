package com.elsol.user.cheerup.Fragment;

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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.elsol.user.cheerup.Activity.WASIPAddress.getdata_link;

/**
 * Created by user on 2017-01-02.
 *
 */

public class MainFragment extends Fragment {

    String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String TAG_CARDNUMBER = "CardNumber";
    private static final String TAG_CONTENTS ="Contents";
    private static final String TAG_WRITER ="Writer";
    private static final String TAG_PROS="Pros";
    private static final String TAG_CONS="Cons";

    JSONArray cards = null;

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.main_frag,null);

        recyclerView=(RecyclerView) v.findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        getData(getdata_link);

        return v;
    }

    //set Tab title for main_fragment
    @Override
    public String toString() {
        return "홈";
    }


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


    public void getData(String url){

        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
        g.execute(url);
    }
}