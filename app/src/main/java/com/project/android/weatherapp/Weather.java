package com.project.android.weatherapp;

public class Weather {

    private double mTemperature;

    private String mLocation;

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
