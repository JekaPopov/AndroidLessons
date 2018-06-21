package ru.dravn.androidlessons;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;


public class BaseFragment extends Fragment {

    public static final String TEMP = "temp";
    public static final String HUMIDITY = "humidity";
    public static final String PRESSURE = "pressure";

    public static final String CITY = "name";
    public static final String CURRTEMP = "currTemp";
    public static final String MINTEMP = "temp_min";
    public static final String MAXTEMP = "temp_max";
    public static final String WEATHER = "weather";
    public static final String MESSAGE = "message";

    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    public String format(String val, String valName) {
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


    protected void showMessage(String mMessage) {
        Toast.makeText(getActivity(), mMessage, Toast.LENGTH_LONG).show();
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
