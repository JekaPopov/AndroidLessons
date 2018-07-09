package ru.dravn.androidlessons;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.SettingInjectorService;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.dravn.androidlessons.interfaces.OpenWeather;


public class App extends Application {

    private static OpenWeather api;


    private static SharedPreferences preferences;
    private static final String APP_PREFERENCES = "mysettings";
    private static final String CITIESNAME = "city";
    private static SharedPreferences.Editor editor;


    @Override
    public void onCreate() {
        initRetrofit();
        initSharedPref();
        super.onCreate();
    }

    private void initRetrofit(){
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
//Базовая часть адреса
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//Создаем объект, при помощи которого будем выполнять запросы
        api = retrofit.create(OpenWeather.class);
    }


    public static OpenWeather getApi() {
        return api;
    }


    private void initSharedPref()
    {
        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static HashSet getCitiesName()
    {
        return (HashSet) preferences.getStringSet(CITIESNAME, new HashSet<String>());
    }

    public static void setCitiesName(HashSet citySet)
    {
        //без вставки remove ничего не сохранялось не понимаю по чему
        editor.remove(CITIESNAME).putStringSet(CITIESNAME, citySet);
        editor.apply();
    }

}
