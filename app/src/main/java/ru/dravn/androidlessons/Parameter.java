package ru.dravn.androidlessons;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class Parameter extends AppCompatActivity {

    public static final String TEMP = "temp";
    public static final String HUMIDITY = "hum";
    public static final String PRESSURE = "press";

    public static final String CITY = "city";
    public static final String CURRTEMP = "currTemp";
    public static final String MINTEMP = "minTemp";
    public static final String MAXTEMP = "maxTemp";
    public static final String WEATHER = "weather";

    public static final String MESSAGE = "map";

    public String format(int val, String valName) {
        String format;
        switch (valName) {
            case TEMP:
                format = getResources().getString(R.string.temp_format);
                return String.format(format, val);
            case HUMIDITY:
                format = getResources().getString(R.string.humidity_format);
                return String.format(format, val);
            case PRESSURE:
                format = getResources().getString(R.string.pressure_format);
                return String.format(format, val);
            default:
                return "";
        }
    }


    protected void showMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected Drawable getImageRes(String name) {
        if (name.equals(getResources().getString(R.string.sunny)))
            return getResources().getDrawable(R.drawable.sunny);
        else if (name.equals(getResources().getString(R.string.hot)))
            return getResources().getDrawable(R.drawable.hot);
        else if (name.equals(getResources().getString(R.string.partly_cloudy)))
            return getResources().getDrawable(R.drawable.partly_cloudy);
        else if (name.equals(getResources().getString(R.string.rain)))
            return getResources().getDrawable(R.drawable.rain);
        else if (name.equals(getResources().getString(R.string.storm)))
            return getResources().getDrawable(R.drawable.storm);
        else if (name.equals(getResources().getString(R.string.cloudy)))
            return getResources().getDrawable(R.drawable.cloudy);
        else if (name.equals(getResources().getString(R.string.snow)))
            return getResources().getDrawable(R.drawable.snow);

        return getResources().getDrawable(R.drawable.sunny);
    }

}
