package ru.dravn.androidlessons;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.HashMap;


public class SetWeatherFragment extends BaseFragment {


    public static SetWeatherFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SetWeatherFragment fragment = new SetWeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    private EditText mCityName;
    private EditText mTempCurrent;
    private EditText mPressure;
    private EditText mHumidity;
    private EditText mTempMin;
    private EditText mTempMax;
    private Spinner  mWeatherSpinner;
    private Button mButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_set_weather, container, false);

        mCityName = view.findViewById(R.id.city);
        mTempCurrent = view.findViewById(R.id.temp_current);
        mPressure = view.findViewById(R.id.pressure);
        mHumidity = view.findViewById(R.id.humidity);
        mTempMin = view.findViewById(R.id.temp_min);
        mTempMax = view.findViewById(R.id.temp_max);
        mButton = view.findViewById(R.id.button);

        mWeatherSpinner = view.findViewById(R.id.weather);


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

                HashMap<String, String> mMessage = new HashMap<>();

                mMessage.put(CITY, mCityName.getText().toString());
                mMessage.put(CURRTEMP, mTempCurrent.getText().toString());
                mMessage.put(MINTEMP, mTempMin.getText().toString());
                mMessage.put(MAXTEMP, mTempMax.getText().toString());
                mMessage.put(HUMIDITY, mHumidity.getText().toString());
                mMessage.put(PRESSURE, mPressure.getText().toString());
                mMessage.put(WEATHER, mWeatherSpinner.getSelectedItem().toString());


                mActivity.showWeatherFragment(mMessage);
            }
        });


        return view;
    }

}
