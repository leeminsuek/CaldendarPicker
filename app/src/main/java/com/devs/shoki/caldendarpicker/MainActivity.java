package com.devs.shoki.caldendarpicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private CalendarPickerDialog calendarPickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarPickerDialog = new CalendarPickerDialog.Builder(this)
                .create();

        calendarPickerDialog.show();
    }
}
