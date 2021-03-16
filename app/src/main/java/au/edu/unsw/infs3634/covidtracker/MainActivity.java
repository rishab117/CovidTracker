package au.edu.unsw.infs3634.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CountryAdapter mAdapter;
    private MenuInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);
        CountryAdapter.RecyclerViewClickListener listener = new CountryAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, String id) {
                launchDetailActivity(id);
            }
        };
        mAdapter = new CountryAdapter(Country.getCountries(), listener);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void launchDetailActivity(String message) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.INTENT_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public boolean OnCreateOptionsMenu (Menu menu){
        MenuInflater inflate = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListner(new SearchView.OnQueryTextListener()){
            @Override
                    public boolean onQueryTextSubmit(String s){
                mAdapter.getFilter().filter(s);
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@nonNull MenuItem item){
        switch (item.getItemId()){
            case.R.id.sort_new:
            mAdapter.sort(CountryAdapter.SORT_METHOD_NEW);
            return true;
            case.R.id.sort_total:
            mAdapter.sort(CountryAdapter.SORT_METHOD_TOTAL);
            default:
                return super.onOptionsItemSelected();
        }

    }
}