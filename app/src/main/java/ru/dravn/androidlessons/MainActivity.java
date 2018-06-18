package ru.dravn.androidlessons;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(mFragmentManager.getBackStackEntryCount()>1)
            super.onBackPressed();
            else
            {
                drawer.openDrawer(GravityCompat.START);
            }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.selectByMap) {
            showMapFragment();
        } else if (id == R.id.selectCity) {
            showCityListFragment();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_exit) {
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
