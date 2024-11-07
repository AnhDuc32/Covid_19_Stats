package com.hfad.something;

import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartScreen extends AppCompatActivity {
   
    Button startButton;
    String name,email,password, apiKey;
    TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    TextView textViewError;
    ProgressBar progressBar;
    SharedPreferences  sharedPreferences;
    /*
    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        // Find views by ID
        TextInputLayout textInputLayout = findViewById(R.id.TextInputLayout);
        TextInputEditText textInputEditText = findViewById(R.id.InputPassword);

        // Add a TextWatcher to listen for changes in the EditText field
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // Code to handle before text change
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String passwordInput = charSequence.toString();
                if (passwordInput.length() >=100) {
                    Pattern pattern = Pattern.compile("^a-zA-Z0-9");
                    Matcher matcher = pattern.matcher(passwordInput);
                    boolean passwordsMatch = matcher.find();
                    if (passwordsMatch) {
                        textInputLayout.setHelperText("Your password are Strong");
                        textInputLayout.setError("");
                    }else {

                        textInputLayout.setError("mix of letters");

                    }


                }
                // Code to handle text change
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Code to handle after text change
            }
        });
    }

     */




    //private boolean passwordShowing = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextPassword = findViewById(R.id.password);
        // final ImageView passwordIcon = findViewById(R.id.passwordIcon);
        final TextView signup = findViewById(R.id.signup);
         textViewError = findViewById(R.id.error);
         progressBar = findViewById(R.id.loading);
         sharedPreferences = getSharedPreferences("Covid_19_Stats", MODE_PRIVATE);

        if(sharedPreferences.getString("logged", "").equals("true")){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }








        /*
        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordShowing) {
                    passwordShowing = false;
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.eyes); // Show the 'closed eye' icon
                } else {
                    passwordShowing = true;
                    passwordEditText.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.eyes_hide); // Show the 'open eye' icon
                }
                passwordEditText.setSelection(passwordEditText.length()); // Retain cursor position
            }
        });

         */




        ImageView splashImage = findViewById(R.id.splashImage);
        ObjectAnimator animator = ObjectAnimator.ofFloat(splashImage, "rotation", 0f, 360f);
        animator.setDuration(5000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();


        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewError.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                email = String.valueOf(textInputEditTextEmail.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://172.16.0.148/login_register/login.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String status = jsonObject.getString("status");
                                    String message = jsonObject.getString("message");
                                    if (status.equals("success")) {
                                         name = jsonObject.getString("name");
                                         email = jsonObject.getString("email");
                                         apiKey = jsonObject.getString("apiKey");
                                         SharedPreferences.Editor editor = sharedPreferences.edit();
                                         editor.putString("logged", "true");
                                         editor.putString("name", name);
                                         editor.putString("email", email);
                                         editor.putString("apiKey", apiKey);
                                         editor.apply();
                                         Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                         startActivity(intent);
                                         finish();
                                    } else {
                                        textViewError.setText(message);
                                        textViewError.setVisibility(View.VISIBLE);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
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
                        paramV.put("email", email);
                        paramV.put("password", password);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartScreen.this, SignUp.class));
            }
        });
    }


}