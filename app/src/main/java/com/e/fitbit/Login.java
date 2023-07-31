package com.e.fitbit;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {
    EditText femail, pass;
    String email, pas;
    Button signin;
    TextView signup;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signin = findViewById(R.id.signIn);
        femail = findViewById(R.id.email_login);
        pass= findViewById(R.id.password_login);
        signup = findViewById(R.id.nothing);
        auth= FirebaseAuth.getInstance();


        email= femail.getText().toString().trim();
        pas= pass.getText().toString().trim();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup.setTextColor(Color.parseColor("#800000"));
                Intent sign = new Intent(Login.this,register.class);
                startActivity(sign);


            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start_home= new Intent(Login.this, Home.class);
                startActivity(start_home);

//                if(email.equals("")){
//                    femail.setError("please enter Email");
//
//                }
//                else if(pas.equals("")){
//                    pass.setError("Enter password ");
//
//                }else {
//
////                    auth.signInWithEmailAndPassword(email, pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
////                        @Override
////                        public void onComplete(@NonNull Task<AuthResult> task) {
////                            if (task.isSuccessful()) {
////                                Toast.makeText(Login.this, "Please wait...", Toast.LENGTH_SHORT).show();
////                                Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_LONG).show();
////                                startActivity(new Intent(getApplicationContext(), Home.class));
////                            } else {
////                                Toast.makeText(Login.this, "Oops!! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
////                            }
////                        }
////                    });
                }

            }
        });





    }

//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this).setTitle("Do youu want to Exit?").setNegativeButton(android.R.string.no,null)
//                .setPositiveButton(android.R.string.yes, new OnClickListener(){
//                    public void  onClick(DialogInterface arg0, int arg1){
//                        Login.super.onBackPressed();
//                    }
//                }).create().show();
//
//    }
}


