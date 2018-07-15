package ru.dravn.androidlessons;


//** Вспомогательный класс для работы с API openweathermap.org и скачивания нужных данных

import retrofit2.Callback;
import ru.dravn.androidlessons.model.WeatherListRequest;
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

    public static void loadCycle(Callback<WeatherListRequest> callback, Double lat, Double lon, String qty) {
        App.getApi().loadByCoordCycle(lat, lon, qty)
                .enqueue(callback);
    }

    public static void loadForecast(Callback<WeatherListRequest> callback, Double lat, Double lon) {
        App.getApi().loadForecastByCoord(lat, lon)
                .enqueue(callback);
    }

    public static void loadForecast(Callback<WeatherListRequest> callback, String name) {
        App.getApi().loadForecastByCityName(name)
                .enqueue(callback);
    }
}