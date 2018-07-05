package ru.dravn.androidlessons.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.dravn.androidlessons.model.WeatherRequest;

public interface OpenWeather {

    String appId = "77fa93e1f50e406392affb609bcd8278";
    String baseUrl = "data/2.5/weather?units=metric&lang=ru&appid="+appId;

    @GET(baseUrl)
    Call<WeatherRequest> loadByCityName(@Query("q") String cityCountry);

    @GET(baseUrl)
    Call<WeatherRequest> loadByCoord(@Query("lat") String lat,
                                     @Query("lon") String lon);

}
