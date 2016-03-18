package com.devs.shoki.caldendarpicker.calendar.week;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devs.shoki.caldendarpicker.R;
import com.devs.shoki.caldendarpicker.constants.Week;

/**
 * Created by shoki on 2016-03-18.
 */
public class CalendarWeekView extends RelativeLayout {

    public CalendarWeekView(Context context) {
        super(context);

        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.picker_week_cell, this);
    }

    public void setTitle(Week week) {
        TextView titleTxtv = (TextView) findViewById(R.id.picker_week_cell_title_txtv);

        titleTxtv.setText(week.week);
    }
}
