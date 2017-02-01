package com.example.user.cheerup.Activity;

/**
 * Created by user on 2017-01-25.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.cheerup.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static com.example.user.cheerup.Activity.WASIPAddress.joinmember_link;


public class JoinMemberActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmailAddress;
    private EditText editTextPassword;
    private Button register_button;

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_member);

        initview();
        }

    public void initview() {

        editTextEmailAddress = (EditText) findViewById(R.id.user_email);
        editTextPassword = (EditText) findViewById(R.id.user_password);

        register_button = (Button) findViewById(R.id.register_button); //도서검색버튼

        register_button.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {

        if(v==register_button)
        {
            joinMember();

            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);

            startActivity(intent);
            finish();
        }
    }

    private void joinMember()
    {
        String mailAddress = editTextEmailAddress.getText().toString();
        String password = editTextPassword.getText().toString();

        insertToDatabase(mailAddress,password);
    }

    private void insertToDatabase(String emailaddress, String password){

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(JoinMemberActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
            }

        @Override
        protected String doInBackground(String... params) {

            try{
                String EmailAddress = (String)params[0];
                String Password = (String)params[1];

                String link="http://IP Address/joinmember.php";
                String data  = URLEncoder.encode("EmailAddress", "UTF-8") + "=" + URLEncoder.encode(EmailAddress, "UTF-8");
                data += "&" + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8");

                URL url = new URL(joinmember_link);
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
        task.execute(emailaddress,password);
        }
}