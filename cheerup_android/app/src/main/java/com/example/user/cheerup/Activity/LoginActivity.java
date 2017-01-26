package com.example.user.cheerup.Activity;

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

import com.example.user.cheerup.GetnSet.MainFragListItem;
import com.example.user.cheerup.R;
import com.example.user.cheerup.adapter.MainFragAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    private Button login_button;
    private Button register_button;

    SignInButton signInButton;
    Button signOutButton;
    TextView statusTextView;
    GoogleApiClient mGoogleApiClient;

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String TAG_USERNUMBER = "UserNumber";
    private static final String TAG_PASSWORD = "Password";

    JSONArray cards = null;

    private EditText editTextEmailAddress;
    private EditText editTextPassword;

    String userInputPassword;
    String userInputEmailAddress;

    Boolean isCheckEmailAddressAndPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        statusTextView = (TextView) findViewById(R.id.status_textview);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);

        signOutButton = (Button) findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(this);

        initview();

    }

    public void initview() {

        login_button = (Button) findViewById(R.id.login_button); //서적등록버튼
        register_button = (Button) findViewById(R.id.register_button); //도서검색버튼

        editTextEmailAddress = (EditText) findViewById(R.id.user_id2);
        editTextPassword = (EditText) findViewById(R.id.passwd2);

        login_button.setOnClickListener(this);
        register_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==login_button)
        {
            login();
        }
        else if(v==register_button)
        {
            Intent intent = new Intent(getApplicationContext(),JoinMemberActivity.class);
            startActivity(intent);
            finish();
        }

        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.signOutButton:
                signOut();
                break;
        }

    }

    private void login() {

        userInputEmailAddress = editTextEmailAddress.getText().toString();
        userInputPassword = editTextPassword.getText().toString();

        insertToDatabase(userInputEmailAddress, userInputPassword);
    }

    private void insertToDatabase(String emailaddress, String password){

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

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

                loading.dismiss();
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String EmailAddress = (String)params[0];
                    String Password = (String)params[1];

                    String link="http://IP Address/login.php";
                    String data  = URLEncoder.encode("EmailAddress", "UTF-8") + "=" + URLEncoder.encode(EmailAddress, "UTF-8");
                    data += "&" + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8");

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
        task.execute(emailaddress,password);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult: " + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            statusTextView.setText("Hello, " + acct.getDisplayName());
        } else {
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }

    private void signOut(){
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                statusTextView.setText("Signed out");
            }
        });
    }
}