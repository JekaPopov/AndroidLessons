package ru.dravn.androidlessons;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONObject;
import java.text.DateFormat;
import java.util.HashMap;


public class WeatherViewFragment extends BaseFragment {


    public static WeatherViewFragment newInstance(HashMap<String, String> message) {

        Bundle args = new Bundle();
        args.putSerializable(MESSAGE, message);
        WeatherViewFragment fragment = new WeatherViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private HashMap<String, String> mMessage=new HashMap<>();
    private Handler handler;
    private TextView cityName;
    private TextView temp_current;
    private TextView pressure;
    private TextView humidity;
    private TextView temp_min;
    private TextView temp_max;
    private TextView time_request;
    private ImageView weatherImage;


    private MainActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = (MainActivity)getActivity();
        if (getArguments().getSerializable(MESSAGE) != null)
            mMessage = (HashMap<String, String>) getArguments().getSerializable(MESSAGE);
        else
        {
            LocationManager locationManager = (LocationManager)
                    mActivity.getSystemService(mActivity.LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                showMessage(getString(R.string.permission));
            }
            Location myLocation = locationManager.getLastKnownLocation(locationManager
                    .getBestProvider(criteria, false));
            mMessage.put(LATITUDE, String.valueOf(myLocation.getLatitude()));
            mMessage.put(LONGITUDE, String.valueOf(myLocation.getLongitude()));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_view, container, false);

        handler = new Handler();

        cityName = view.findViewById(R.id.city);
        temp_current = view.findViewById(R.id.temp_current);
        pressure = view.findViewById(R.id.pressure);
        humidity = view.findViewById(R.id.humidity);
        temp_min = view.findViewById(R.id.temp_min);
        temp_max = view.findViewById(R.id.temp_max);
        time_request = view.findViewById(R.id.time_request);
        weatherImage = view.findViewById(R.id.weatherImage);

            updateWeatherData();

        return view;
    }

    //Обновление/загрузка погодных данных
    private void updateWeatherData() {
        new Thread() {
            public void run() {
                JSONObject json= null;
                if(mMessage.get(CITY)!=null) {
                    json = WeatherData.getDate(mActivity, mMessage.get(CITY));
                }
                if(mMessage.get(LATITUDE)!=null&&mMessage.get(LONGITUDE)!=null){
                    json = WeatherData.getDate(mActivity, mMessage.get(LATITUDE), mMessage.get(LONGITUDE));
                }
                if (json == null) {
                    handler.post(new Runnable() {
                        public void run() {
                            showMessage("Ошибка сервера");
                        }
                    });
                } else {
                    final JSONObject finalJson = json;
                    handler.post(new Runnable() {
                        public void run() {
                            renderWeather(finalJson);
                        }
                    });
                }
            }
        }.start();
    }

    //Обработка загруженных данных
    private void renderWeather(JSONObject json) {
        try {
            cityName.setText(json.getString(CITY));

            JSONObject weather = json.getJSONArray(WEATHER).getJSONObject(0);
            JSONObject main = json.getJSONObject("main");

            //перевод в ммHg
            int prMM = (int)(main.getInt(PRESSURE)*0.75006375541921);
            temp_current.setText(format(main.getString(TEMP), TEMP));
            pressure.setText(format(String.valueOf(prMM), PRESSURE));
            humidity.setText(format(main.getString(HUMIDITY), HUMIDITY));
            temp_max.setText(format(main.getString(MAXTEMP), TEMP));
            temp_min.setText(format(main.getString(MINTEMP), TEMP));

            weatherImage.setImageDrawable(setWeatherIcon(weather.getInt("id")));

            String stringDate = DateFormat.getDateTimeInstance(0, 2)
                    .format(json.getLong("dt") * 1000);

            time_request.setText(stringDate);

        } catch (Exception e) {
            showMessage("Ошибка сервера");
        }
    }

    //Подстановка нужной иконки
    private Drawable setWeatherIcon(int actualId) {
        int id = actualId / 100;

        switch (id) {
            case 2:
                return getImageRes(getString(R.string.storm));
            case 3:
                return getImageRes(getString(R.string.rain));
            case 5:
                return getImageRes(getString(R.string.rain));
            case 6:
                return getImageRes(getString(R.string.snow));
            case 7:
                return getImageRes(getString(R.string.cloudy));
            case 8:
                return getImageRes(getString(R.string.sunny));

            default: return getImageRes(getString(R.string.sunny));
        }
    }
}
