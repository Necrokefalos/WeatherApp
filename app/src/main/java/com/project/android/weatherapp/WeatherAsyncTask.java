package com.project.android.weatherapp;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class WeatherAsyncTask extends AsyncTask<URL, Void, ArrayList<Weather>> {

    private final RecyclerView weatherListView;
    private final String apiUrl;

    WeatherAsyncTask(String apiUrl, RecyclerView wlv) {
        this.weatherListView = wlv;
        this.apiUrl = apiUrl;
    }

    protected ArrayList<Weather> doInBackground(URL... urls) {
        URL url = HttpHandler.createUrl(apiUrl);

        String jsonResponse = "";
        try {
            jsonResponse = HttpHandler.makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(WeatherActivity.LOG_TAG, "Problem making the HTTP request.", e);
        }

        return QueryUtils.extractWeathers(jsonResponse);
    }

    protected void onPostExecute(ArrayList<Weather> weather) {
        if (weather == null) {
            return;
        }
        ((WeatherAdapter)weatherListView.getAdapter()).setNewData(weather);
    }
}
