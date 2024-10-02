package com.hfad.something;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Help#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Help extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Help() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HElp.
     */
    // TODO: Rename and change types and number of parameters
    public Help newInstance(String param1, String param2) {
        Help fragment = new Help();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        // Get references to each LinearLayout
        LinearLayout linearLayout1 = view.findViewById(R.id.hotline);
        LinearLayout linearLayout2 = view.findViewById(R.id.doctor);
        LinearLayout linearLayout3 = view.findViewById(R.id.hospital);
        LinearLayout linearLayout4 = view.findViewById(R.id.consult);

        // Set click listeners for each LinearLayout
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for linearLayout1
               showPopup(R.layout.fragment_phone_popup);
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for linearLayout2
                showPopup(R.layout.fragment_doctor_popup);
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for linearLayout3
                showPopup(R.layout.fragment_hospital_popup);
            }
        });

        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for linearLayout4
                showPopup(R.layout.fragment_consult_popup);
            }
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
