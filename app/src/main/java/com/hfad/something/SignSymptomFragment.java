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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignSymptomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignSymptomFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignSymptomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignSymptomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignSymptomFragment newInstance(String param1, String param2) {
        SignSymptomFragment fragment = new SignSymptomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;


            layoutParams.horizontalMargin = 0.3f;
            window.setAttributes(layoutParams);
        }


        dialog.show();
    }




}