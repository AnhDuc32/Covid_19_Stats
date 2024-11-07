package com.hfad.something;

import static com.hfad.something.R.*;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextName,textInputEditTextEmail, textInputEditTextPassword,textInputEditTextconPassword,textInputEditTextPhone;
    String name, email, password,phone, conPassword;

    TextView textViewError;
    ProgressBar progressBar;






    //private boolean passwordShowing = false;
    //private boolean conPasswordShowing = false;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_symptom, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextName = findViewById(id.fullName);
        textInputEditTextPassword = findViewById(id.password);
        textInputEditTextconPassword = findViewById(id.conPassword);
        textInputEditTextPhone = findViewById(id.mobile);
        textViewError = findViewById(R.id.error);






        // final ImageView conPasswordIcon = findViewById(R.id.conPasswordIcon);


        // final ImageView passwordIcon = findViewById(R.id.passwordIcon);

        final Button signUpButton = findViewById(R.id.signUpButton);
        final TextView signIn = findViewById(R.id.signIn);
        progressBar = findViewById(R.id.loading);

        /*
        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordShowing) {
                    // Hide password
                    passwordShowing = false;
                    textInputEditTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    textInputEditTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordIcon.setImageResource(R.drawable.eyes); // Closed eye icon
                } else {
                    // Show password
                    passwordShowing = true;
                    textInputEditTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    textInputEditTextPassword.setTransformationMethod(null);
                    passwordIcon.setImageResource(R.drawable.eyes_hide); // Open eye icon
                }
                // Retain cursor position
                textInputEditTextPassword.setSelection(textInputEditTextPassword.length());
            }
        });
        */



        /*
        conPasswordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conPasswordShowing) {
                    // Hide password
                    conPasswordShowing = false;
                    textInputEditTextconPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    textInputEditTextconPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    conPasswordIcon.setImageResource(R.drawable.eyes); // Closed eye icon
                } else {
                    // Show password
                    conPasswordShowing = true;
                    textInputEditTextconPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    textInputEditTextconPassword.setTransformationMethod(null);
                    conPasswordIcon.setImageResource(R.drawable.eyes_hide); // Open eye icon
                }
                // Retain cursor position
                textInputEditTextconPassword.setSelection(textInputEditTextconPassword.length());
            }
        });
        */



        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = String.valueOf(textInputEditTextPhone.getText());
                name = String.valueOf(textInputEditTextName.getText());
                email = String.valueOf(textInputEditTextEmail.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                conPassword = String.valueOf(textInputEditTextconPassword.getText());
                //Intent intent = new Intent(SignUp.this, OTPVerification.class);
                //intent.putExtra("email", getEmailText);
                //startActivity(intent);

                if (email.isEmpty()) {
                    textInputEditTextEmail.setError("Email is required");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    textInputEditTextEmail.setError("Please enter a valid email address");
                    return;
                }
                if (phone.isEmpty()) {
                    textInputEditTextPhone.setError("Phone number is required");
                    return;
                }
                if (name.isEmpty()) {
                    textInputEditTextName.setError("Name is required");
                    return;
                }
                if (password.isEmpty()) {
                    textInputEditTextPassword.setError("Password is required");
                    return;
                }
                if (!password.equals(conPassword)) {
                    textInputEditTextconPassword.setError("Passwords do not match");
                    return;
                }


                textViewError.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://172.16.0.148/login_register/register.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                if (response.equals("success")){
                                    Toast.makeText(getApplicationContext(), "Successfully Sign Up", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), StartScreen.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    textViewError.setText(response);
                                    textViewError.setVisibility(View.VISIBLE);
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        textViewError.setText(error.getLocalizedMessage());
                        textViewError.setVisibility(View.VISIBLE);

                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("name", name);
                        paramV.put("email",  email);
                        paramV.put("phone", phone);
                        paramV.put("password", password);
                        return paramV;
                    }
                };
                queue.add(stringRequest);

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}
