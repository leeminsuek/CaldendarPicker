package com.devs.shoki.caldendarpicker.calendar.day;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devs.shoki.caldendarpicker.R;

/**
 * Created by shoki on 2016-03-18.
 */
public class CalendarCellView extends RelativeLayout {
    public CalendarCellView(Context context) {
        super(context);

        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.picker_cell, this);
    }

    public void setTitle(String day) {
        TextView titleTxtv = (TextView) findViewById(R.id.picker_cell_title_txtv);

        titleTxtv.setText(day);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        setMeasuredDimension(widthSize, widthSize);
//    }
}
