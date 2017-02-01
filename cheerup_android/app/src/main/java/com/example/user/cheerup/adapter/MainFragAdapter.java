package com.example.user.cheerup.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.user.cheerup.Activity.CardDetail_Activity;
import com.example.user.cheerup.GetnSet.MainFragListItem;
import com.example.user.cheerup.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.user.cheerup.Activity.WASIPAddress.addcons_link;
import static com.example.user.cheerup.Activity.WASIPAddress.addpros_link;
import static com.example.user.cheerup.Activity.WASIPAddress.login_link;

/**
 * Created by user on 2017-01-16.
 */

public class MainFragAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private static final String BUNDLE_EXTRAS="BUNDLE_EXTRAS";
    private static final String EXTRA_QUESTION="EXTRA_QUESTION";
    private static final String EXTRA_PROS="EXTRA_PROS";
    private static final String EXTRA_CONSS="EXTRA_CONS";

    Context c;
    ArrayList<MainFragListItem> listData;

    public MainFragAdapter(Context c, ArrayList<MainFragListItem> listData) {
        this.listData = listData;
        this.c = c;
    }

    //create view holder object
    //initialize holder
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_card, null);
        MainViewHolder holder = new MainViewHolder(view);
        return holder;
    }

    //bind data to views
    @Override
    public void onBindViewHolder(final MainViewHolder holder, final int position) {
        final MainFragListItem item = listData.get(position);
       // holder.sender.setText(item.getsender());
        holder.question.setText(item.getQcontent());

        //버튼 애니메이션션
       final Animation animScale= AnimationUtils.loadAnimation(c,R.anim.scale);

        //찬성button
        holder.pros_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                Toast.makeText(c, "찬성합니다", Toast.LENGTH_SHORT).show();

                String cardnumber = String.valueOf(item.getCardNumber());
                addPros(cardnumber, "");

            }

        });
        //반대button
        holder.cons_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                Toast.makeText(c, "반대합니다", Toast.LENGTH_SHORT).show();

                String cardnumber = String.valueOf(item.getCardNumber());
                addCons(cardnumber, "");

            }

        });

        //listener card 누르면
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "카드뷰", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent().setClass(v.getContext(),CardDetail_Activity.class);

                //test data transition
                Bundle extras=new Bundle();
                extras.putString(EXTRA_QUESTION,item.getQcontent());
                intent.putExtra(BUNDLE_EXTRAS,extras);

                v.getContext().startActivity(intent);
            }
        });

        //listener
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //Toast.makeText(c, "찬성"+listData.get(pos).getPros_count()+"개", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    private void addPros(String cardnumber, String emptydata){

            class InsertData extends AsyncTask<String, Void, String> {
            //ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(LoginActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
/*

                myJSON=result;

                try {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    cards = jsonObj.getJSONArray(TAG_RESULTS);

                    JSONObject c = cards.getJSONObject(0);
                    String password = c.getString(TAG_PASSWORD);
                    int userNumber = c.getInt(TAG_USERNUMBER);

                    if(userInputPassword.equals(password))
                        isCheckEmailAddressAndPassword = true;
                    else
                        isCheckEmailAddressAndPassword = false;

                    if(isCheckEmailAddressAndPassword)
                    {
                        SharedPreferences prefs = getApplicationContext().getSharedPreferences("UserInfo", getApplicationContext().MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("EmailAddress", userInputEmailAddress);
                        editor.commit();


                        UserInfo userInfo = new UserInfo(userNumber,userInputEmailAddress);
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("UserInfo", userInfo);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "이메일주소와 비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
*/

                //loading.dismiss();
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String CardNumber = (String)params[0];
                    String EmptyData = (String)params[1];

                    String data  = URLEncoder.encode("CardNumber", "UTF-8") + "=" + URLEncoder.encode(CardNumber, "UTF-8");
                    data += "&" + URLEncoder.encode("EmptyData", "UTF-8") + "=" + URLEncoder.encode(EmptyData, "UTF-8");

                    URL url = new URL(addpros_link);
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
        task.execute(cardnumber,emptydata);
    }



    private void addCons(String cardnumber, String emptydata){

        class InsertData extends AsyncTask<String, Void, String> {
            //ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(LoginActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
/*

                myJSON=result;

                try {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    cards = jsonObj.getJSONArray(TAG_RESULTS);

                    JSONObject c = cards.getJSONObject(0);
                    String password = c.getString(TAG_PASSWORD);
                    int userNumber = c.getInt(TAG_USERNUMBER);

                    if(userInputPassword.equals(password))
                        isCheckEmailAddressAndPassword = true;
                    else
                        isCheckEmailAddressAndPassword = false;

                    if(isCheckEmailAddressAndPassword)
                    {
                        SharedPreferences prefs = getApplicationContext().getSharedPreferences("UserInfo", getApplicationContext().MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("EmailAddress", userInputEmailAddress);
                        editor.commit();


                        UserInfo userInfo = new UserInfo(userNumber,userInputEmailAddress);
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("UserInfo", userInfo);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "이메일주소와 비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
*/

                //loading.dismiss();
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String CardNumber = (String)params[0];
                    String EmptyData = (String)params[1];

                    String data  = URLEncoder.encode("CardNumber", "UTF-8") + "=" + URLEncoder.encode(CardNumber, "UTF-8");
                    data += "&" + URLEncoder.encode("EmptyData", "UTF-8") + "=" + URLEncoder.encode(EmptyData, "UTF-8");

                    URL url = new URL(addcons_link);
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
        task.execute(cardnumber,emptydata);
    }



}


