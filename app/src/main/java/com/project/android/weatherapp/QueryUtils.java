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
                // Extract the key values
                JSONObject currentWeather = listArray.getJSONObject(i);
                //#1
                JSONObject coord = currentWeather.getJSONObject("coord");
                double lon = coord.getDouble("lon");
                double lat = coord.getDouble("lat");

                //#2
                JSONObject sys = currentWeather.getJSONObject("sys");
                int type = sys.getInt("type");
                int id = sys.getInt("id");
                double message = sys.getDouble("message");
                String country = sys.getString("country");
                long sunrise = sys.getLong("sunrise");
                long sunset = sys.getLong("sunset");

                //#3
                JSONArray weath = currentWeather.getJSONArray("weather");
                JSONObject weatherInfo = weath.getJSONObject(0);
                int iconId = weatherInfo.getInt("id");
                String mainDesc = weatherInfo.getString("main");
                String description = weatherInfo.getString("description");
                String icon = weatherInfo.getString("icon");

                //#4
                JSONObject main = currentWeather.getJSONObject("main");
                double temperature = main.getDouble("temp");
                int pressure = main.getInt("pressure");
                int humidity = main.getInt("humidity");
                double temp_min = main.getDouble("temp_min");
                double temp_max = main.getDouble("temp_max");

                //#5
                int visibility = currentWeather.getInt("visibility");

                //#6
                JSONObject wind = currentWeather.getJSONObject("wind");
                double speed = wind.getDouble("speed");
                //int deg = wind.getInt("deg"); TODO: Fix if no value for deg.

                //#7
                long time = currentWeather.getLong("dt");

                //#8
                int woeid = currentWeather.getInt("id");

                //#9
                String location = currentWeather.getString("name");

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
