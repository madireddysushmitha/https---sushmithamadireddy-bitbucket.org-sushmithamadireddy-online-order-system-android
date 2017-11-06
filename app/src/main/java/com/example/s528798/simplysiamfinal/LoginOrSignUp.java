package com.example.s528798.simplysiamfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class LoginOrSignUp extends AppCompatActivity {
    Button mLogin,mSignUp,mGuest;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_sign_up);
        setContentView(R.layout.activity_login_or_sign_up);
        mLogin = (Button) findViewById(R.id.login);
        mSignUp = (Button) findViewById(R.id.signUp);
        mGuest = (Button) findViewById(R.id.guestCheckout);
        progressBar = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginOrSignUp.this,SignUp.class));
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginOrSignUp.this,Login.class));
            }
        });

        mGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signInAnonymously();
                startActivity(new Intent(LoginOrSignUp.this,MainPage.class));
            }
        });


    }
}
