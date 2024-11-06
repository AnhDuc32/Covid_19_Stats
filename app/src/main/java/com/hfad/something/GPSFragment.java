package com.hfad.something;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;


public class GPSFragment extends Fragment {

    public GPSFragment(){
        // empty public constructor
    }

    TextView tv_lat, tv_lon, tv_altitude, tv_accuracy, tv_speed, tv_sensor, tv_updates, tv_address;

    Switch sw_locationsupdates, sw_gps;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_g_p_s, container, false);

        //give UI variable a value
        tv_lat = view.findViewById(R.id.tv_lat);
        tv_lon = view.findViewById(R.id.tv_lon);
        tv_altitude = view.findViewById(R.id.tv_altitude);
        tv_accuracy = view.findViewById(R.id.tv_accuracy);
        tv_speed = view.findViewById(R.id.tv_speed);
        tv_sensor = view.findViewById(R.id.tv_sensor);
        tv_updates = view.findViewById(R.id.tv_updates);
        tv_address = view.findViewById(R.id.tv_address);
        sw_locationsupdates = view.findViewById(R.id.sw_locationsupdates);
        sw_gps = view.findViewById(R.id.sw_gps);

        return view;
    }
}