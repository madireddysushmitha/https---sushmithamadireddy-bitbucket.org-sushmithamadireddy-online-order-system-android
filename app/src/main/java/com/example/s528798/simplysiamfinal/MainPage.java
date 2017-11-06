package com.example.s528798.simplysiamfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainPage extends AppCompatActivity implements View.OnClickListener {

    private Button mLogout;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mLogout = (Button) findViewById(R.id.logout);
        mLogout.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,Login.class));
        }


    }

    @Override
    public void onClick(View view) {
        if(view == mLogout){
            firebaseAuth.signOut();
            startActivity(new Intent(this,LoginOrSignUp.class));
        }

    }
}
