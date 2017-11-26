package com.project.android.weatherapp;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving weather data from OWM.
 */
public final class QueryUtils {

    /** Sample JSON response for a OWM query */
    private static final String SAMPLE_JSON_RESPONSE = "{\"cnt\":7,\"list\":[{\"coord\":{\"lon\":37.62,\"lat\":55.75},\"sys\":{\"type\":1,\"id\":7323,\"message\":0.0031,\"country\":\"RU\",\"sunrise\":1511587467,\"sunset\":1511615287},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"main\":{\"temp\":-5.66,\"pressure\":1022,\"humidity\":92,\"temp_min\":-6,\"temp_max\":-5},\"visibility\":10000,\"wind\":{\"speed\":3,\"deg\":230},\"clouds\":{\"all\":90},\"dt\":1511569989,\"id\":524901,\"name\":\"Moscow\"},{\"coord\":{\"lon\":30.52,\"lat\":50.43},\"sys\":{\"type\":1,\"id\":7358,\"message\":0.0019,\"country\":\"UA\",\"sunrise\":1511587679,\"sunset\":1511618488},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50n\"}],\"main\":{\"temp\":-0.34,\"pressure\":1022,\"humidity\":97,\"temp_min\":-1,\"temp_max\":0},\"visibility\":4300,\"wind\":{\"speed\":3,\"deg\":200},\"clouds\":{\"all\":90},\"dt\":1511569989,\"id\":703448,\"name\":\"Kiev\"},{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"sys\":{\"type\":1,\"id\":5168,\"message\":0.0121,\"country\":\"GB\",\"sunrise\":1511595311,\"sunset\":1511625570},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50n\"}],\"main\":{\"temp\":-1.02,\"pressure\":1012,\"humidity\":100,\"temp_min\":-4,\"temp_max\":1},\"visibility\":7000,\"wind\":{\"speed\":0.5,\"deg\":260},\"clouds\":{\"all\":40},\"dt\":1511569989,\"id\":2643743,\"name\":\"London\"},{\"coord\":{\"lon\":21.73,\"lat\":38.24},\"sys\":{\"type\":1,\"id\":5694,\"message\":0.0023,\"country\":\"GR\",\"sunrise\":1511587495,\"sunset\":1511622900},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"main\":{\"temp\":8.6,\"pressure\":1021,\"humidity\":100,\"temp_min\":7,\"temp_max\":10},\"visibility\":10000,\"wind\":{\"speed\":1.07,\"deg\":14.0025},\"clouds\":{\"all\":20},\"dt\":1511569989,\"id\":255683,\"name\":\"Patra\"},{\"coord\":{\"lon\":21.83,\"lat\":38.39},\"sys\":{\"type\":1,\"id\":5694,\"message\":0.0052,\"country\":\"GR\",\"sunrise\":1511587493,\"sunset\":1511622853},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"main\":{\"temp\":7,\"pressure\":1021,\"humidity\":100,\"temp_min\":7,\"temp_max\":7},\"visibility\":10000,\"wind\":{\"speed\":1.07,\"deg\":14.0025},\"clouds\":{\"all\":20},\"dt\":1511569989,\"id\":256639,\"name\":\"Nafpaktos\"},{\"coord\":{\"lon\":22.94,\"lat\":40.64},\"sys\":{\"type\":1,\"id\":5704,\"message\":0.0245,\"country\":\"GR\",\"sunrise\":1511587578,\"sunset\":1511622234},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"main\":{\"temp\":8,\"pressure\":1022,\"humidity\":87,\"temp_min\":8,\"temp_max\":8},\"visibility\":10000,\"wind\":{\"speed\":1.5},\"clouds\":{\"all\":40},\"dt\":1511569989,\"id\":734077,\"name\":\"Thessaloniki\"},{\"coord\":{\"lon\":37.62,\"lat\":55.75},\"sys\":{\"type\":1,\"id\":7323,\"message\":0.0031,\"country\":\"RU\",\"sunrise\":1511587467,\"sunset\":1511615287},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"main\":{\"temp\":-5.66,\"pressure\":1022,\"humidity\":92,\"temp_min\":-6,\"temp_max\":-5},\"visibility\":10000,\"wind\":{\"speed\":3,\"deg\":230},\"clouds\":{\"all\":90},\"dt\":1511569989,\"id\":524901,\"name\":\"Moscow\"}]}";

    /**
     * Private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Weather} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Weather> extractWeathers() {

        // Create an empty ArrayList that we can start adding weathers to
        ArrayList<Weather> weathers = new ArrayList<>();

        try {

            // Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Weather objects with the corresponding data.

            JSONObject baseJsonReport = new JSONObject(SAMPLE_JSON_RESPONSE);
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

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash.
            Log.e("QueryUtils", "Problem parsing the weather JSON results", e);
        }

        // Return the list of weathers
        return weathers;
    }

}
