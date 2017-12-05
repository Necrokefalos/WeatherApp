package com.project.android.weatherapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class WeatherAsyncTask extends AsyncTask<URL, Void, ArrayList<Weather>> {

    private final Activity activity;
    private final RecyclerView weatherListView;
    private final String apiUrl;
    private final HttpHandler handler;

    private byte cnt;


    WeatherAsyncTask(Activity activity, RecyclerView wlv, String apiUrl) {
        this.activity = activity;
        this.weatherListView = wlv;
        this.apiUrl = apiUrl;
        this.handler = new HttpHandler(activity);

        this.cnt = 0;
    }

    protected ArrayList<Weather> doInBackground(URL... urls) {
        URL url = handler.createUrl(apiUrl);

        String jsonResponse = "";
        try {
            jsonResponse = handler.makeHttpRequest(url);
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
        System.out.println("COUNT: " + cnt);
        cnt++;
    }
}
