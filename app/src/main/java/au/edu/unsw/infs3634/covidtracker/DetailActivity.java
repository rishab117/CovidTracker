package au.edu.unsw.infs3634.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {
    public static final String INTENT_MESSAGE = "au.edu.unsw.infs3634.covidtracker.intent_message";

    private TextView mCountry;
    private TextView mNewCases;
    private TextView mTotalCases;
    private TextView mNewDeaths;
    private TextView mTotalDeaths;
    private TextView mNewRecovered;
    private TextView mTotalRecovered;
    private Button mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mCountry = findViewById(R.id.tvCountry);
        mNewCases = findViewById(R.id.tvNewCases);
        mTotalCases = findViewById(R.id.tvTotalCases);
        mNewDeaths = findViewById(R.id.tvNewDeaths);
        mTotalDeaths = findViewById(R.id.tvTotalDeaths);
        mNewRecovered = findViewById(R.id.tvNewRecovered);
        mTotalRecovered = findViewById(R.id.tvTotalRecovered);
        mSearch = findViewById(R.id.btSearch);

        Intent intent = getIntent();
        String id = intent.getStringExtra(INTENT_MESSAGE);

        Country country = Country.getCountry(id);
        if(country != null) {
            setTitle(country.getCountryCode());
            mCountry.setText(country.getCountry());
            mNewCases.setText(String.valueOf(country.getNewConfirmed()));
            mTotalCases.setText(String.valueOf(country.getTotalConfirmed()));
            mNewDeaths.setText(String.valueOf(country.getNewDeaths()));
            mTotalDeaths.setText(String.valueOf(country.getTotalDeaths()));
            mNewRecovered.setText(String.valueOf(country.getNewRecovered()));
            mTotalRecovered.setText(String.valueOf(country.getTotalRecovered()));
            mSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchCountry(country.getCountry());
                }
            });
        }
    }

    private void searchCountry(String country) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=covid " + country));
        startActivity(intent);
    }
}