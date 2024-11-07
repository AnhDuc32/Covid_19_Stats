package com.hfad.something;

import static com.google.android.material.internal.ViewUtils.showKeyboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

public class OTPVerification extends AppCompatActivity {
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0){
                if(selectedETPosition == 0){
                    selectedETPosition = 1;
                    showKeyboard(otpEt2);
                }
                else if (selectedETPosition == 1) {
                    selectedETPosition = 2;
                    showKeyboard(otpEt3);
                }
                else if (selectedETPosition == 2)  {
                    selectedETPosition = 3;
                    showKeyboard(otpEt4);
                }
            }
        }
    };

    private EditText otpEt1, otpEt2, otpEt3, otpEt4;

    private int selectedETPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        otpEt1 = findViewById(R.id.otpET1);
        otpEt2 = findViewById(R.id.otpET2);
        otpEt3 = findViewById(R.id.otpET3);
        otpEt4 = findViewById(R.id.otpET4);



        final Button verifyBtn = findViewById(R.id.verifyBtn);

        final TextView otpEmail = findViewById(R.id.otpEmail);



        final String getEmail = getIntent().getStringExtra("email");

        otpEmail.setText(getEmail);


        otpEt1.addTextChangedListener(textWatcher);
        otpEt2.addTextChangedListener(textWatcher);
        otpEt3.addTextChangedListener(textWatcher);
        otpEt4.addTextChangedListener(textWatcher);

        showKeyboard(otpEt1);



        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String generateOtp = otpEt1.getText().toString() + otpEt2.getText().toString() + otpEt3.getText().toString() + otpEt4.getText().toString();
                startActivity(new Intent(OTPVerification.this, StartScreen.class));
                if (generateOtp.length() == 4) {

                }
            }
        });
    }


        private void showKeyboard (EditText otpET){
            otpET.requestFocus();
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(otpET, InputMethodManager.SHOW_IMPLICIT);

        }


        @Override
        public boolean onKeyUp ( int keyCode, KeyEvent event){
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (selectedETPosition == 3) {
                    selectedETPosition = 2;
                    showKeyboard(otpEt3);
                } else if (selectedETPosition == 2) {
                    selectedETPosition = 1;
                    showKeyboard(otpEt2);
                } else if (selectedETPosition == 1) {
                    selectedETPosition = 0;
                    showKeyboard(otpEt1);
                }

                return true;


            } else {
                return super.onKeyUp(keyCode, event);
            }

        }

    }
