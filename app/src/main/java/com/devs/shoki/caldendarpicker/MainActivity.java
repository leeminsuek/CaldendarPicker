package com.devs.shoki.caldendarpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.devs.shoki.caldendarpicker.calendar.CalendarDayParams;
import com.devs.shoki.caldendarpicker.constants.CalendarMode;
import com.devs.shoki.caldendarpicker.listener.IPickerFromToListener;

public class MainActivity extends AppCompatActivity {

    private CalendarPickerDialog calendarPickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarPickerDialog = new CalendarPickerDialog.Builder(this)
                .setStartDate(2016, 3, 21)
                .setEndDate(2016, 3, 26)
                .setOnPickerFromToListener(new IPickerFromToListener() {
                    @Override
                    public void onPickerFromToListener(CalendarPickerDialog dialog, CalendarDayParams from, CalendarDayParams to) {
                        Log.d("calendar", from.getYear() + "" + from.getMonth() + "" + from.getDay() + " ~ " + to.getYear() + "" + to.getMonth() + "" + to.getDay());
                    }
                })
                .setSelectedMode(CalendarMode.FROM_TO)
//                .setSelectedMode(CalendarMode.SELECT)
//                .setOnPickerListener(new IPickerListener() {
//                    @Override
//                    public void onPickerListener(CalendarPickerDialog dialog, CalendarDayParams day) {
//                        Log.d("calendar", day.getYear()+ "" + day.getMonth() + "" + day.getDay());
//                    }
//                })
                .create();

        calendarPickerDialog.show();
    }
}
