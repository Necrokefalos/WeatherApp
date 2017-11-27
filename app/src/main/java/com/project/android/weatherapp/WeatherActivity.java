package com.project.android.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    public static final String LOG_TAG = WeatherActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);

        // Create a fake list of weather locations.
        ArrayList<Weather> weathers = QueryUtils.extractWeathers();

        // Find a reference to the {@link ListView} in the layout
        ListView weatherListView = (ListView) findViewById(R.id.list);

        // Create a new {@link WeatherAdapter} of weathers
        WeatherAdapter adapter = new WeatherAdapter(this, weathers);
        // Set the adapter on the {@link ListView} so the list can be populated in the user interface
        weatherListView.setAdapter(adapter);
    }




}