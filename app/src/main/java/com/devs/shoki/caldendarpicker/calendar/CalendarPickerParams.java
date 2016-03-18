package com.devs.shoki.caldendarpicker.calendar;

import com.devs.shoki.caldendarpicker.listener.IPickerListener;

import java.util.Date;

/**
 * Created by shoki on 2016-03-18.
 */
public class CalendarPickerParams {

    private IPickerListener pickerListener;
    private Date startDate;
    private Date endDate;

    public IPickerListener getPickerListener() {
        return pickerListener;
    }

    public void setPickerListener(IPickerListener pickerListener) {
        this.pickerListener = pickerListener;
    }
}
