package com.project.android.weatherapp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WeatherAdapter extends ArrayAdapter<Weather> {

    public WeatherAdapter(Context context, List<Weather> weathers) {
        super(context, 0 , weathers);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted temperature string showing 1 decimal place (i.e. "3.2")
     * from a decimal temperature value.
     */
    private String formatTemperature(double magnitude) {
        DecimalFormat temperatureFormat = new DecimalFormat("0.0");
        return temperatureFormat.format(magnitude);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.weather_list_item, parent, false);
        }

        Weather currentWeather = getItem(position);

        // TODO: Create switch case for displaying images from description (imageView.setImageResponse(R.id.sunJPG);)

        //Find the TextView with view ID temperature
        TextView temperatureView = (TextView) listItemView.findViewById(R.id.temperature);
        // Format the temperature to show 1 decimal place
        String formattedTemperature = formatTemperature(currentWeather.getTemperature());
        // Display the temperature of the current weather in that TextView
        temperatureView.setText(formattedTemperature);


        TextView locationView = (TextView) listItemView.findViewById(R.id.location);
        locationView.setText(currentWeather.getLocation());

        // Create a new Date object from the time in milliseconds of the weather
        Date dateObject = new Date(currentWeather.getTimeInMilliseconds()*1000);

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current weather in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current weather in that TextView
        timeView.setText(formattedTime);

        return listItemView;

    }


}
