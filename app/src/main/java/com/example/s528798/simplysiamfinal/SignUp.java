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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener  {
    public EditText mName,mNumber,mEmail,mPassword;
    private TextView mRegistered;
    private Button mSignUp;
    String emailFinal,number,passwordFinal,name;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mName = (EditText) findViewById(R.id.name);
        mNumber = (EditText) findViewById(R.id.number);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mRegistered = (TextView)findViewById(R.id.registered);
        mSignUp = (Button) findViewById(R.id.signUp);

        mSignUp.setOnClickListener(this);
        mRegistered.setOnClickListener(this);
        progressBar = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    private void registerUser() {
        emailFinal = mEmail.getText().toString().trim();
        passwordFinal = mPassword.getText().toString().trim();
        number = mNumber.getText().toString();
        name = mName.getText().toString().trim();
        final UserdatabaseInformation dbinfo = new UserdatabaseInformation(name);

        if(TextUtils.isEmpty(emailFinal)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(emailFinal.contains("@")){

        }
        else{
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(passwordFinal)){
            //if password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(passwordFinal.length() <=5 ){
            Toast.makeText(this, "Password length should be more that 5 charecters", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setMessage("Registering User...");
        progressBar.show();
        firebaseAuth.createUserWithEmailAndPassword(emailFinal,passwordFinal)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this, "Sucessful", Toast.LENGTH_SHORT).show();
                            FirebaseUser user  = firebaseAuth.getCurrentUser();
                            databaseReference.child(user.getUid()).setValue(name);
                            Intent i = new Intent(SignUp.this,MainPage.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(SignUp.this, "Failed to register, Please try after sometime", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == mSignUp){
            registerUser();
        }
        if(view == mRegistered){

            Intent i = new Intent(SignUp.this,Login.class);
            startActivity(i);
        }

    }


}
