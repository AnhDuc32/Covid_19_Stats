package com.hfad.something;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class StatsFragment extends Fragment {

    private LineChart lineChart;
    private Spinner spinner;
    private PieChart pieChart;

    public StatsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        pieChart = view.findViewById(R.id.piechart);
        lineChart = view.findViewById(R.id.lineChart);
        spinner = view.findViewById(R.id.spinner);
        setupSpinner();
        setupChart("total_cases");
        return view;
    }

    private void setupPieChart(){
        pieChart.addPieSlice(new PieModel());
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
        ArrayList<Entry> entries = new ArrayList<>();
        switch (statistics) {
            case "total_cases":
                entries.add(new Entry(0, 1000));
                entries.add(new Entry(1, 2000));
                entries.add(new Entry(2, 1500));
                entries.add(new Entry(3, 3000));
                entries.add(new Entry(4, 2500));
                entries.add(new Entry(5, 4000));
                entries.add(new Entry(6, 3500));
                break;
            case "recovered":
                entries.add(new Entry(0, 800));
                entries.add(new Entry(1, 1800));
                entries.add(new Entry(2, 1200));
                entries.add(new Entry(3, 2500));
                entries.add(new Entry(4, 2000));
                entries.add(new Entry(5, 2800));
                entries.add(new Entry(6, 1500));
                break;
            case "deaths":
                entries.add(new Entry(0, 50));
                entries.add(new Entry(1, 100));
                entries.add(new Entry(2, 75));
                entries.add(new Entry(3, 150));
                entries.add(new Entry(4, 120));
                entries.add(new Entry(5, 200));
                entries.add(new Entry(6, 250));
                break;
            case "active_cases":
                entries.add(new Entry(0, 150));
                entries.add(new Entry(1, 100));
                entries.add(new Entry(2, 200));
                entries.add(new Entry(3, 350));
                entries.add(new Entry(4, 250));
                entries.add(new Entry(5, 400));
                entries.add(new Entry(6, 180));
                break;
        }

        LineDataSet dataSet = new LineDataSet(entries, "Statistics");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setDrawCircles(true);
        dataSet.setDrawValues(true);

        LineData lineData = new LineData(dataSet);
        lineChart.getLegend().setEnabled(false);
        lineChart.setData(lineData);
        lineChart.invalidate();
        lineChart.getDescription().setEnabled(false);
        lineChart.getXAxis().setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(false);

        final String[] daysOfWeek = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday","Sunday"};
        ValueFormatter xAxisFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return daysOfWeek[(int) value % daysOfWeek.length];
            }
        };
        lineChart.getXAxis().setValueFormatter(xAxisFormatter);
        lineChart.getXAxis().setGranularity(1f);
    }
}

