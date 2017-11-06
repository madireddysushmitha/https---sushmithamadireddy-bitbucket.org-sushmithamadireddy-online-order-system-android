package com.example.s528798.simplysiamfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button mSignIn;
    private EditText mEmail;
    private EditText mPassword;
    private TextView mSignUp;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSignIn = (Button) findViewById(R.id.login);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mSignUp = (TextView) findViewById(R.id.signUp);
        progressBar = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        mSignIn.setOnClickListener(this);
        mSignUp.setOnClickListener(this);
        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(this,MainPage.class));
        }
    }

    private void userLogin() {

    String email = mEmail.getText().toString().trim();
    String password = mPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //if password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setMessage("Please wait...");
        progressBar.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.dismiss();
                        if(task.isSuccessful()){
                            startActivity(new Intent(Login.this,MainPage.class));
                        }
                        else{
                            Toast.makeText(Login.this, "Failed to login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
                if( view == mSignIn){
                    userLogin();
                }

                if( view == mSignUp){
                    finish();
                    startActivity(new Intent(this,SignUp.class));
                }
    }


}
