package ru.dravn.androidlessons.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.dravn.androidlessons.model.WeatherListRequest;
import ru.dravn.androidlessons.model.WeatherRequest;

public interface OpenWeather {

    String appId = "77fa93e1f50e406392affb609bcd8278";
    String weatherUrl = "data/2.5/weather?units=metric&lang=ru&appid="+appId;
    String cycleUrl = "/data/2.5/find?units=metric&lang=ru&appid="+appId;
    String forecastUrl = "data/2.5/forecast?units=metric&lang=ru&appid="+appId;

    @GET(weatherUrl)
    Call<WeatherRequest> loadByCityName(@Query("q") String cityCountry);

    @GET(weatherUrl)
    Call<WeatherRequest> loadByCoord(@Query("lat") String lat,
                                     @Query("lon") String lon);

    @GET(cycleUrl)
    Call<WeatherListRequest> loadByCoordCycle(@Query("lat") Double lat,
                                              @Query("lon") Double lon,
                                              @Query("cnt") String qty);

    @GET(forecastUrl)
    Call<WeatherListRequest> loadForecastByCoord(@Query("lat") Double lat,
                                                 @Query("lon") Double lon);

    @GET(weatherUrl)
    Call<WeatherListRequest> loadForecastByCityName(@Query("q") String cityCountry);

}
