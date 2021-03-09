package au.edu.unsw.infs3634.covidtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private ArrayList<Country> mCountries;
    private RecyclerViewClickListener mListener;

    public CountryAdapter(ArrayList<Country> countries, RecyclerViewClickListener listener) {
        mCountries = countries;
        mListener = listener;
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
}
