package com.e.fitbit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;



public class register extends AppCompatActivity {
    EditText femail, fname, lname, phone,  height, weight, profs, pass, cpass;
    String email, fn, ln, ph, d, hei, wei, pro, pas, cpas, gen, UserId;
    RequestQueue requestQueue;
    Spinner gender;
    Button reg;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    public static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        requestQueue= Volley.newRequestQueue(this);
        femail = findViewById(R.id.email);
        fname = findViewById(R.id.firstname);
        lname = findViewById(R.id.lastname);
        phone = findViewById(R.id.phone);

        gender = findViewById(R.id.gender);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        profs = findViewById(R.id.prof);
        pass = findViewById(R.id.password);
        cpass = findViewById(R.id.cpassword);
        reg = findViewById(R.id.register);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();


        if(firebaseAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = femail.getText().toString().trim();
                fn = fname.getText().toString().trim();
                ln = lname.getText().toString();
                ph = phone.getText().toString();
                gen = gender.getSelectedItem().toString();
                hei = height.getText().toString();
                wei = weight.getText().toString();
                pro = profs.getText().toString();
                pas = pass.getText().toString().trim();
                cpas = cpass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    femail.setError("Email Required");

                }else if (TextUtils.isEmpty(pas)) {
                        pass.setError("Password Required");

                    }
                    else if (pas.length() < 6) {
                        pass.setError("Minimum Password character is 6");
                         if (pas.equals(cpas)) {
                             Toast.makeText(register.this, "password matched", Toast.LENGTH_SHORT).show();

                    } else
                        pass.setError("Password doesn't match");
                    }
                    else {

                            sendData();
                            Intent regin= new Intent(register.this, Login.class);
                            startActivity(regin);


//                    firebaseAuth.createUserWithEmailAndPassword(email, pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
//                                UserId = firebaseAuth.getCurrentUser().getUid();
//                                DocumentReference reference = firestore.collection("users").document(UserId);
//                                Map<String, Object> user = new HashMap<>();
//                                user.put("femail", email);
//                                user.put("fname", fn);
//                                user.put("lname", ln);
//                                user.put("phone", ph);
//                                user.put("gender", gen);
//                                user.put("height", hei);
//                                user.put("weight", wei);
//                                user.put("pass", pas);
//                                user.put("profession", pro);
//                                reference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        Log.d(TAG, "onSuccess: User Profile is created for " + fn);
//                                    }
//                                });
//                                Intent log = new Intent(register.this, Login.class);
//                                startActivity(log);
//                            } else {
//                                Toast.makeText(register.this, "Error!:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });


                }


            }
        });
    }

        public void sendData(){

            StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/android/includes/register.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("response", response);
                    Toast.makeText(register.this, response, Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error", error.toString());

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> parameters = new HashMap<>();
                    parameters.put("email", email);
                    parameters.put("firstname", fn);
                    parameters.put("lastname", ln);
                    parameters.put("phone", ph);
                    parameters.put("dob", d);
                    parameters.put("gender", gen);
                    parameters.put("height", hei);
                    parameters.put("weight", wei);
                    parameters.put("prof", pro);
                    parameters.put("password", pas);


                    return parameters;
                }
            };

            requestQueue.add(request);
        }
    }


