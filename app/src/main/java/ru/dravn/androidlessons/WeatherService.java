package ru.dravn.androidlessons;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import static ru.dravn.androidlessons.BaseFragment.CITY;
import static ru.dravn.androidlessons.BaseFragment.TEMP;

public class WeatherService extends Service {

    private MyBinder binder = new MyBinder();
    private String city;
    private Handler mHandler;

    public WeatherService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void onCreate() {
        super.onCreate();
        Timer mTimer = new Timer();
        MyTimerTask mMyTimerTask = new MyTimerTask();
        mHandler = new Handler();
        mTimer.schedule(mMyTimerTask, 0, 1000 * 60 * 30);

    }

    private void getTemp() {

        new Thread() {
            public void run() {
                final Location myLocation = getPosition();
                final JSONObject message;
                if (city != null) {
                    message = WeatherData.getDate(getApplicationContext(), city);
                } else if (myLocation != null) {
                    message = WeatherData.getDate(getApplicationContext(), String.valueOf(myLocation.getLatitude()),
                            String.valueOf(myLocation.getLongitude()));
                } else {
                    return;
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            makeNote(message.getString(CITY), String.valueOf(message.getJSONObject("main").getInt(TEMP)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }.start();
    }


    public Location getPosition() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return null;
        } else {
            LocationManager locationManager = (LocationManager)
                    getApplicationContext().getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            return locationManager.getLastKnownLocation(locationManager
                    .getBestProvider(criteria, false));
        }
    }

    void makeNote(String cityName, String currTemp) {

        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(cityName)
                .setContentText(String.format(getResources().getString(R.string.temp_format), currTemp));
        Intent resultIntent = new Intent(this, WeatherService.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager =
                (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
        int MSG = 123;
        notificationManager.notify(MSG, builder.build());
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    class MyBinder extends Binder {
        WeatherService getService() {
            return WeatherService.this;
        }
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    getTemp();
                }
            });
        }
    }
}


