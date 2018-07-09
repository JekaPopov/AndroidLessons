package ru.dravn.androidlessons.service;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.dravn.androidlessons.App;
import ru.dravn.androidlessons.R;
import ru.dravn.androidlessons.WeatherData;
import ru.dravn.androidlessons.model.WeatherRequest;
import ru.dravn.androidlessons.utils.Const;


public class WeatherService extends Service {

    private MyBinder binder = new MyBinder();
    private String city;
    private Handler mHandler;
    private Callback<WeatherRequest> callback;

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

        callback = new Callback<WeatherRequest>() {
            @Override
            public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                if (response.body() != null)
                    makeNote(response.body());
            }

            @Override
            public void onFailure(Call<WeatherRequest> call, Throwable t) {
                t.printStackTrace();
            }

        };

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private void getTemp() {

        new Thread() {
            public void run() {
                final Location myLocation = getPosition();
                if (city != null) {
                    WeatherData.loadByCityName(callback, city);
                } else if (myLocation != null) {
                    WeatherData.loadByCoord(callback, String.valueOf(myLocation.getLatitude()),
                            String.valueOf(myLocation.getLongitude()));
                }
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

    void makeNote(WeatherRequest response) {

        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(response.getName())
                .setContentText(Const.format(response.getMain().getTemp(), Const.TEMP, getApplicationContext()));


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

    public class MyBinder extends Binder {
        public WeatherService getService() {
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


