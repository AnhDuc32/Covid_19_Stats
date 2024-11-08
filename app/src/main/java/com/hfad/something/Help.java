package com.hfad.something;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class Help extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        // Get references to each LinearLayout
        LinearLayout hotline = view.findViewById(R.id.hotline);
        LinearLayout hospital = view.findViewById(R.id.hospital);
        LinearLayout report = view.findViewById(R.id.report_form);

        // Set click listeners for each LinearLayout
        hotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for linearLayout1
               showPopup(R.layout.fragment_phone_popup);
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for linearLayout3
                showPopup(R.layout.fragment_hospital_popup);
            }
        });

        report.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ReportForm.class);
            startActivity(intent);
        });

        return view;
    }

    // Method to show a custom XML layout as a pop-up
    private void showPopup(int layoutResId) {
        // Create a Dialog instance
        Dialog dialog = new Dialog(getContext());

        // Inflate the custom XML layout
        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(layoutResId, null);

        // Set the custom layout as the content view for the dialog
        dialog.setContentView(popupView);

        // Optional: Set dialog properties (width, height, etc.)
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        // Find and set up views from the inflated layout
        Button buttonDismiss = popupView.findViewById(R.id.dismiss);
        buttonDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // Close the dialog when the button is clicked
            }
        });

        // Show the dialog
        dialog.show();
    }
}