package com.hfad.something;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MyCustomAdapter extends ArrayAdapter<CountryModel> {
    private Context context;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListFiltered;

    public MyCustomAdapter(Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.list_custom_item, countryModelList);
        this.context = context;
        this.countryModelList = countryModelList;
        this.countryModelListFiltered = new ArrayList<>(countryModelList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item, parent, false);
        }
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView imageView = view.findViewById(R.id.imageFlag);

        CountryModel countryModel = getItem(position);
        if (countryModel != null) {
            tvCountryName.setText(countryModel.getCountry());
            Glide.with(context).load(countryModel.getFlag()).circleCrop().into(imageView);
        }

        return view;
    }

    @Override
    public int getCount() {
        return countryModelListFiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if (charSequence == null || charSequence.length() == 0) {
                    filterResults.count = countryModelList.size();
                    filterResults.values = countryModelList;
                } else {
                    List<CountryModel> resultsModel = new ArrayList<>();
                    String searchStr = charSequence.toString().toLowerCase();

                    for (CountryModel itemModel : countryModelList) {
                        if (itemModel.getCountry().toLowerCase().contains(searchStr)) {
                            resultsModel.add(itemModel);
                        }
                    }
                    filterResults.count = resultsModel.size();
                    filterResults.values = resultsModel;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                countryModelListFiltered = (List<CountryModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
