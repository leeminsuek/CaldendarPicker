package com.devs.shoki.caldendarpicker.calendar;

import com.devs.shoki.caldendarpicker.listener.IPickerListener;

/**
 * Created by shoki on 2016-03-18.
 */
public class CalendarPickerParams {

    private IPickerListener pickerListener;
    private CalendarDayParams startDate;
    private CalendarDayParams endDate;

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
