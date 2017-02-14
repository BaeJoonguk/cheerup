package com.elsol.user.cheerup.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import static com.elsol.user.cheerup.Activity.WASIPAddress.login_link;

/* 클래스 이름 :public class LoginActivity extends AppCompatActivity implements View.OnClickListener
* 주요 기능 :로그인에 관한 기능
* 멤버 변수
* private Button login_button;
    private Button register_button;
    private static final String TAG
    private static final int RC_SIGN_IN
    String myJSON;
    private static final String TAG_RESULTS
    private static final String TAG_USERNUMBER
    private static final String TAG_PASSWORD
    JSONArray cards
    private EditText editTextEmailAddress;
    private EditText editTextPassword;
    String userInputPassword;
    String userInputEmailAddress;
    Boolean isCheckEmailAddressAndPassword
* 메소드
* protected void onCreate(Bundle savedInstanceState)
* 매개변수 : Bundle savedInstanceState
* 주요 기능 : 뷰의 생성자
* public void initview()
* 매개변수 :
* 주요 기능 : 클래스 내의 객체들을 실체화 시킴
* public void onClick(View v)
* 매개변수 :View v
* 주요 기능 : 클릭 시 발생하는 액티비티 처리
* private void login()
* 매개변수 :
* 주요 기능 : 입력받은 이메일/비밀번호를 데이터베이스에 삽입하고 같은 정보가있는지 비교
* private void insertToDatabase(String emailaddress, String password)
* 매개변수 :String emailaddress, String password
* 주요 기능 : 데이터베이스에 삽입
* public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
* 매개변수 :@NonNull ConnectionResult connectionResult
* 주요 기능 : 연결불가에 대한 경고기능
*/

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button login_button;
    private Button register_button;

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

        initview();
    }

    public void initview() {

        login_button = (Button) findViewById(R.id.login_button); // 로그인 버튼
        register_button = (Button) findViewById(R.id.register_button); // 회원가입 버튼

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
            // JoinMemberActivity로 전환
            Intent intent = new Intent(getApplicationContext(),JoinMemberActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void login() {

        userInputEmailAddress = editTextEmailAddress.getText().toString();
        userInputPassword = editTextPassword.getText().toString();

        // 사용자가 입력한 이메일과 비밀번호를 서버에 전송
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
                    String userNumber = c.getString(TAG_USERNUMBER);

                    // 사용자가 입력한 비밀번호와 서버에 저장된 비밀번호가 일치하는지 확인
                    if(userInputPassword.equals(password))
                        isCheckEmailAddressAndPassword = true;
                    else
                        isCheckEmailAddressAndPassword = false;


                    if(isCheckEmailAddressAndPassword)
                    {
                        // 비밀번호가 일치하는 경우 SharedPreferences에 이메일주소와 UserNumber를 저장
                        SharedPreferences prefs = getApplicationContext().getSharedPreferences("UserInfo", getApplicationContext().MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("EmailAddress", userInputEmailAddress);
                        editor.putString("UserNumber", userNumber);
                        editor.commit();

                        Toast.makeText(getApplicationContext(), "환영합니다", Toast.LENGTH_LONG).show();

                        // MainActivity로 전환
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
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

                    String data  = URLEncoder.encode("EmailAddress", "UTF-8") + "=" + URLEncoder.encode(EmailAddress, "UTF-8");
                    data += "&" + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8");

                    URL url = new URL(login_link);
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