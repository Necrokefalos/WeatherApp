package com.project.android.weatherapp;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UrlParser {

    public String getUrlFav(Context context) throws IOException {
        String url = "";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("key"),
                            "UTF-8"));

            // do reading, usually loop until end of file reading
            url = String.format("https://api.openweathermap.org/data/2.5/group?id=524901,703448,2643743,255683,256639,734077,264371,255274&units=metric&appid=%s",
                    reader.readLine());
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return url;
    }

    public String getUrlForecast(Context context) throws IOException {
        String url = "";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("key"),
                            "UTF-8"));
            url = String.format("http://api.openweathermap.org/data/2.5/forecast?q=%s&units=metric&appid=%s",
                    reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return url;
    }
}

