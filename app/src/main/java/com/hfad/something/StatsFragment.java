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
import android.widget.TextView;
import android.widget.Toast;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;

public class StatsFragment extends Fragment {

    private LineChart lineChart;
    private Spinner spinner;
    private PieChart pieChart;
    private ThemedToggleButtonGroup toggleButton;
    private TextView totalCases;
    private TextView activeCases;
    private TextView recovered;
    private TextView deaths;

    private String globalDailyData = "https://disease.sh/v3/covid-19/historical/all?lastdays=all";
    private String vietnamDailyData = "https://orange-crabs-scream.loca.lt/daily";
    private int selectedBtnId = R.id.btnGlobal;

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
        totalCases = view.findViewById(R.id.total_cases);
        activeCases = view.findViewById(R.id.active_cases);
        recovered = view.findViewById(R.id.recovered);
        deaths = view.findViewById(R.id.deaths);

        setupToggleButton();
        setupSpinner();
        fetchDailyData("total_cases", globalDailyData);
        return view;
    }

    private void setupToggleButton() {
        fetchData("https://disease.sh/v3/covid-19/all");
        toggleButton.selectButton(R.id.btnGlobal);
        toggleButton.setOnSelectListener(new Function1<ThemedButton, Unit>() {
            @Override
            public Unit invoke(ThemedButton button) {
                selectedBtnId = button.getId();
                if (selectedBtnId == R.id.btnGlobal) {
                    fetchData("https://disease.sh/v3/covid-19/all");
                    fetchDailyData("total_cases", globalDailyData);
                } else if (selectedBtnId == R.id.btnVietnam) {
                    fetchData("https://disease.sh/v3/covid-19/countries/vietnam");
                    fetchDailyData("total_cases", vietnamDailyData);
                }
                return Unit.INSTANCE;
            }
        });
    }

    private void fetchData(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String totalCasesStr = jsonObject.getString("cases");
                    String activeCasesStr = jsonObject.getString("active");
                    String recoveredStr = jsonObject.getString("recovered");
                    String deathStr = jsonObject.getString("deaths");

                    totalCases.setText(totalCasesStr);
                    activeCases.setText(activeCasesStr);
                    recovered.setText(recoveredStr);
                    deaths.setText(deathStr);

                    updatePieChart(Integer.parseInt(recoveredStr), Integer.parseInt(activeCasesStr), Integer.parseInt(deathStr));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void updatePieChart(int recovered, int active, int deaths) {
        pieChart.clearChart();
        pieChart.addPieSlice(new PieModel("Recovered", recovered, Color.parseColor("#00BFA6")));
        pieChart.addPieSlice(new PieModel("Active", active, Color.parseColor("#09B5FF")));
        pieChart.addPieSlice(new PieModel("Deaths", deaths, Color.parseColor("#FF575F")));
        pieChart.startAnimation();

        String selectedStatistic = spinner.getSelectedItem().toString().toLowerCase().replace(" ", "_");
        String url = (selectedBtnId == R.id.btnGlobal) ? globalDailyData : vietnamDailyData;
        fetchDailyData(selectedStatistic, url);
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
                String url = (selectedBtnId == R.id.btnGlobal) ? globalDailyData : vietnamDailyData;
                fetchDailyData(selectedStatistic, url);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void fetchDailyData(String statistics, String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject casesData = jsonObject.getJSONObject("cases");
                    JSONObject deathsData = jsonObject.getJSONObject("deaths");
                    JSONObject recoveredData = jsonObject.getJSONObject("recovered");

                    ArrayList<Entry> entries = new ArrayList<>();
                    JSONObject statData;
                    List<String> dateLabels = new ArrayList<>();

                    switch (statistics) {
                        case "total_cases":
                            statData = casesData;
                            break;
                        case "deaths":
                            statData = deathsData;
                            break;
                        case "recovered":
                            statData = recoveredData;
                            break;
                        case "active_cases":
                            statData = new JSONObject();
                            Iterator<String> dates = casesData.keys();
                            while (dates.hasNext()) {
                                String date = dates.next();
                                int totalCases = casesData.has(date) ? casesData.getInt(date) : 0;
                                int deaths = deathsData.has(date) ? deathsData.getInt(date) : 0;
                                int recovered = recoveredData.has(date) ? recoveredData.getInt(date) : 0;
                                int activeCases = totalCases - (deaths + recovered);
                                statData.put(date, activeCases);
                            }
                            break;
                        default:
                            statData = new JSONObject();
                    }

                    Iterator<String> keys = statData.keys();
                    int index = 0;

                    while (keys.hasNext()) {
                        String key = keys.next();
                        int value = statData.getInt(key);
                        dateLabels.add(key);
                        entries.add(new Entry(index++, value));
                    }

                    if (url.contains("disease")) {
                        updateChart(entries, statistics);
                    } else {
                        updateChartVietnam(entries, statistics, dateLabels);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }



    private void updateChart(ArrayList<Entry> entries, String statistics) {
        LineDataSet dataSet = new LineDataSet(entries, statistics);
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setDrawCircles(true);
        dataSet.setDrawValues(false);

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

        final String[] daysOfWeek = new String[]{"2020/1", "2021/8", "2023/3"};
        ValueFormatter xAxisFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return daysOfWeek[(int) value % daysOfWeek.length];
            }
        };
        lineChart.getXAxis().setValueFormatter(xAxisFormatter);
        lineChart.getXAxis().setGranularity(1f);
        lineChart.getXAxis().setLabelCount(3, true);
    }

    private void updateChartVietnam(ArrayList<Entry> entries, String statistics, List<String> dateLabels) {
        LineDataSet dataSet = new LineDataSet(entries, statistics);
        dataSet.setColor(Color.RED); // Use a different color for Vietnam data
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setDrawCircles(true);
        dataSet.setDrawValues(false); // Hide values above data points

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

        // Use dynamic dates for x-axis labels, specific to Vietnam
        final String[] daysOfWeekVietnam = dateLabels.stream().map(date -> date.substring(0, 7).replace("-", "/").replace("/0", "/")).toArray(String[]::new);
        ValueFormatter xAxisFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < daysOfWeekVietnam.length) {
                    return daysOfWeekVietnam[index];
                } else {
                    return "";
                }
            }
        };
        lineChart.getXAxis().setValueFormatter(xAxisFormatter);
        lineChart.getXAxis().setGranularity(1f);
        lineChart.getXAxis().setLabelCount(Math.min(2, daysOfWeekVietnam.length), true);
    }
}

