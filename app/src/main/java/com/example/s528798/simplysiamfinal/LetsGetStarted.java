package com.example.s528798.simplysiamfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LetsGetStarted extends AppCompatActivity {
    Button mLetsStart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_get_started);
        mLetsStart = (Button) findViewById(R.id.getStarted);
        mLetsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LetsGetStarted.this,LoginOrSignUp.class);
                startActivity(i);
            }
        });
    }
}
