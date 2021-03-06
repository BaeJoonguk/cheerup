package com.elsol.user.cheerup.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.elsol.user.cheerup.Activity.CardDetail_Activity;
import com.elsol.user.cheerup.GetnSet.MainFragListItem;
import com.elsol.user.cheerup.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.elsol.user.cheerup.Activity.WASIPAddress.addcons_link;
import static com.elsol.user.cheerup.Activity.WASIPAddress.addpros_link;

/**
 클래스명 : public class MainFragAdapter extends RecyclerView.Adapter<MainViewHolder>
 주된기능 : MainFragListItem의 정보를 어댑터를 통해 받아들인다.
 멤버변수 :
 String myJSON;
 private static final String BUNDLE_EXTRAS
 private static final String EXTRA_QUESTION
 private static final String TAG_RESULTS
 private static final String TAG_PROS
 private static final String TAG_CONS
 JSONArray ProsAndCons
 Context c
 ArrayList<MainFragListItem> listData

 메소드 :
 public void setItemClickCallback(final ItemClickCallback itemClickCallback)
 * 매개변수: final ItemClickCallback itemClickCallback
 * 기능 : 아이템을 눌렀을 때 반응함
 public CommentAdapter(List<CommentListItem> listData, Context c)
 * 매개변수 :List<CommentListItem> listData, Context c
 * 기능 : CommentListItem의 데이터를 가지고 뷰를 생성
 public DerpHolder onCreateViewHolder(ViewGroup parent, int viewType)
 * 매개변수 : ViewGroup parent, int viewType
 * 기능 : viewholder를 생성
 public void onBindViewHolder(DerpHolder holder, int position)
 * 매개변수 : DerpHolder holder, int position
 * 기능 : 만들어진 ViewHolder에 데이터를 넣는 작업
 public int getItemCount()
 * 매개변수 :
 * 기능 : 데이터 갯수 반환 */

public class MainFragAdapter extends RecyclerView.Adapter<MainViewHolder> {

    String myJSON;

    private static final String BUNDLE_EXTRAS="BUNDLE_EXTRAS";
    private static final String EXTRA_QUESTION="EXTRA_QUESTION";
    private static final String TAG_RESULTS="result";
    private static final String TAG_PROS="Pros";
    private static final String TAG_CONS="Cons";

    JSONArray ProsAndCons = null;

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
                addPros(cardnumber, item.getQcontent(), v, item);
            }

        });
        //반대button
        holder.cons_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                Toast.makeText(c, "반대합니다", Toast.LENGTH_SHORT).show();

                String cardnumber = String.valueOf(item.getCardNumber());
                addCons(cardnumber, item.getQcontent(), v, item);

            }

        });

        //listener card 누르면
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 해당 카드의 CardDetail_Activity로 전환
                Intent intent = new Intent().setClass(v.getContext(),CardDetail_Activity.class);

                Bundle extras=new Bundle();
                extras.putString(EXTRA_QUESTION,item.getQcontent());
                extras.putString(TAG_PROS, String.valueOf(item.getPros_count()));
                extras.putString(TAG_CONS, String.valueOf(item.getCons_count()));
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

    private void addPros(String cardnumber, final String emptydata, final View v, final MainFragListItem item){

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

                myJSON=result;

                try {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    ProsAndCons = jsonObj.getJSONArray(TAG_RESULTS);

                    JSONObject c = ProsAndCons.getJSONObject(0);
                    String pros = c.getString(TAG_PROS);
                    String cons = c.getString(TAG_CONS);

                    item.setPros_count(Integer.parseInt(pros));
                    item.setCons_count(Integer.parseInt(cons));

                    // 해당 카드의 CardDetail_Activity로 전환
                    Intent intent = new Intent().setClass(v.getContext(),CardDetail_Activity.class);

                    Bundle extras=new Bundle();
                    extras.putString(EXTRA_QUESTION,emptydata);
                    extras.putString(TAG_PROS,pros);
                    extras.putString(TAG_CONS,cons);

                    intent.putExtra(BUNDLE_EXTRAS,extras);
                    v.getContext().startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    private void addCons(String cardnumber, final String emptydata, final View v, final MainFragListItem item){

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


                myJSON=result;

                try {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    ProsAndCons = jsonObj.getJSONArray(TAG_RESULTS);

                    JSONObject c = ProsAndCons.getJSONObject(0);
                    String pros = c.getString(TAG_PROS);
                    String cons = c.getString(TAG_CONS);

                    item.setPros_count(Integer.parseInt(pros));
                    item.setCons_count(Integer.parseInt(cons));

                    // 해당 카드의 CardDetail_Activity로 전환
                    Intent intent = new Intent().setClass(v.getContext(),CardDetail_Activity.class);

                    Bundle extras=new Bundle();
                    extras.putString(EXTRA_QUESTION,emptydata);
                    extras.putString(TAG_PROS,pros);
                    extras.putString(TAG_CONS,cons);

                    intent.putExtra(BUNDLE_EXTRAS,extras);
                    v.getContext().startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

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


