package com.project.android.weatherapp;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import java.io.IOException;
import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    public static final String LOG_TAG = WeatherActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private WeatherAsyncTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ArrayList<Weather> emptyList = new ArrayList<>();

        RecyclerView weatherListView = findViewById(R.id.recycler_view);
        weatherListView.setLayoutManager(new LinearLayoutManager(WeatherActivity.this));
        weatherListView.setAdapter(new WeatherAdapter(emptyList));

        try {
            task = new WeatherAsyncTask(this, weatherListView,
                    new UrlParser().getUrl(getApplicationContext()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (task != null) {
                task.execute();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        initSearchView(menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void initSearchView(Menu menu) {
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        searchView.setQueryHint("Search a location...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });

    }

}
