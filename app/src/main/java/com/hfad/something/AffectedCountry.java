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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;

public class AffectedCountry extends Fragment {
    public static List<CountryModel> countryModelList = new ArrayList<>();

    CountryModel countryModel;
    MyCustomAdapter myCustomAdapter;
    ListView listView;
    EditText editSearch;
    ThemedButton btnVietnam;
    ThemedButton btnGlobal;
    ThemedToggleButtonGroup toggleButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_affected_country, container, false);

        editSearch = view.findViewById(R.id.editSearch);
        listView = view.findViewById(R.id.listView);
        toggleButton = view.findViewById(R.id.toggleButton);
        btnVietnam = view.findViewById(R.id.btnVietnam);
        btnGlobal = view.findViewById(R.id.btnGlobal);

        setupToggleButton();

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

        editSearch.addTextChangedListener(new TextWatcher() {
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

    private void setupToggleButton() {
        fetchCountryData("https://disease.sh/v3/covid-19/countries");
        toggleButton.selectButton(R.id.btnGlobal);
        toggleButton.setOnSelectListener(new Function1<ThemedButton, Unit>() {
            @Override
            public Unit invoke(ThemedButton button) {
                int buttonId = button.getId();
                if (buttonId == R.id.btnGlobal) {
                    fetchCountryData("https://disease.sh/v3/covid-19/countries");
                    editSearch.setHint("Search Country");
                } else if (buttonId == R.id.btnVietnam) {
                    fetchCityData("https://shy-baboons-try.loca.lt/data");
                    editSearch.setHint("Search City");
                }
                return Unit.INSTANCE;
            }
        });
    }

    private void fetchCountryData(String url) {
        countryModelList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String countryName = jsonObject.getString("country");
                                String cases = jsonObject.getString("cases");
                                String todayCases = jsonObject.getString("todayCases");
                                String deaths = jsonObject.getString("deaths");
                                String todayDeaths = jsonObject.getString("todayDeaths");
                                String recovered = jsonObject.getString("recovered");
                                String active = jsonObject.getString("active");
                                String critical = jsonObject.getString("critical");
                                JSONObject object = jsonObject.getJSONObject("countryInfo");
                                String flag = object.getString("flag");

                                countryModel = new CountryModel(flag, countryName, cases, todayCases, deaths, todayDeaths, recovered, active, critical);
                                countryModelList.add(countryModel);
                            }

                            myCustomAdapter = new MyCustomAdapter(getActivity(), countryModelList);
                            listView.setAdapter(myCustomAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void fetchCityData(String url) {
        countryModelList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String cityName = jsonObject.getString("name");

                                JSONObject object = jsonObject.getJSONObject("statistics");

                                String cases = object.getString("total_cases");
                                String todayCases = object.getString("new_cases");
                                String deaths = object.getString(   "total_deaths");
                                String todayDeaths = object.getString("new_deaths");
                                String recovered = object.getString("total_recovered");
                                String active = object.getString("active_cases");
                                String critical = "0";

                                String flag = jsonObject.getString("logo_url");

                                countryModel = new CountryModel(flag, cityName, cases, todayCases, deaths, todayDeaths, recovered, active, critical);
                                countryModelList.add(countryModel);
                            }

                            myCustomAdapter = new MyCustomAdapter(getActivity(), countryModelList);
                            listView.setAdapter(myCustomAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}