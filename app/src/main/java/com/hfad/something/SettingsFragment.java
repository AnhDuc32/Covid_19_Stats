package com.hfad.something;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SettingsFragment extends Fragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    Switch nightModeSwitch;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Initialize SharedPreferences
        sharedPreferences = getActivity().getSharedPreferences("Covid_19_Stats", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Initialize RequestQueue
        queue = Volley.newRequestQueue(getContext());

        // Setup Logout Button
        LinearLayout buttonLogout = view.findViewById(R.id.logoutButton);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change to your IPv4 address
                String url = "http://192.168.1.9/login_register/logout.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("success")) {
                                    editor.putString("logged", "");
                                    editor.putString("name", "");
                                    editor.putString("email", "");
                                    editor.putString("apiKey", "");
                                    editor.apply();

                                    Intent intent = new Intent(getActivity(), StartScreen.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                } else {
                                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Logout failed. Please try again.", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("email", sharedPreferences.getString("email", ""));
                        paramV.put("apiKey", sharedPreferences.getString("apiKey", ""));
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });

        return view;
    }
}
