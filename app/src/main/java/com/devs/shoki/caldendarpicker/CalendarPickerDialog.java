package com.devs.shoki.caldendarpicker;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.devs.shoki.caldendarpicker.calendar.CalendarDayParams;
import com.devs.shoki.caldendarpicker.calendar.CalendarPickerParams;
import com.devs.shoki.caldendarpicker.calendar.CalendarPickerView;
import com.devs.shoki.caldendarpicker.constants.CalendarMode;
import com.devs.shoki.caldendarpicker.listener.IPickerFromToListener;
import com.devs.shoki.caldendarpicker.listener.IPickerListener;

/**
 * Created by shoki on 2016-03-18.
 */
public class CalendarPickerDialog extends AlertDialog {

    private CalendarPickerView calendarPickerView;

    protected CalendarPickerDialog(Context context, CalendarPickerParams params) {
        super(context);

        calendarPickerView = new CalendarPickerView(context, params, this);
        setView(calendarPickerView);
    }

    public CalendarPickerView getCalendarPickerView() {
        return calendarPickerView;
    }

    public static class Builder {
        private CalendarPickerDialog calendarPickerDialog;
        private CalendarPickerParams calendarPickerParams;

        private Context context;

        public Builder(Context context) {
            this.context = context;
            this.calendarPickerParams = new CalendarPickerParams();
        }

        public Builder setOnPickerListener(IPickerListener pickerListener) {
            calendarPickerParams.setPickerListener(pickerListener);
            return this;
        }

        public Builder setOnPickerFromToListener(IPickerFromToListener pickerFromToListener) {
            calendarPickerParams.setPickerFromToListener(pickerFromToListener);
            return this;
        }

        public Builder setSelectedMode(CalendarMode mode) {
            calendarPickerParams.setMode(mode);
            return this;
        }

        public Builder setStartDate(int year, int month, int day) {
            CalendarDayParams dayParams = new CalendarDayParams();
            dayParams.setYear(year);
            dayParams.setMonth(month);
            dayParams.setDay(day);
            calendarPickerParams.setStartDate(dayParams);
            return this;
        }

        public Builder setEndDate(int year, int month, int day) {
            CalendarDayParams dayParams = new CalendarDayParams();
            dayParams.setYear(year);
            dayParams.setMonth(month);
            dayParams.setDay(day);
            calendarPickerParams.setEndDate(dayParams);
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
