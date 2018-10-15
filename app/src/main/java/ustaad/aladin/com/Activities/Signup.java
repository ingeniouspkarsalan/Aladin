package ustaad.aladin.com.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ustaad.aladin.com.R;
import ustaad.aladin.com.classes.Animation;

public class Signup extends AppCompatActivity {

    Button sing_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        sing_in = findViewById(R.id.sing_in_btn);
        sing_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, Signin.class));
                Animation.slideDown(Signup.this);
            }
        });
    }


    @Override
    public void finish() {
        super.finish();
        Animation.slideDown(Signup.this);
    }
}
