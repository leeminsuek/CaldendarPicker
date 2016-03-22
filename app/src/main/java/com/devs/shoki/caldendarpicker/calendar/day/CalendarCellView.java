package com.devs.shoki.caldendarpicker.calendar.day;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devs.shoki.caldendarpicker.R;
import com.devs.shoki.caldendarpicker.calendar.CalendarCellParams;
import com.devs.shoki.caldendarpicker.calendar.CalendarPickerParams;
import com.devs.shoki.caldendarpicker.constants.CalendarMode;
import com.devs.shoki.caldendarpicker.constants.Config;
import com.devs.shoki.caldendarpicker.constants.MonthState;

/**
 * Created by shoki on 2016-03-18.
 */
public class CalendarCellView extends RelativeLayout {

    private CalendarCellParams params;
    private CalendarPickerParams pickerParams;
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

    public void setParams(CalendarCellParams params, CalendarPickerParams pickerParams) {
        this.params = params;
        this.pickerParams = pickerParams;
        setTitle(String.valueOf(params.getDayParams().getDay()));
        setTextColor(params.getMonthState());
        setBackgroundColor();
    }

    public void setBackgroundColor() {
        if(params.isSelected()) {
            if(pickerParams.getMode().equals(CalendarMode.SELECT)) {
                backgroundView.setBackgroundDrawable(pickerParams.getSelectedDrawable());
            }
            else if(pickerParams.getMode().equals(CalendarMode.FROM_TO)) {
                if(params.getSelectedState() == Config.SELECTED_FIRST_DATE) {
                    backgroundView.setBackgroundDrawable(pickerParams.getSelectedFirstDrawable());
                }
                else if(params.getSelectedState() == Config.SELECTED_LAST_DATE) {
                    backgroundView.setBackgroundDrawable(pickerParams.getSelectedLastDrawable());
                }
                else if(params.getSelectedState() == Config.SELECTED_BETWEEN_DATE) {
                    backgroundView.setBackgroundDrawable(pickerParams.getSelectedBetweenDrawable());
                }
                else {
                    backgroundView.setBackgroundDrawable(pickerParams.getSelectedDrawable());
                }
            }
        }
        else {
            backgroundView.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.calendar_date_default));
        }
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
//    }
}
