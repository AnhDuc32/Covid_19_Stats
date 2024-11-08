package com.hfad.something;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    SharedPreferences  sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("Covid_19_Stats", MODE_PRIVATE);
        if(sharedPreferences.getString("logged", "").equals("false")){
            Intent intent = new Intent(getApplicationContext(), StartScreen.class);
            startActivity(intent);
            finish();
        }

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setIcon(R.drawable.baseline_home_24);
                    break;
                case 1:
                    tab.setIcon(R.drawable.bar_chart);
                    break;
                case 2:
                    tab.setIcon(R.drawable.virus);
                    break;
                case 3:
                    tab.setIcon(R.drawable.haphoi);
                    break;
                case 4:
                    tab.setIcon(R.drawable.baseline_settings_24);
                    break;
                case 5:
                    tab.setIcon(R.drawable.gps_svgrepo_com);
            }
        }).attach();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            // Open the SettingsFragment
            SettingsFragment settingsFragment = new SettingsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, settingsFragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class Help extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_help, container, false);

            // Get references to each LinearLayout
            LinearLayout linearLayout1 = view.findViewById(R.id.hotline);
            LinearLayout linearLayout3 = view.findViewById(R.id.hospital);

            // Set click listeners for each LinearLayout
            linearLayout1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle click for linearLayout1
                   showPopup(R.layout.fragment_phone_popup);
                }
            });

            linearLayout3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle click for linearLayout3
                    showPopup(R.layout.fragment_hospital_popup);
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
}
