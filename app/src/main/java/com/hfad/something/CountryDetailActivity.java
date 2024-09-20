package com.hfad.something;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class CountryDetailActivity extends AppCompatActivity {
    private int positionCountry;
    TextView cases, todayCases, deaths, todayDeaths, recovered, activeCases, criticalCases;
    TextView toolBarTitle;
    PieChart pieChart;
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position", 0);

        // Get id from xml
        toolBarTitle = findViewById(R.id.toolBarTitle);
        cases = findViewById(R.id.cases);
        todayCases = findViewById(R.id.todayCases);
        deaths = findViewById(R.id.deaths);
        todayDeaths = findViewById(R.id.todayDeaths);
        recovered = findViewById(R.id.recovered);
        activeCases = findViewById(R.id.activeCases);
        criticalCases = findViewById(R.id.criticalCases);

        // Set up ToolBar
        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        // Set data
        toolBarTitle.setText(AffectedCountry.countryModelList.get(positionCountry).getCountry() + " Statistics");
        cases.setText(AffectedCountry.countryModelList.get(positionCountry).getCases());
        todayCases.setText(AffectedCountry.countryModelList.get(positionCountry).getTodayCases());
        deaths.setText(AffectedCountry.countryModelList.get(positionCountry).getDeaths());
        todayDeaths.setText(AffectedCountry.countryModelList.get(positionCountry).getTodayDeaths());
        recovered.setText(AffectedCountry.countryModelList.get(positionCountry).getRecovered());
        activeCases.setText(AffectedCountry.countryModelList.get(positionCountry).getActive());
        criticalCases.setText(AffectedCountry.countryModelList.get(positionCountry).getCritical());

        // Set data to PieChart
        pieChart = findViewById(R.id.pieChart);

        float floatCases = Float.parseFloat(AffectedCountry.countryModelList.get(positionCountry).getCases().replace(",", ""));
        float floatRecovered = Float.parseFloat(AffectedCountry.countryModelList.get(positionCountry).getRecovered().replace(",", ""));
        float floatDeaths = Float.parseFloat(AffectedCountry.countryModelList.get(positionCountry).getDeaths().replace(",", ""));
        float floatActive = Float.parseFloat(AffectedCountry.countryModelList.get(positionCountry).getActive().replace(",", ""));

        pieChart.addPieSlice(new PieModel("Recovered", floatRecovered, Color.parseColor("#00BFA6")));
        pieChart.addPieSlice(new PieModel("Active", floatRecovered, Color.parseColor("#09B5FF")));
        pieChart.addPieSlice(new PieModel("Deaths", floatDeaths, Color.parseColor("#FF575F")));

        pieChart.startAnimation();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
