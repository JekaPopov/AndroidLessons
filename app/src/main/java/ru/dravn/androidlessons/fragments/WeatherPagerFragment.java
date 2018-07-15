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
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.dravn.androidlessons.MainActivity;
import ru.dravn.androidlessons.R;
import ru.dravn.androidlessons.WeatherData;
import ru.dravn.androidlessons.model.WeatherListRequest;
import ru.dravn.androidlessons.utils.Const;

public class WeatherPagerFragment extends BaseFragment {


    private static final int PERMISSION_REQUEST_CODE = 1;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private Callback<WeatherListRequest> callback;
    private HashMap<String, String> mMessage;


    public static WeatherPagerFragment newInstance(HashMap<String, String> message) {

        Bundle args = new Bundle();
        args.putSerializable(Const.MESSAGE, message);
        WeatherPagerFragment fragment = new WeatherPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_pager_view, container, false);

        pager = view.findViewById(R.id.pager);

        callback = new Callback<WeatherListRequest>() {
            @Override
            public void onResponse(Call<WeatherListRequest> call, Response<WeatherListRequest> response) {
                if (response.body() != null) {
                    pagerAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), response.body().getList());
                    pager.setAdapter(pagerAdapter);
                } else
                    showMessage(R.string.no_city);
            }

            @Override
            public void onFailure(Call<WeatherListRequest> call, Throwable t) {
                t.printStackTrace();
                showMessage(R.string.serverError);
            }
        };


        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mMessage = (HashMap<String, String>) getArguments().getSerializable(Const.MESSAGE);
        if (mMessage != null) {
            updateWeatherData();
        } else {
            getCurrentLocation();
        }

        return view;
    }


    private void updateWeatherData() {

        if (mMessage.get(Const.CITY) != null) {
            WeatherData.loadForecast(callback, mMessage.get(Const.CITY));
        }
        else if (mMessage.get(Const.LATITUDE) != null && mMessage.get(Const.LONGITUDE) != null) {
            WeatherData.loadForecast(callback, Double.valueOf(mMessage.get(Const.LATITUDE)), Double.valueOf(mMessage.get(Const.LONGITUDE)));
        }
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


                WeatherData.loadForecast(callback,
                        myLocation.getLatitude(),
                        myLocation.getLongitude());
            } else {
                showFragment(MapFragment.class, null);
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
