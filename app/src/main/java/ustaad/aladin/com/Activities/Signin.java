package ustaad.aladin.com.Activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import ustaad.aladin.com.R;

public class Signin extends AppCompatActivity {
    RelativeLayout rellay1, rellay2;
    Button signup, loginbtn, forgetbtn;
    EditText email,password;



    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        //take Layout
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        //take button
        loginbtn = findViewById(R.id.loginbtn);
        forgetbtn = findViewById(R.id.forgetpassbtn);

        handler.postDelayed(runnable, 3000); // 3 Sec is the timeout for the splash

        signup = findViewById(R.id.singbtn);

        //Take Email and Pass
        email = findViewById(R.id.emailtxt);
        password = findViewById(R.id.passtxt);
    }
}
