package com.hfad.something;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CountryDetailActivity extends AppCompatActivity {
    private int positionCountry;
    TextView country, cases, todayCases, deaths, todayDeaths, recovered, activeCases, criticalCases;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position", 0);

        // Get id from xml
        toolbar = findViewById(R.id.toolBar);
        country = findViewById(R.id.country);
        cases = findViewById(R.id.cases);
        todayCases = findViewById(R.id.todayCases);
        deaths = findViewById(R.id.deaths);
        todayDeaths = findViewById(R.id.todayDeaths);
        recovered = findViewById(R.id.recovered);
        activeCases = findViewById(R.id.activeCases);
        criticalCases = findViewById(R.id.criticalCases);

        // Set data
        toolbar.setTitle("Details of " + AffectedCountry.countryModelList.get(positionCountry).getCountry());
        country.setText(AffectedCountry.countryModelList.get(positionCountry).getCountry());
        cases.setText(AffectedCountry.countryModelList.get(positionCountry).getCases());
        todayCases.setText(AffectedCountry.countryModelList.get(positionCountry).getTodayCases());
        deaths.setText(AffectedCountry.countryModelList.get(positionCountry).getDeaths());
        todayDeaths.setText(AffectedCountry.countryModelList.get(positionCountry).getTodayDeaths());
        recovered.setText(AffectedCountry.countryModelList.get(positionCountry).getRecovered());
        activeCases.setText(AffectedCountry.countryModelList.get(positionCountry).getActive());
        criticalCases.setText(AffectedCountry.countryModelList.get(positionCountry).getCritical());
    }
}
