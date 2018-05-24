package ru.dravn.androidlessons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;


public class SetWeather extends Parameter {

    private EditText mCityName;
    private EditText mTempCurrent;
    private EditText mPressure;
    private EditText mHumidity;
    private EditText mTempMin;
    private EditText mTempMax;
    private Spinner  mWeatherSpinner;

    private Button mButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_weather);


        mCityName = findViewById(R.id.city);
        mTempCurrent = findViewById(R.id.temp_current);
        mPressure = findViewById(R.id.pressure);
        mHumidity = findViewById(R.id.humidity);
        mTempMin = findViewById(R.id.temp_min);
        mTempMax = findViewById(R.id.temp_max);
        mButton = findViewById(R.id.button);

        mWeatherSpinner = findViewById(R.id.weather);
        

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mCityName.getText().length()==0)
                {
                    showMessage(getResources().getString(R.string.editCity));
                    mCityName.requestFocus();
                    return;
                }
                else if(mTempCurrent.getText().length()==0)
                {
                    showMessage(getResources().getString(R.string.editTemp));
                    mTempCurrent.requestFocus();
                    return;
                }
                else if(mPressure.getText().length()==0)
                {
                    showMessage(getResources().getString(R.string.editPressure));
                    mPressure.requestFocus();
                    return;
                }
                else if(mHumidity.getText().length()==0)
                {
                    showMessage(getResources().getString(R.string.editHumidity));
                    mHumidity.requestFocus();
                    return;
                }
                else if(mTempMin.getText().length()==0)
                {
                    showMessage(getResources().getString(R.string.editMinTemp));
                    mTempMin.requestFocus();
                    return;
                }
                else if(mTempMax.getText().length()==0)
                {
                    showMessage(getResources().getString(R.string.editMaxTemp));
                    mTempMax.requestFocus();
                    return;
                }

                HashMap<String, String> msg = new HashMap<>();

                msg.put(CITY, mCityName.getText().toString());
                msg.put(CURRTEMP, mTempCurrent.getText().toString());
                msg.put(MINTEMP, mTempMin.getText().toString());
                msg.put(MAXTEMP, mTempMax.getText().toString());
                msg.put(HUMIDITY, mHumidity.getText().toString());
                msg.put(PRESSURE, mPressure.getText().toString());
                msg.put(WEATHER, mWeatherSpinner.getSelectedItem().toString());
                
                
                Intent intent = new Intent(SetWeather.this, WeatherView.class);
                intent.putExtra(MESSAGE, msg);
                startActivity(intent);
            }
        });

    }




}
