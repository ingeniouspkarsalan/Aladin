package ustaad.aladin.com.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pixplicity.easyprefs.library.Prefs;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;
import es.dmoral.toasty.Toasty;
import ustaad.aladin.com.R;
import ustaad.aladin.com.Utils.Endpoints;
import ustaad.aladin.com.Utils.Utils;
import ustaad.aladin.com.classes.Animation;

public class Signin extends AppCompatActivity {

    Button signupbtn, btn_sign, forgetbtn;
    EditText email,password;

    private SweetAlertDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signupbtn = findViewById(R.id.sing_up_btn);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signin.this, Signup.class));
                Animation.slideUp(Signin.this);
            }
        });
        init();
    }




    private void init(){
        email=findViewById(R.id.et_email);
        password=findViewById(R.id.et_pass);

        btn_sign=findViewById(R.id.loginbtn);

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scaningallfields();
            }
        });
    }

    private void scaningallfields(){
        if(!TextUtils.isEmpty(email.getText().toString()) & !TextUtils.isEmpty(password.getText().toString())){

           // sign_in(email.getText().toString(),password.getText().toString());
            startActivity(new Intent(Signin.this, Home.class));
            finish();
            Animation.slideUp(Signin.this);


        }else{
            new SweetAlertDialog(Signin.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Fill all Fields")
                    .show();
        }
    }


    // Declear the Registration Function
    private void sign_in(String email, String password)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("req_key","user_login");
        params.put("email",email);
        params.put("pass",password);
        client.post(Endpoints.ip_server, params, new AsyncHttpResponseHandler()
        {
            @Override
            public void onStart()
            {
                super.onStart();
                pd = Utils.showProgress(Signin.this);
                pd.show();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = Utils.getResponse(responseBody);
                if(response.equals("null")) {
                    Toasty.warning(Signin.this, "Response is null", Toast.LENGTH_SHORT).show();
                }else {

                    try {
                        JSONObject object  = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                        if(object.getBoolean("success")) {
                            Toasty.success(Signin.this,object.getString("message"),Toast.LENGTH_LONG).show();
                            Prefs.putString("user_id",object.getString("id"));
                            Prefs.putString("user_name",object.getString("name"));
                            Prefs.putString("user_email",object.getString("email"));
                            Prefs.putBoolean("loginSuccess",true); // change this value on logout
                            startActivity(new Intent(Signin.this, Home.class));
                            finish();
                            Animation.slideUp(Signin.this);

                        }else {
                            new SweetAlertDialog(Signin.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText(object.getString("message"))
                                    .show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("response",response);
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String  response  = Utils.getResponse(responseBody);
                if(response.equals("null")) {
                    Toasty.warning(Signin.this, "Unable to Connect Server", Toast.LENGTH_SHORT).show();

                }else {

                    Log.d("response",response);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                pd.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(1);
    }

}
