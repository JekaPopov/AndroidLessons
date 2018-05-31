package ru.dravn.androidlessons;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class WeatherViewFragment extends BaseFragment {


    public static WeatherViewFragment newInstance(HashMap<String, String> map) {
        
        Bundle args = new Bundle();
        args.putSerializable(MESSAGE,map);
        WeatherViewFragment fragment = new WeatherViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private HashMap<String, String> mMessage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mMessage = (HashMap<String, String>)getArguments().getSerializable(MESSAGE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_view, container, false);

        TextView cityName = view.findViewById(R.id.city);
        TextView temp_current = view.findViewById(R.id.temp_current);
        TextView pressure = view.findViewById(R.id.pressure);
        TextView humidity = view.findViewById(R.id.humidity);
        TextView temp_min = view.findViewById(R.id.temp_min);
        TextView temp_max = view.findViewById(R.id.temp_max);
        TextView time_request = view.findViewById(R.id.time_request);
        ImageView weatherImage = view.findViewById(R.id.weatherImage);

        cityName.setText(mMessage.get(CITY));
        temp_current.setText(format(Integer.valueOf(mMessage.get(CURRTEMP)), TEMP));
        pressure.setText(format(Integer.valueOf(mMessage.get(PRESSURE)), PRESSURE));
        humidity.setText(format(Integer.valueOf(mMessage.get(HUMIDITY)), TEMP));
        temp_max.setText(format(Integer.valueOf(mMessage.get(MAXTEMP)), TEMP));
        temp_min.setText(format(Integer.valueOf(mMessage.get(MINTEMP)), TEMP));


        weatherImage.setImageDrawable(getImageRes(mMessage.get(WEATHER)));
        Date date = new Date();
        String stringDate = DateFormat.getDateTimeInstance(0,2).format(date);

        time_request.setText(stringDate);

        return view;
    }
}
