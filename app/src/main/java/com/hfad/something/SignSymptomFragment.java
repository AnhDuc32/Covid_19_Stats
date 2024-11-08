package com.hfad.something;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.concurrent.LinkedBlockingQueue;

public class SignSymptomFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_symptom, container, false);

        // Get references to each LinearLayout
        LinearLayout linearLayout1 = view.findViewById(R.id.button1);
        LinearLayout linearLayout2 = view.findViewById(R.id.button2);
        LinearLayout linearLayout3 = view.findViewById(R.id.button3);
        LinearLayout linearLayout4 = view.findViewById(R.id.button4);
        LinearLayout linearLayout5 = view.findViewById(R.id.button5);
        LinearLayout linearLayout6 = view.findViewById(R.id.button6);
        LinearLayout linearLayout7 = view.findViewById(R.id.button7);
        LinearLayout linearLayout8 = view.findViewById(R.id.button8);
        LinearLayout linearLayout9 = view.findViewById(R.id.button9);
        LinearLayout linearLayout10 = view.findViewById(R.id.button10);
        // Set click listeners for each LinearLayout
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for linearLayout1
                showPopup(R.layout.popup1);
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(R.layout.popup2);
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(R.layout.popup3);
            }
        });

        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(R.layout.popup4);
            }
        });

        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(R.layout.popup5);
            }
        });

        linearLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(R.layout.popup6);
            }
        });

        linearLayout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(R.layout.popup7);
            }
        });

        linearLayout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(R.layout.popup8);
            }
        });

        linearLayout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(R.layout.popup9);
            }
        });

        linearLayout10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(R.layout.popup10);
            }
        });

        return view;
    }


    private void showPopup(int layoutResId) {
        Dialog dialog = new Dialog(getContext());


        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(layoutResId, null);
        dialog.setContentView(popupView);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        dialog.dismiss();
                    }
                },
                3000);

        if (dialog.getWindow() != null) {

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


            Window window = dialog.getWindow();
            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.CENTER;

            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            layoutParams.width = (int) (screenWidth * 0.927);
            window.setAttributes(layoutParams);
        }


        dialog.show();
    }




}