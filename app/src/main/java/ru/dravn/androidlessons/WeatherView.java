package ru.dravn.androidlessons;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class WeatherView extends Parameter {


    private TextView cityName;
    private TextView temp_current;
    private TextView pressure;
    private TextView humidity;
    private TextView temp_min;
    private TextView temp_max;
    private TextView time_request;
    private ImageView weatherImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_view);

        cityName = findViewById(R.id.city);
        temp_current = findViewById(R.id.temp_current);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        temp_min = findViewById(R.id.temp_min);
        temp_max = findViewById(R.id.temp_max);
        time_request = findViewById(R.id.time_request);
        weatherImage = findViewById(R.id.weatherImage);


        Intent intent = getIntent();
        HashMap<String, String> msg = (HashMap<String, String>) intent.getSerializableExtra(MESSAGE);


        cityName.setText(msg.get(CITY));
        temp_current.setText(format(Integer.valueOf(msg.get(CURRTEMP)),TEMP));
        pressure.setText(format(Integer.valueOf(msg.get(PRESSURE)),PRESSURE));
        humidity.setText(format(Integer.valueOf(msg.get(HUMIDITY)),TEMP));
        temp_max.setText(format(Integer.valueOf(msg.get(MAXTEMP)),TEMP));
        temp_min.setText(format(Integer.valueOf(msg.get(MINTEMP)),TEMP));


        weatherImage.setImageDrawable(getImageRes(msg.get(WEATHER)));


        Time now = new Time();
        now.setToNow();
        time_request.setText(now.format("%d.%m.%Y %H.%M.%S"));


    }

}
