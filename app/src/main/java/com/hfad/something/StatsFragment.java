package com.hfad.something;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.EventLogTags;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
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

    private LineChart lineChart;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats,container,false);
        lineChart = view.findViewById(R.id.lineChart);
        spinner = view.findViewById(R.id.spinner);
        setupSpinner();
        //Sample data
        setupChart("total_cases");
        return view;
    }

    private void setupSpinner() {
        String[] statistics = {"Total Cases", "Recovered", "Deaths", "Active Cases"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, statistics);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedStatistic = statistics[position].toLowerCase().replace(" ", "_");
                setupChart(selectedStatistic);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void setupChart(String statistics) {
        // Sample data for demonstration
        ArrayList<Entry> entries = new ArrayList<>();
        switch (statistics) {
            case "total_cases":
                entries.add(new Entry(0, 1000));
                entries.add(new Entry(1, 2000));
                entries.add(new Entry(2, 1500));
                entries.add(new Entry(3, 3000));
                entries.add(new Entry(4, 2500));
                break;
            case "recovered":
                entries.add(new Entry(0, 800));
                entries.add(new Entry(1, 1800));
                entries.add(new Entry(2, 1200));
                entries.add(new Entry(3, 2500));
                entries.add(new Entry(4, 2000));
                break;
            case "deaths":
                entries.add(new Entry(0, 50));
                entries.add(new Entry(1, 100));
                entries.add(new Entry(2, 75));
                entries.add(new Entry(3, 150));
                entries.add(new Entry(4, 120));
                break;
            case "active_cases":
                entries.add(new Entry(0, 150));
                entries.add(new Entry(1, 100));
                entries.add(new Entry(2, 200));
                entries.add(new Entry(3, 350));
                entries.add(new Entry(4, 250));
                break;
        }

        LineDataSet dataSet = new LineDataSet(entries, "Statistics");
        dataSet.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setDrawCircles(true);
        dataSet.setDrawValues(true);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate(); // Refresh the chart
        lineChart.getDescription().setEnabled(false); // Disable description
        lineChart.getXAxis().setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisLeft().setDrawGridLines(true);
        lineChart.getAxisRight().setEnabled(false); // disable right axis
    }
}

