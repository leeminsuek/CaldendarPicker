package com.devs.shoki.caldendarpicker.listener;

import com.devs.shoki.caldendarpicker.CalendarPickerDialog;
import com.devs.shoki.caldendarpicker.calendar.CalendarDayParams;

/**
 * Created by shoki on 2016-03-18.
 */
public interface IPickerListener {

    public void onPickerListener(CalendarPickerDialog dialog, CalendarDayParams day);
}
