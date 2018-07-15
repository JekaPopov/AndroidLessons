package ru.dravn.androidlessons.fragments;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Callback;
import ru.dravn.androidlessons.model.List;
import ru.dravn.androidlessons.model.WeatherRequest;
import ru.dravn.androidlessons.utils.Const;

class MyFragmentPagerAdapter extends FragmentPagerAdapter {


    private  java.util.List<List> mList = new ArrayList<>();
    private HashMap<String, String> mMessage = new HashMap<>();


    public MyFragmentPagerAdapter(FragmentManager fm, java.util.List<List> list) {
        super(fm);
        this.mList = list;


    }

    @Override
    public Fragment getItem(int position) {
        mMessage.put(Const.CITY,mList.get(position).getName());
        mMessage.put(Const.TEMP,mList.get(position).getMain().getTemp());
        mMessage.put(Const.PRESSURE,mList.get(position).getMain().getPressure());
        mMessage.put(Const.HUMIDITY,mList.get(position).getMain().getHumidity());
        mMessage.put(Const.MINTEMP,mList.get(position).getMain().getTempMin());
        mMessage.put(Const.MAXTEMP,mList.get(position).getMain().getTempMax());
        mMessage.put(Const.IMAGE,mList.get(position).getWeather().get(0).getIcon());


       return position==0?WeatherViewFragment.newInstance(mMessage):ForecastFragment.newInstance(mMessage);
    }

    @Override
    public int getCount() {
        return mList.size();
    }


}
