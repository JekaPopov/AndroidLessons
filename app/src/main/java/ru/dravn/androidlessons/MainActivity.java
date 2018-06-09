package ru.dravn.androidlessons;


import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;


import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    FragmentManager mFragmentManager;
    ImageButton mMapButton;
    BaseFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMapButton = findViewById(R.id.map);
        mFragmentManager = getSupportFragmentManager();

        if(mFragmentManager.findFragmentByTag(getString(R.string.weatherFragment))==null) {
            showWeatherFragment(null);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.selectCity:
                showCityListFragment();
                return true;
            case R.id.selectByMap:
                showMapFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showWeatherFragment(HashMap<String, String> mMessage) {

        fragment = WeatherViewFragment.newInstance(mMessage);

        mFragmentManager.beginTransaction()
                .replace(R.id.fragment, fragment,getString(R.string.weatherFragment))
                .addToBackStack(getString(R.string.weatherFragment))
                .commit();
    }

    public void showMapFragment() {
        fragment = MapFragment.newInstance();
        mFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, fragment,getString(R.string.mapFragment))
                .addToBackStack(getString(R.string.mapFragment))
                .commit();
    }


    public void showCityListFragment() {
        fragment = CityListFragment.newInstance();
        mFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, fragment,getString(R.string.CityListFragment))
                .addToBackStack(getString(R.string.CityListFragment))
                .commit();
    }
}
