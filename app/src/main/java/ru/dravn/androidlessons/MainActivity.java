package ru.dravn.androidlessons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import static android.provider.Telephony.Mms.Part.TEXT;


public class MainActivity extends AppCompatActivity {

    private EditText mCityName;
    private EditText mTempCurrent;
    private EditText mPressure;
    private EditText mHumidity;
    private EditText mTempMin;
    private EditText mTempMax;


    private Button mButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mCityName = findViewById(R.id.city);
        mTempCurrent = findViewById(R.id.temp_current);
        mPressure = findViewById(R.id.pressure);
        mHumidity = findViewById(R.id.humidity);
        mTempMin = findViewById(R.id.temp_min);
        mTempMax = findViewById(R.id.temp_max);
        mButton = findViewById(R.id.button);

        final String format = getResources().getString(R.string.msg_format);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mCityName.getText().length()==0)
                {
                    showMessage(getResources().getString(R.string.editCity));
                    mCityName.requestFocus();
                }
                else if(mTempCurrent.getText().length()==0)
                {
                    showMessage(getResources().getString(R.string.editTemp));
                    mTempCurrent.requestFocus();
                }
                else if(mPressure.getText().length()==0)
                {
                    showMessage(getResources().getString(R.string.editPressure));
                    mPressure.requestFocus();
                }
                else if(mHumidity.getText().length()==0)
                {
                    showMessage(getResources().getString(R.string.editHumidity));
                    mHumidity.requestFocus();
                }
                else if(mTempMin.getText().length()==0)
                {
                    showMessage(getResources().getString(R.string.editMinTemp));
                    mTempMin.requestFocus();
                }
                else if(mTempMax.getText().length()==0)
                {
                    showMessage(getResources().getString(R.string.editMaxTemp));
                    mTempMax.requestFocus();
                }

                HashMap<String, String> msg = new HashMap<>();

                msg.put("city", mCityName.getText().toString());
                msg.put("currTemp", mTempCurrent.getText().toString());
                msg.put("minTemp", mTempMin.getText().toString());
                msg.put("maxTemp", mTempMax.getText().toString());
                msg.put("humidity", mHumidity.getText().toString());
                msg.put("pressure", mPressure.getText().toString());

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("map", msg);
                startActivity(intent);
            }
        });




    }

    protected void showMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
