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

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;
import es.dmoral.toasty.Toasty;
import ustaad.aladin.com.R;
import ustaad.aladin.com.Utils.Endpoints;
import ustaad.aladin.com.Utils.Utils;
import ustaad.aladin.com.classes.Animation;
import ustaad.aladin.com.fcm_classes.SharedPrefManager;

public class Signup extends AppCompatActivity {

    Button sing_in,btn_sign;
    EditText nametxt,usertxt,emailtxt,passtxt;
    private SweetAlertDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();

        sing_in = findViewById(R.id.sing_in_btn);
        sing_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, Signin.class));
                Animation.slideDown(Signup.this);
                finish();
            }
        });
    }

    private void init(){
        nametxt=findViewById(R.id.nametxt);
        usertxt=findViewById(R.id.usertxt);
        emailtxt=findViewById(R.id.emailtxt);
        passtxt=findViewById(R.id.passtxt);
        btn_sign=findViewById(R.id.btn_sign);

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scaningallfields();
            }
        });
    }

    private void scaningallfields(){
        if(!TextUtils.isEmpty(nametxt.getText().toString()) & !TextUtils.isEmpty(usertxt.getText().toString()) & !TextUtils.isEmpty(emailtxt.getText().toString()) & !TextUtils.isEmpty(passtxt.getText().toString())
               ){

            String token = SharedPrefManager.getInstance(this).getDeviceToken();

            //if token is not null
            if (token != null) {
                //calling funtion
                signup(nametxt.getText().toString(),usertxt.getText().toString(),emailtxt.getText().toString(),passtxt.getText().toString(),token);
            } else {
                signup(nametxt.getText().toString(),usertxt.getText().toString(),emailtxt.getText().toString(),passtxt.getText().toString(),"");
            }



        }else{
            new SweetAlertDialog(Signup.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Fill all Fields")
                    .show();
        }
    }

    // Declear the Registration Function
    private void signup(String name,String usrname, String email, String password,String token)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("req_key","user_registration");
        params.put("name",name);
        params.put("user_name",usrname);
        params.put("user_email",email);
        params.put("password",password);
        params.put("fcm_id",token);
        client.post(Endpoints.ip_server, params, new AsyncHttpResponseHandler()
        {
            @Override
            public void onStart()
            {
                super.onStart();
                pd = Utils.showProgress(Signup.this);
                pd.show();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = Utils.getResponse(responseBody);
                if(response.equals("null")) {
                    Toasty.warning(Signup.this, "Response is null", Toast.LENGTH_SHORT).show();
                }else {

                    try {
                        JSONObject object  = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                        if(object.getBoolean("success")) {
                            Toasty.success(Signup.this,object.getString("message"),Toast.LENGTH_LONG).show();
                            finish();
                        }else {
                            new SweetAlertDialog(Signup.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("Try Again")
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
                    Toasty.warning(Signup.this, "Unable to Connect Server", Toast.LENGTH_SHORT).show();

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
    public void finish() {
        super.finish();
        Animation.slideDown(Signup.this);
    }
}
