package com.devs.shoki.caldendarpicker.listener;

import com.devs.shoki.caldendarpicker.CalendarPickerDialog;
import com.devs.shoki.caldendarpicker.calendar.param.CalendarDayParams;

/**
 * Created by shoki on 2016-03-21.
 */
public interface IPickerFromToListener {
    public void onPickerFromToListener(CalendarPickerDialog dialog, CalendarDayParams from, CalendarDayParams to);
}
