package ru.dravn.androidlessons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.TextView;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {


    private TextView cityName;
    private TextView temp_current;
    private TextView pressure;
    private TextView humidity;
    private TextView temp_min;
    private TextView temp_max;
    private TextView time_request;

    final private static String TEMP = "temp";
    final private static String HUMIDITY = "hum";
    final private static String PRESSURE = "press";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        cityName = findViewById(R.id.city);
        temp_current = findViewById(R.id.temp_current);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        temp_min = findViewById(R.id.temp_min);
        temp_max = findViewById(R.id.temp_max);
        time_request = findViewById(R.id.time_request);


        Intent intent = getIntent();
        HashMap<String, String> msg = (HashMap<String, String>)intent.getSerializableExtra("map");


        cityName.setText(msg.get("city"));
        temp_current.setText(format(Integer.valueOf(msg.get("currTemp")),TEMP));
        pressure.setText(format(Integer.valueOf(msg.get("pressure")),PRESSURE));
        humidity.setText(format(Integer.valueOf(msg.get("humidity")),HUMIDITY));
        temp_min.setText(format(Integer.valueOf(msg.get("minTemp")),TEMP));
        temp_max.setText(format(Integer.valueOf(msg.get("maxTemp")),TEMP));

        Time now = new Time();
        now.setToNow();
        time_request.setText(now.format("%d.%m.%Y %H.%M.%S"));
    }



    private String format(int val, String valName)
    {
        String format;
        switch (valName)
        {
            case TEMP:
                format = getResources().getString(R.string.temp_format);
                return String.format(format, val);
            case HUMIDITY:
                format = getResources().getString(R.string.humidity_format);
                return String.format(format, val);
            case PRESSURE:
                format = getResources().getString(R.string.pressure_format);
                return String.format(format, val);
            default: return "";
        }

    }
}
