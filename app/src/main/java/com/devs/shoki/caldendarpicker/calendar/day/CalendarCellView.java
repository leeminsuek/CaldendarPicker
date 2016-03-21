package com.devs.shoki.caldendarpicker.calendar.day;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devs.shoki.caldendarpicker.R;
import com.devs.shoki.caldendarpicker.calendar.CalendarCellParams;
import com.devs.shoki.caldendarpicker.constants.MonthState;

/**
 * Created by shoki on 2016-03-18.
 */
public class CalendarCellView extends RelativeLayout {
    private CalendarCellParams params;
    private View backgroundView;
    public CalendarCellView(Context context) {
        super(context);

        init();
    }

    public CalendarCellParams getParams() {
        return params;
    }

    private void init() {
        View.inflate(getContext(), R.layout.picker_cell, this);

        backgroundView = findViewById(R.id.picker_cell_background);
    }

    public void setTitle(String day) {
        TextView titleTxtv = (TextView) findViewById(R.id.picker_cell_title_txtv);

        titleTxtv.setText(day);
    }

    public void setTextColor(int state) {
        TextView titleTxtv = (TextView) findViewById(R.id.picker_cell_title_txtv);

        if(state == MonthState.PREV) {
            titleTxtv.setTextColor(getContext().getResources().getColor(R.color.prevTextColor));
        }
        else if(state == MonthState.NOW) {
            titleTxtv.setTextColor(getContext().getResources().getColor(R.color.nowTextColor));
        }
        else {
            titleTxtv.setTextColor(getContext().getResources().getColor(R.color.nextTextColor));
        }
    }

    public void setParams(CalendarCellParams params) {
        this.params = params;
        setTitle(String.valueOf(params.getDayParams().getDay()));
        setTextColor(params.getMonthState());
        setBackgroundColor();
    }

    public void setBackgroundColor() {
        if(params.isSelected()) {
            backgroundView.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
        }
        else {
            backgroundView.setBackgroundColor(getContext().getResources().getColor(android.R.color.white));
        }
    }
}
