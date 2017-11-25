package com.example.android.weatherapp;

/**
 * Created by georg on 11/24/2017.
 */

public class Weather {

    private double mTemperature;

    private String mLocation;

    /** Time of the weather */
    private long mTimeInMilliseconds;

    public Weather(double temperature, String location, long timeInMilliseconds) {
        this.mTemperature = temperature;
        this.mLocation = location;
        this.mTimeInMilliseconds = timeInMilliseconds;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }


}
