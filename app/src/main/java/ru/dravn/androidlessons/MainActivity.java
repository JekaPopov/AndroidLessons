package ru.dravn.androidlessons;


import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    FragmentManager mFragmentManager;
    ImageButton mMenuButton;
    BaseFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMenuButton = findViewById(R.id.menu);
        mFragmentManager = getSupportFragmentManager();

        if(mFragmentManager.findFragmentByTag(getString(R.string.setWeatherFragment))==null) {
            fragment = SetWeatherFragment.newInstance();
            mFragmentManager.beginTransaction()
                    .replace(R.id.fragment, fragment, getString(R.string.setWeatherFragment))
                    .addToBackStack(getString(R.string.setWeatherFragment))
                    .commit();
        }

        mMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragment instanceof MenuFragment) {
                    mFragmentManager.popBackStack();
                    fragment=null;
                }
                else
                showMenuFragment();
            }
        });
    }

    public void showWeatherFragment(HashMap<String, String> mMessage) {

        fragment = WeatherViewFragment.newInstance(mMessage);

        mFragmentManager.beginTransaction()
                .replace(R.id.fragment, fragment,getString(R.string.weatherFragment))
                .addToBackStack(getString(R.string.weatherFragment))
                .commit();
    }

    public void showMenuFragment() {
        fragment = MenuFragment.newInstance();
        mFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, fragment,getString(R.string.menuFragment))
                .addToBackStack(getString(R.string.menuFragment))
                .commit();
    }

}
