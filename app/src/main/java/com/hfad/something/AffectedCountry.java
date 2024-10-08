package com.hfad.something;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CountryModel selectedCountry = (CountryModel) myCustomAdapter.getItem(i);
                int position = AffectedCountry.countryModelList.indexOf(selectedCountry);
                Intent intent = new Intent(requireActivity(), CountryDetailActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                myCustomAdapter.getFilter().filter(charSequence);
                myCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    private void addCountryData() {
        countryModelList.clear();

        countryModel = new CountryModel(
                R.drawable.vietnam, // Flag
                "Vietnam", // Country name
                "1,000,000", // Cases
                "500", // Today cases
                "200,000", // Deaths
                "10", // Today deaths
                "600,000", // Recovered
                "30,000", // Active cases
                "10,000" // Critical cases
        );

        // Add the object to the list
        countryModelList.add(countryModel);

        countryModel = new CountryModel(
                R.drawable.united_states, // Flag
                "USA", // Country name
                "2,000,000", // Cases
                "1,000", // Today cases
                "500,000", // Deaths
                "55", // Today deaths
                "1,300,000", // Recovered
                "75,000", // Active cases
                "25,000" // Critical cases
        );

        // Add the object to the list
        countryModelList.add(countryModel);

        countryModel = new CountryModel(
                R.drawable.united_kingdom, // Flag
                "UK", // Country name
                "1,400,000", // Cases
                "700", // Today cases
                "350,000", // Deaths
                "40", // Today deaths
                "850,000", // Recovered
                "55,000", // Active cases
                "17,000" // Critical cases
        );

        // Add the object to the list
        countryModelList.add(countryModel);

        // Set the adapter to the ListView
        myCustomAdapter = new MyCustomAdapter(getActivity(), countryModelList);
        listView.setAdapter(myCustomAdapter);
    }
}
