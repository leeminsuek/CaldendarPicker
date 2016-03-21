package com.devs.shoki.caldendarpicker.calendar;

import com.devs.shoki.caldendarpicker.constants.CalendarMode;
import com.devs.shoki.caldendarpicker.listener.IPickerFromToListener;
import com.devs.shoki.caldendarpicker.listener.IPickerListener;

/**
 * Created by shoki on 2016-03-18.
 */
public class CalendarPickerParams {

    private IPickerListener pickerListener;
    private IPickerFromToListener pickerFromToListener;
    private CalendarDayParams startDate;
    private CalendarDayParams endDate;
    private CalendarMode mode = CalendarMode.DEFAULT;

    public CalendarMode getMode() {
        return mode;
    }

    public void setMode(CalendarMode mode) {
        this.mode = mode;
    }

    public IPickerFromToListener getPickerFromToListener() {
        return pickerFromToListener;
    }

    public void setPickerFromToListener(IPickerFromToListener pickerFromToListener) {
        this.pickerFromToListener = pickerFromToListener;
    }

    public IPickerListener getPickerListener() {
        return pickerListener;
    }

    public void setPickerListener(IPickerListener pickerListener) {
        this.pickerListener = pickerListener;
    }

    public CalendarDayParams getStartDate() {
        return startDate;
    }

    public void setStartDate(CalendarDayParams startDate) {
        this.startDate = startDate;
    }

    public CalendarDayParams getEndDate() {
        return endDate;
    }

    public void setEndDate(CalendarDayParams endDate) {
        this.endDate = endDate;
    }
}
