package au.edu.unsw.infs3634.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static final String INTENT_MESSAGE = "au.edu.unsw.infs3634.covidtracker.intent_message";
    private TextView tvCountry, tvCountryCode,tvSlug,tvTotalRecovered, tvnewDeaths, tvnewConfirmed, tvTotalConfirmed,tvnewRecovered, tvTotalDeaths;
            private Button search;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvCountry = findViewById(R.id.textCountry);
        tvCountryCode = findViewById(R.id.textCountryCode);
        tvnewConfirmed = findViewById(R.id.textnewConfirmed);
        tvnewDeaths = findViewById(R.id.textnewDeaths);
        tvTotalConfirmed = findViewById(R.id.textTotalConfirmed);
        tvnewRecovered = findViewById(R.id.textnewRecovered);
        tvTotalDeaths = findViewById(R.id.textTotalDeaths);
        tvSlug = findViewById(R.id.textSlug);
        tvTotalRecovered = findViewById(R.id.textTotalRecovered);


        Intent intent = getIntent();
        String id = intent.getStringExtra(INTENT_MESSAGE);
        Country country = Country.getCountry(id);

        if (country != null){
            setTitle(country.getCountryCode());
            tvCountry.setText(country.getCountry());
            tvnewConfirmed.setText(String.valueOf(country.getNewConfirmed()));
            tvnewDeaths.setText(String.valueOf(country.getNewDeaths()));
            tvTotalConfirmed.setText(String.valueOf(country.getTotalConfirmed()));
            tvnewRecovered.setText(String.valueOf(country.getNewRecovered()));
            tvTotalDeaths.setText(String.valueOf(country.getTotalDeaths()));
            tvTotalRecovered.setText(String.valueOf(country.getTotalRecovered()));

            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchCountry(country.getCountry());
                }
            });
        }


    }

    private void searchCountry(String country) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=covid" + country));
        startActivity(intent);
    }
}