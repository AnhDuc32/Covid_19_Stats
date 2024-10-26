package com.hfad.something;

import android.bluetooth.le.AdvertiseData;
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

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.Currency;

public class StatsFragment extends Fragment {

    private LineChart lineChart;
    private Spinner spinner;
    private PieChart pieChart;
    private ThemedToggleButtonGroup toggleButton;

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
        toggleButton = view.findViewById(R.id.toggleButton);

        setupToggleButton();
        setupPieChart();
        setupSpinner();
        setupChart("total_cases");
        return view;
    }

    private void setupToggleButton() {
        toggleButton.selectButton(R.id.btnGlobal);
        toggleButton.setOnSelectListener(new Function1<ThemedButton, Unit>() {
            @Override
            public Unit invoke(ThemedButton button) {
                int buttonId = button.getId();
                if (buttonId == R.id.btnGlobal) {
                    updateDataForGlobal();
                } else if (buttonId == R.id.btnVietnam) {
                    updateDataForVietnam();
                }
                return Unit.INSTANCE;
            }
        });
    }

    private void updateDataForGlobal(){
        pieChart.clearChart();
        pieChart.addPieSlice(new PieModel("Recovered",50,Color.parseColor("#00BFA6")));
        pieChart.addPieSlice(new PieModel("Active",30,Color.parseColor("#09B5FF")));
        pieChart.addPieSlice(new PieModel("Deaths",20,Color.parseColor("#FF575F")));
        pieChart.startAnimation();

        String selectedStatistic = spinner.getSelectedItem().toString().toLowerCase().replace(" ","_");
        setupChart(selectedStatistic);
    }

    private void updateDataForVietnam() {
        pieChart.clearChart();
        pieChart.addPieSlice(new PieModel("Recovered",100,Color.parseColor("#00BFA6")));
        pieChart.addPieSlice(new PieModel("Active",60,Color.parseColor("#09B5FF")));
        pieChart.addPieSlice(new PieModel("Deaths",40,Color.parseColor("#FF575F")));
        pieChart.startAnimation();

        String selectedStatistic = spinner.getSelectedItem().toString().toLowerCase().replace(" ","_");
        setupChart(selectedStatistic);
    }

    private void setupChartForVietnam(String statistics){
        ArrayList<Entry> entries = new ArrayList<>();
        switch (statistics){
            case "total_cases":
                entries.add(new Entry(0, 500));
                entries.add(new Entry(1, 1000));
                entries.add(new Entry(2, 750));
                entries.add(new Entry(3, 1500));
                entries.add(new Entry(4, 1250));
                entries.add(new Entry(5, 2000));
                entries.add(new Entry(6, 1750));
                break;
        }
        updateLineChart(entries);
    }

    private void updateLineChart(ArrayList<Entry> entries){
        LineDataSet dataSet = new LineDataSet(entries,"Statistics");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setDrawCircles(true);
        dataSet.setDrawValues(true);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    private void setupPieChart(){
        pieChart.addPieSlice(new PieModel("Recovered",50,Color.parseColor("#00BFA6")));
        pieChart.addPieSlice(new PieModel("Active",30,Color.parseColor("#09B5FF")));
        pieChart.addPieSlice(new PieModel("Deaths",20,Color.parseColor("#FF575F")));
        pieChart.setUsePieRotation(true);
        pieChart.setDrawValueInPie(true);
        pieChart.startAnimation();
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

        final String[] daysOfWeek = new String[]{"Mon", "Tues", "Wed", "Thur", "Fri", "Sat", "Sun"};
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

