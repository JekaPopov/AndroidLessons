package ru.dravn.androidlessons;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.dravn.androidlessons.interfaces.OpenWeather;
import ru.dravn.androidlessons.utils.Const;

public class App extends Application {

    private static OpenWeather api;


    @Override
    public void onCreate() {
        initRetorfit();
        super.onCreate();
    }

    private void initRetorfit(){
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




}
