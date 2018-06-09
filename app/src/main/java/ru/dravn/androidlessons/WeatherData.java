package ru.dravn.androidlessons;


//** Вспомогательный класс для работы с API openweathermap.org и скачивания нужных данных

import android.content.Context;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherData {

    private static final String APIbyLatLon = "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric";
    private static final String APIbyCity = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";

    private static URL url;

    public static JSONObject getDate(Context context, String city)
    {
        try {
            url = new URL (String.format(APIbyCity, city));
           return getJSONData(context);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getDate(Context context, String lat, String lon)
    {
        try {
            url = new URL (String.format(APIbyLatLon, lat, lon));
            return getJSONData(context);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static JSONObject getJSONData (Context context){
        try{
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("x-api-key", context.getString(R.string.openWeatherMapApiKey));

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = bufferedReader.readLine()) != null)
                json.append(tmp).append("\n");
            bufferedReader.close();

            JSONObject data = new JSONObject(json.toString());

            if (data.getInt("cod") != 200)  return null;

            return data;
        } catch (Exception e) {
            return null;
        }
    }
}