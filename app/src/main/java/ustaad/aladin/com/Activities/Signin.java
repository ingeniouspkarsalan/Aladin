package ustaad.aladin.com.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ustaad.aladin.com.R;
import ustaad.aladin.com.classes.Animation;

public class Signin extends AppCompatActivity {

    Button signupbtn, loginbtn, forgetbtn;
    EditText email,password;


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

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(1);
    }

}
