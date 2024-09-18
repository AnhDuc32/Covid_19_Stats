package com.hfad.something;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MyCustomAdapter extends ArrayAdapter<CountryModel> {
    private Context context;
    private List<CountryModel> countryModelList;

    public MyCustomAdapter(Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.list_custom_item, countryModelList);

        this.context= context;
        this.countryModelList = countryModelList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item, null,true);
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView imageView = view.findViewById(R.id.imageFlag);

        tvCountryName.setText(countryModelList.get(position).getCountry());
        Glide.with(context).load(countryModelList.get(position).getFlag()).circleCrop().into(imageView);

        return view;
    }
}
