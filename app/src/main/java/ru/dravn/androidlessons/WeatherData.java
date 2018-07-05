package ru.dravn.androidlessons;


//** Вспомогательный класс для работы с API openweathermap.org и скачивания нужных данных

import retrofit2.Callback;
import ru.dravn.androidlessons.model.WeatherRequest;

public  class WeatherData {

    public static void loadByCoord(Callback<WeatherRequest> callback, String lat,  String lon) {
        App.getApi().loadByCoord(lat, lon)
                .enqueue(callback);
    }

    public static void loadByCityName(Callback<WeatherRequest> callback, String city) {
        App.getApi().loadByCityName(city)
                .enqueue(callback);
    }
}