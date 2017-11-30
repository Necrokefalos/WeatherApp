package com.project.android.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    public static final String LOG_TAG = WeatherActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);

        ArrayList<Weather> emptyList = new ArrayList<>();
        String key = null;
        try {
            key = new UrlParser().getUrl(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView weatherListView = findViewById(R.id.recycler_view);
        weatherListView.setLayoutManager(new LinearLayoutManager(WeatherActivity.this));
        weatherListView.setAdapter(new WeatherAdapter(emptyList));

        WeatherAsyncTask task = new WeatherAsyncTask(key, weatherListView);
        task.execute();
    }
}