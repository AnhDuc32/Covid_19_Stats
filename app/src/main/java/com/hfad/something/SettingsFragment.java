package com.hfad.something;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SettingsFragment extends Fragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        RelativeLayout hotline = view.findViewById(R.id.hotline);
        RelativeLayout hospital = view.findViewById(R.id.hospital);
        RelativeLayout report = view.findViewById(R.id.report);

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
                // Handle click for linearLayout2
                showPopup(R.layout.fragment_hospital_popup);
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for linearLayout3
                showPopup(R.layout.fragment_consult_popup);
            }
        });

        return view;
    }

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
        dialog.show();
    }
}
