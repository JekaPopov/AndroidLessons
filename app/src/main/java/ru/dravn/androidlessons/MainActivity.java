package ru.dravn.androidlessons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView clock = findViewById(R.id.clock);

        Time now = new Time();
        now.setToNow();

        clock.setText(now.format("%d.%m.%Y %H.%M.%S"));

    }
}
