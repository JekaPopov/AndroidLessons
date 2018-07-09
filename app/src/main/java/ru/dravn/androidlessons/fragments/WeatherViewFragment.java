package ru.dravn.androidlessons.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.dravn.androidlessons.MainActivity;
import ru.dravn.androidlessons.R;
import ru.dravn.androidlessons.WeatherData;
import ru.dravn.androidlessons.model.WeatherRequest;
import ru.dravn.androidlessons.utils.Const;


public class WeatherViewFragment extends BaseFragment {


    public static WeatherViewFragment newInstance(HashMap<String, String> message) {

        Bundle args = new Bundle();
        args.putSerializable(Const.MESSAGE, message);
        WeatherViewFragment fragment = new WeatherViewFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private static final int PERMISSION_REQUEST_CODE = 1;
    private HashMap<String, String> mMessage = new HashMap<>();
    private TextView cityName;
    private TextView temp_current;
    private TextView pressure;
    private TextView humidity;
    private TextView temp_min;
    private TextView temp_max;
    private TextView time_request;
    private ImageView weatherImage;


    private Callback<WeatherRequest> callback;

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

        callback = new Callback<WeatherRequest>() {
            @Override
            public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                if (response.body() != null)
                    renderWeather(response.body());
                else
                    Toast.makeText(getContext(), "Город не найден", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<WeatherRequest> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), R.string.serverError, Toast.LENGTH_SHORT).show();
            }
        };

        if (getArguments().getSerializable(Const.MESSAGE) != null) {
            mMessage = (HashMap<String, String>) getArguments().getSerializable(Const.MESSAGE);

            updateWeatherData();
        } else {
            getCurrentLocation();
        }


        return view;
    }


    private void updateWeatherData() {

        if (mMessage.get(Const.CITY) != null) {
            WeatherData.loadByCityName(callback, mMessage.get(Const.CITY));
        }
        if (mMessage.get(Const.LATITUDE) != null && mMessage.get(Const.LONGITUDE) != null) {
            WeatherData.loadByCoord(callback, mMessage.get(Const.LATITUDE), mMessage.get(Const.LONGITUDE));
        }
    }

    //Обработка загруженных данных
    private void renderWeather(WeatherRequest response) {
            cityName.setText(response.getName());

            temp_current.setText(Const.format(response.getMain().getTemp(), Const.TEMP, getContext()));
            pressure.setText(Const.format(response.getMain().getPressure(), Const.PRESSURE, getContext()));
            humidity.setText(Const.format(response.getMain().getHumidity(), Const.HUMIDITY, getContext()));
            temp_max.setText(Const.format(response.getMain().getTempMax(), Const.TEMP, getContext()));
            temp_min.setText(Const.format(response.getMain().getTempMin(), Const.TEMP, getContext()));
            temp_max.setText(Const.format(response.getMain().getTempMax(), Const.TEMP, getContext()));


            setWeatherIcon(response.getWeather().get(0).getIcon(), weatherImage);

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
                .placeholder(R.drawable.place_holder)
                .into(img);
    }


    private void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestForLocationPermission();
        } else {
            LocationManager locationManager = (LocationManager)
                    getActivity().getSystemService(getActivity().LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            Location myLocation = locationManager.getLastKnownLocation(locationManager
                    .getBestProvider(criteria, false));
            if (myLocation != null) {


                WeatherData.loadByCoord(callback,
                        String.valueOf(myLocation.getLatitude()),
                        String.valueOf(myLocation.getLongitude()));
            } else {
                ((MainActivity) getActivity()).showMapFragment();
            }
        }
    }


    public void requestForLocationPermission() {

        if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            ActivityCompat.requestPermissions(getActivity(), new
                    String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]
            permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {

                getCurrentLocation();
            }
        }
    }

}
