package com.project.android.weatherapp;

public class Weather {
    //Temperature of the weather
    private double mTemperature;
    //Location of the weather
    private String mLocation;
    //Time of the weather
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
