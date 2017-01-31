package com.example.user.cheerup.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.cheerup.Activity.MainActivity;
import com.example.user.cheerup.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


/**
 * Created by user on 2017-01-02.
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

                    String link="http://52.78.63.146/insert.php";

                    String data  = URLEncoder.encode("Contents", "UTF-8") + "=" + URLEncoder.encode(Contents, "UTF-8");
                    data += "&" + URLEncoder.encode("Writer", "UTF-8") + "=" + URLEncoder.encode(Writer, "UTF-8");



                    URL url = new URL(link);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.writeq_frag, container, false);
        Button write_button=(Button)root.findViewById(R.id.write_button) ;

        editTextContents = (EditText) root.findViewById(R.id.Q_editText);

        write_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                String contents = editTextContents.getText().toString();
                insertToDatabase(contents, "writer");

                Intent intent=new Intent(getActivity(), MainActivity.class);
                //Toast.makeText(,"질문등록 완료", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                getActivity().finish();
            }
        });

        return root;
    }

    //set Tab title for main_fragment
    @Override
    public String toString() {
        return "질문하기";
    }

}
