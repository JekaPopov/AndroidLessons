package ru.dravn.androidlessons.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import ru.dravn.androidlessons.R;
import ru.dravn.androidlessons.utils.Const;

public class ForecastFragment extends BaseFragment{


    public static WeatherViewFragment newInstance(HashMap<String, String> message) {

        Bundle args = new Bundle();
        args.putSerializable(Const.MESSAGE, message);
        WeatherViewFragment fragment = new WeatherViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private HashMap<String, String> mMessage = new HashMap<>();
    private TextView cityName;
    private TextView temp_current;
    private TextView pressure;
    private TextView humidity;
    private TextView temp_min;
    private TextView temp_max;
    private TextView time_request;
    private ImageView weatherImage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessage = (HashMap<String, String>) getArguments().getSerializable(Const.MESSAGE);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_view, container, false);

        cityName = view.findViewById(R.id.city);
        temp_current = view.findViewById(R.id.temp_current);
        pressure = view.findViewById(R.id.pressure);
        humidity = view.findViewById(R.id.humidity);
        temp_min = view.findViewById(R.id.temp_min);
        temp_max = view.findViewById(R.id.temp_max);
        time_request = view.findViewById(R.id.time_request);
        weatherImage = view.findViewById(R.id.weatherImage);


        renderWeather();
        return view;
    }


    //Обработка загруженных данных
    private void renderWeather() {
        //cityName.setText(mMessage.get(Const.CITY));

        temp_current.setText(Const.format(mMessage.get(Const.TEMP), Const.TEMP, getContext()));
        pressure.setText(Const.format(mMessage.get(Const.PRESSURE), Const.PRESSURE, getContext()));
        humidity.setText(Const.format(mMessage.get(Const.HUMIDITY), Const.HUMIDITY, getContext()));
        temp_max.setText(Const.format(mMessage.get(Const.MAXTEMP), Const.TEMP, getContext()));
        temp_min.setText(Const.format(mMessage.get(Const.MINTEMP), Const.TEMP, getContext()));


        setWeatherIcon(mMessage.get(Const.IMAGE), weatherImage);

        Date date = new Date();

        long millis = date.getTime();
        time_request.setText(DateFormat
                .getDateTimeInstance(0, 2)
                .format(millis));
    }

    //Подстановка нужной иконки
    private void setWeatherIcon(String id, ImageView img) {

        Picasso.get()
                .load(Const.format(id,Const.IMAGE, getContext()))
                .resize(200,200)
                .placeholder(R.drawable.place_holder)
                .into(img);
    }

}
