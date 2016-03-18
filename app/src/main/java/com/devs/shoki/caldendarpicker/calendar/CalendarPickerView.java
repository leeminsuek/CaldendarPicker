package com.devs.shoki.caldendarpicker.calendar;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devs.shoki.caldendarpicker.CalendarGridAdapter;
import com.devs.shoki.caldendarpicker.R;
import com.devs.shoki.caldendarpicker.constants.MonthState;
import com.devs.shoki.caldendarpicker.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by shoki on 2016-03-18.
 */
public class CalendarPickerView extends RelativeLayout {

    private RecyclerView recyclerView;
    private CalendarGridAdapter adapter;
    private List<CalendarCellParams> cellParamsList;
    private Calendar calendar;

    public CalendarPickerView(Context context, CalendarPickerParams params) {
        super(context);

        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.picker_main, this);

        recyclerView = (RecyclerView) findViewById(R.id.picker_main_recyclerview);

        cellParamsList = new ArrayList<>();

        calendar = Calendar.getInstance();
        calendar.set(2016, 4-1, 0);

        Button prevBtn = (Button) findViewById(R.id.picker_main_prev_btn);
        Button nextBtn = (Button) findViewById(R.id.picker_main_next_btn);

        prevBtn.setTag(1);
        nextBtn.setTag(2);

        prevBtn.setOnClickListener(btnClickListener);
        nextBtn.setOnClickListener(btnClickListener);

        initRecyclerView();
        createMonthOfDay();
    }


    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 7, LinearLayoutManager.VERTICAL, false);

        adapter = new CalendarGridAdapter(cellParamsList);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 해당 월 텍스트 표기
     */
    private void setMonthTitle() {
        TextView monthTitleTxtv = (TextView) findViewById(R.id.picker_main_month_txtv);

        monthTitleTxtv.setText(calendar.get(Calendar.YEAR) + "년" + (calendar.get(Calendar.MONTH) + 1) + "월");
    }

    /**
     * 달력 그리기
     */
    private void createMonthOfDay() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;

        cellParamsList.clear();

        int emptyStart = 0;
        for (int i = 1; i <= DateUtil.getNumberOfDaysInMonth(year, month); i++) {
            CalendarCellParams params = new CalendarCellParams();
            if (i < 2) {
                for (int y = 0; y < DateUtil.getStartDay(year, month); y++) {
                    CalendarCellParams beforeParams = new CalendarCellParams();
                    String beforeYearMonthBy = DateUtil.getBeforeYearMonthByYM(year, month, 1);
                    String lastDayOfMonth =DateUtil.getLastDayOfMonth(beforeYearMonthBy);

                    String day = DateUtil.getBeforeDay(beforeYearMonthBy+lastDayOfMonth, emptyStart);
                    emptyStart++;

                    beforeParams.setMonthState(MonthState.PREV);
                    beforeParams.setDay(Integer.parseInt(day));
                    cellParamsList.add(0, beforeParams);
                }
            }

            params.setDay(i);
            params.setMonthState(MonthState.NOW);
            cellParamsList.add(params);
        }

        int plusDayCnt = 7 * ((cellParamsList.size() / 7) + 1) - cellParamsList.size();
        for (int i = 1; i <= plusDayCnt; i ++) {
            CalendarCellParams params = new CalendarCellParams();
            params.setMonthState(MonthState.NEXT);
            params.setDay(i);
            cellParamsList.add(params);
        }
        setMonthTitle();

        adapter.setData(cellParamsList);
        adapter.notifyDataSetChanged();
    }

    /**
     * 이전/다음버튼
     */
    private OnClickListener btnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            if(tag == 1) {
                calendar.add(Calendar.MONTH, -1);
                createMonthOfDay();
            }
            else {
                calendar.add(Calendar.MONTH, 1);
                createMonthOfDay();
            }
        }
    };
}
