package com.hfad.something;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class AffectedCountry extends Fragment {
    public static List<CountryModel> countryModelList = new ArrayList<>();
    CountryModel countryModel;
    MyCustomAdapter myCustomAdapter;
    ListView listView;
    EditText edtSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_affected_country, container, false);

        edtSearch = view.findViewById(R.id.edtSearch);
        listView = view.findViewById(R.id.listView);

        // Manually add data for one country
        addCountryData();

        return view;
    }

    private void addCountryData() {
        countryModel = new CountryModel(
                R.drawable.vietnam, // Flag
                "Vietnam", // Country name
                "1,000,000", // Cases
                "500", // Today cases
                "20,000", // Deaths
                "10", // Today deaths
                "950,000", // Recovered
                "30,000", // Active cases
                "100" // Critical cases
        );

        // Add the object to the list
        countryModelList.add(countryModel);

        countryModel = new CountryModel(
                R.drawable.united_states, // Flag
                "USA", // Country name
                "1,000,000", // Cases
                "500", // Today cases
                "20,000", // Deaths
                "10", // Today deaths
                "950,000", // Recovered
                "30,000", // Active cases
                "100" // Critical cases
        );

        // Add the object to the list
        countryModelList.add(countryModel);

        countryModel = new CountryModel(
                R.drawable.united_kingdom, // Flag
                "UK", // Country name
                "1,000,000", // Cases
                "500", // Today cases
                "20,000", // Deaths
                "10", // Today deaths
                "950,000", // Recovered
                "30,000", // Active cases
                "100" // Critical cases
        );

        // Add the object to the list
        countryModelList.add(countryModel);

        // Set the adapter to the ListView
        myCustomAdapter = new MyCustomAdapter(getActivity(), countryModelList);
        listView.setAdapter(myCustomAdapter);
    }
}
