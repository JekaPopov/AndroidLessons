package ru.dravn.androidlessons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);


        cityName = findViewById(R.id.city);
        temp_current = findViewById(R.id.temp_current);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        temp_min = findViewById(R.id.temp_min);
        temp_max = findViewById(R.id.temp_max);
        time_request = findViewById(R.id.time_request);


        cityName.setText("Dolgoprudniy");
        temp_current.setText(format(25,TEMP));
        pressure.setText(format(747,PRESSURE));
        humidity.setText(format(85,HUMIDITY));
        temp_min.setText(format(20,TEMP));
        temp_max.setText(format(27,TEMP));

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
