package ru.dravn.androidlessons.utils;


import android.content.Context;

import ru.dravn.androidlessons.App;
import ru.dravn.androidlessons.R;

public class Const {
    public static final String TEMP = "temp";
    public static final String HUMIDITY = "humidity";
    public static final String PRESSURE = "pressure";
    public static final String IMAGE = "image";

    public static final String CITY = "name";
    public static final String CURRTEMP = "currTemp";
    public static final String MINTEMP = "temp_min";
    public static final String MAXTEMP = "temp_max";
    public static final String WEATHER = "weather";
    public static final String MESSAGE = "message";

    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";


    public static String format(String val, String valName, Context context) {
        String format;
        switch (valName) {
            case Const.TEMP:
                if (Integer.valueOf(val) > 0)
                    format = context.getResources().getString(R.string.temp_format_plus);
                else
                    format = context.getResources().getString(R.string.temp_format);
                return String.format(format, val);
            case Const.HUMIDITY:
                format = context.getResources().getString(R.string.humidity_format);
                return String.format(format, val);
            case Const.PRESSURE:
                format = context.getResources().getString(R.string.pressure_format);
                return String.format(format, val);
            case Const.IMAGE:
                format = context.getResources().getString(R.string.image_url);
                return String.format(format, val);
            default:
                return "";
        }
    }

}
