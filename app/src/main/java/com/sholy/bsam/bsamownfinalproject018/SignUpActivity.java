package com.sholy.bsam.bsamownfinalproject018;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private EditText etFirstName,etLastName,etEmail1,etPassword,etPhone,etConfirmPassword;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail1 = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnSave = (Button) findViewById(R.id.btnSave);

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        dataHandler();
    }

    private void dataHandler() {
        boolean isk=true;
        String email=etEmail1.getText().toString();
        String passw1=etPassword.getText().toString();
        String fname=etFirstName.getText().toString();
        String passw2=etConfirmPassword.getText().toString();
        String lname=etLastName.getText().toString();
        String phone=etPhone.getText().toString();
        boolean isok = false;
        if (
                email.length() < 4 || email.indexOf('@') < 0 || email.indexOf('.') < 0
                ) {
            etEmail1.setError("wrong email");
            isok = false;
        }
        if (passw1.length()<8){
            etPassword.setError("Have to be at least 8 char");
            isok = false;
        }
        if (passw1.length() == passw2.length()){
            etConfirmPassword.setError("passwords have to be matched");
            isok = false;
        }
        if (isok){
            creatAcount(email,passw1);
        }
    }


    private void creatAcount (String email , String passw)
    {
        auth.createUserWithEmailAndPassword(email, passw).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Authentication Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(SignUpActivity.this,"Authentication Failed"+task.getException().toString(),Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });

    }
}


