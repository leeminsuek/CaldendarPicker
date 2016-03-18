package com.devs.shoki.caldendarpicker;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.devs.shoki.caldendarpicker.calendar.CalendarPickerParams;
import com.devs.shoki.caldendarpicker.calendar.CalendarPickerView;
import com.devs.shoki.caldendarpicker.listener.IPickerListener;

import java.util.Date;

/**
 * Created by shoki on 2016-03-18.
 */
public class CalendarPickerDialog extends AlertDialog {

    private CalendarPickerView calendarPickerView;

    protected CalendarPickerDialog(Context context, CalendarPickerParams params) {
        super(context);

        calendarPickerView = new CalendarPickerView(context, params);
        setView(calendarPickerView);
    }


    public static class Builder {
        private CalendarPickerDialog calendarPickerDialog;
        private CalendarPickerParams calendarPickerParams;

        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setOnPickerListener(IPickerListener pickerListener) {
            calendarPickerParams.setPickerListener(pickerListener);
            return this;
        }

        public Builder setStartDate(Date startDate) {
            return this;
        }

        public CalendarPickerDialog create() {
            if(calendarPickerDialog == null) {
                calendarPickerDialog = new CalendarPickerDialog(context, calendarPickerParams);
            }
            return calendarPickerDialog;
        }
    }
}
