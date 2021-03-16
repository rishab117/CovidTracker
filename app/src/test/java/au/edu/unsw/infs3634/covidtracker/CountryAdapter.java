package au.edu.unsw.infs3634.covidtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> implements Filterable {
    private ArrayList<Country> mCountries;
    private ArrayList<Country> mCountriesFiltered;
    private RecyclerViewClickListener mListener;
    public static final int SORT_METHOD_NEW = 1;
    public static final int SORT_METHOD_TOTAL = 2;

    public CountryAdapter(ArrayList<Country> countries, RecyclerViewClickListener listener) {
        mCountries = countries;
        mListener = listener;
    }

    @Override
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    mCountriesFiltered = mCountries;
                }else{
                    ArrayList<Country> filteredList = new ArrayList<>();
                    for(Country country:mCountries){
                        if (country.getCountry().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(country);
                        }
                    }
                    mCountriesFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mCountriesFiltered;
                return filterResults;

            }


            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mCountriesFiltered = (ArrayList<Country>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface RecyclerViewClickListener {
        void onClick(View view, String id);
    }


    @NonNull
    @Override
    public CountryAdapter.CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view, parent, false);
        return new CountryViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryViewHolder holder, int position) {
        Country country = mCountries.get(position);
        holder.country.setText(country.getCountry());
        holder.totalCases.setText(String.valueOf(country.getTotalConfirmed()));
        holder.newCases.setText(String.valueOf(country.getNewConfirmed()));
        holder.itemView.setTag(country.getId());

    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView country, totalCases, newCases;
        private RecyclerViewClickListener listener;

        public CountryViewHolder(@NonNull View itemView, RecyclerViewClickListener listener){
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            country = itemView.findViewById(R.id.tvCountry);
            totalCases = itemView.findViewById(R.id.tvTotalCases);
            newCases = itemView.findViewById(R.id.tvNewCases);
        }
        @Override
        public void onClick(View v){mListener.onClick(v, (String)v.getTag());}
    }
    public void sort (final int sortMethod){
        if (mCountriesFiltered.size()>0){
            Collections.sort(mCountriesFiltered, new Comparator<Country>() {
                @Override
                public int compare(Country c1, Country c2) {
                    if(sortMethod == SORT_METHOD_NEW){
                        return c2.getNewConfirmed().compareTo(c1.getNewConfirmed());
                    }else if(sortMethod == SORT_METHOD_TOTAL){
                        return c2.getTotalConfirmed().compareTo(c1.getTotalConfirmed());
                    }
                    return c2.getTotalConfirmed().compareTo(c1.getTotalConfirmed());
                }
            });
        }
        notifyDataSetChanged();
    }
}
