package com.project.android.weatherapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving weather data from OWM.
 */
public final class QueryUtils {

    private QueryUtils() {
    }

    /**
     * Returned list of objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Weather> extractWeathers(String weatherJSON) {

        if (TextUtils.isEmpty(weatherJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding weathers to
        ArrayList<Weather> weathers = new ArrayList<>();

        try {
            // Parse the response given by the jsonResponse.WeatherAsyncTask string and
            // build up a list of Weather objects with the corresponding data.

            JSONObject baseJsonReport = new JSONObject(weatherJSON);
            JSONArray listArray = baseJsonReport.getJSONArray("list");

            for ( int i = 0; i < listArray.length(); i++) {
                JSONObject currentWeather = listArray.getJSONObject(i);

                /*JSONArray weather = currentWeather.getJSONArray("weather");
                *JSONObject weatherInfo = weather.getJSONObject(0);
                *String description = weatherInfo.getString("description");
                */

                JSONObject main = currentWeather.getJSONObject("main");
                // Extract the value for the key called "temp"
                Double temperature = main.getDouble("temp");

                // Extract the value for the key called "name"
                String location = currentWeather.getString("name");

                // Extract the value for the key called "dt"
                long time = currentWeather.getLong("dt");

                Weather weather = new Weather(temperature, location, time);
                weathers.add(weather);
            }
            return weathers;
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the weather JSON results", e);
        }
        return null;
    }
}
