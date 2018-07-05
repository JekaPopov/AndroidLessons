package ru.dravn.androidlessons.fragments;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

import ru.dravn.androidlessons.MainActivity;
import ru.dravn.androidlessons.R;
import ru.dravn.androidlessons.utils.Const;

public class MapFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {


    public static MapFragment newInstance() {

        Bundle args = new Bundle();

        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private GoogleMap map;
    private SupportMapFragment supportMapFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.map, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.setOnMapLongClickListener(this);

        if (ActivityCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }

        LocationManager locationManager = (LocationManager)
                getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location myLocation = locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));

        if (myLocation != null) {
            map.moveCamera(CameraUpdateFactory.zoomTo(15.0f));

            map.moveCamera(CameraUpdateFactory.newLatLng(
                    new LatLng(myLocation.getLatitude(), myLocation.getLongitude())));
        }
    }


    @Override
    public void onMapLongClick(LatLng latLng) {
        HashMap<String, String> message = new HashMap<>();

        message.put(Const.LATITUDE, String.valueOf(latLng.latitude));
        message.put(Const.LONGITUDE, String.valueOf(latLng.longitude));

        ((MainActivity) getActivity()).showWeatherFragment(message);
    }
}
