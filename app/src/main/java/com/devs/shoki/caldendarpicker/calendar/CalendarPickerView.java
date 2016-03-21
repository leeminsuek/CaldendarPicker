package com.devs.shoki.caldendarpicker.calendar;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devs.shoki.caldendarpicker.CalendarGridAdapter;
import com.devs.shoki.caldendarpicker.CalendarPickerDialog;
import com.devs.shoki.caldendarpicker.R;
import com.devs.shoki.caldendarpicker.constants.CalendarMode;
import com.devs.shoki.caldendarpicker.constants.Config;
import com.devs.shoki.caldendarpicker.constants.MonthState;
import com.devs.shoki.caldendarpicker.listener.IDayClickListener;
import com.devs.shoki.caldendarpicker.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shoki on 2016-03-18.
 */
public class CalendarPickerView extends RelativeLayout {

    private RecyclerView recyclerView;
    private Button selectBtn;

    private CalendarGridAdapter adapter;
    private List<CalendarCellParams> cellParamsList;
    private Calendar calendar;
    private Map<String, CalendarDayParams> selectParamsMap;
    private CalendarPickerParams params;
    private CalendarPickerDialog dialog;

    public CalendarPickerView(Context context, CalendarPickerParams params, CalendarPickerDialog dialog) {
        super(context);

        this.dialog = dialog;
        this.params = params;

        checkMode();
        init();
    }

    private void checkMode() {
        if(params.getMode().equals(CalendarMode.SELECT)) {
            params.setPickerFromToListener(null);
            params.setStartDate(null);
            params.setEndDate(null);
        }
        else if(params.getMode().equals(CalendarMode.FROM_TO)){
            params.setPickerListener(null);
        }
    }

    private void init() {
        View.inflate(getContext(), R.layout.picker_main, this);

        recyclerView = (RecyclerView) findViewById(R.id.picker_main_recyclerview);
        selectBtn = (Button) findViewById(R.id.picker_main_select_btn);

        cellParamsList = new ArrayList<>();

        calendar = Calendar.getInstance();
        calendar.set(2016, 4-1, 0);

        selectParamsMap = new HashMap<>();

        if(params.getStartDate() != null) {
            selectParamsMap.put(Config.SELECT_START_DATE, params.getStartDate());

            if(params.getEndDate() != null) {
                selectParamsMap.put(Config.SELECT_END_DATE, params.getEndDate());
            }
        }

        Button prevBtn = (Button) findViewById(R.id.picker_main_prev_btn);
        Button nextBtn = (Button) findViewById(R.id.picker_main_next_btn);

        prevBtn.setTag(1);
        nextBtn.setTag(2);

        prevBtn.setOnClickListener(btnClickListener);
        nextBtn.setOnClickListener(btnClickListener);
        selectBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(params.getMode().equals(CalendarMode.FROM_TO)) {
                    if(params.getPickerFromToListener() != null) {
                        params.getPickerFromToListener().onPickerFromToListener(dialog, selectParamsMap.get(Config.SELECT_START_DATE), selectParamsMap.get(Config.SELECT_END_DATE));
                    }
                }
            }
        });

        initRecyclerView();
        createMonthOfDay();
    }


    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 7, LinearLayoutManager.VERTICAL, false);

        adapter = new CalendarGridAdapter(cellParamsList, selectParamsMap);
        adapter.setOnDayClickListener(new IDayClickListener() {
            @Override
            public void onDayClickListener(CalendarDayParams day, int position) {
                Log.d("calendar", day.getYear() + "년" + day.getMonth() + "월" + day.getDay() + "일");
                if(params.getMode().equals(CalendarMode.FROM_TO)) {
                    if(selectParamsMap.containsKey(Config.SELECT_START_DATE)) {
                        selectParamsMap.put(Config.SELECT_END_DATE, day);
                    }
                    else {
                        selectParamsMap.put(Config.SELECT_START_DATE, day);
                    }

                    if(selectParamsMap.containsKey(Config.SELECT_END_DATE)) {

                        CalendarDayParams startDay = selectParamsMap.get(Config.SELECT_START_DATE);
                        CalendarDayParams endDay = selectParamsMap.get(Config.SELECT_END_DATE);
                        boolean allCheck = false;
                        for(int i = 0 ;i < cellParamsList.size() ; i ++) {
                            if(DateUtil.isDifferenceOfDay(startDay, cellParamsList.get(i).getDayParams()) >= 0 &&
                                    (DateUtil.isDifferenceOfDay(endDay, cellParamsList.get(i).getDayParams()) == -1 ||
                                            DateUtil.isDifferenceOfDay(endDay, cellParamsList.get(i).getDayParams()) == 0 )) {
                                cellParamsList.get(i).setSelected(true);
                                allCheck = true;
                            }
                            else {
                                cellParamsList.get(i).setSelected(false);
                            }
                        }

                        if(!allCheck) {
                            selectParamsMap.clear();
                        }

                        adapter.notifyDataSetChanged();

                    }
                    else {
                        if(position -7 >= 0) {
                            cellParamsList.get(position -7).setSelected(true);
                            adapter.notifyItemChanged(position);
                        }
                    }
                }
                else {
                    if(position -7 >= 0) {
                        cellParamsList.get(position -7).setSelected(true);
                        selectParamsMap.put(Config.SELECT_DATE, cellParamsList.get(position - 7).getDayParams());
                        adapter.notifyItemChanged(position);

                        if(params.getMode().equals(CalendarMode.SELECT)) {
                            dialog.dismiss();
                            if(params.getPickerFromToListener() != null) {
                                params.getPickerListener().onPickerListener(dialog, selectParamsMap.get(Config.SELECT_DATE));
                            }
                        }
                    }
                }
            }
        });

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

                    CalendarDayParams calendarDayParams = new CalendarDayParams();
                    calendarDayParams.setDay(Integer.parseInt(day));
                    calendarDayParams.setMonth(Integer.parseInt(beforeYearMonthBy.substring(4, 6)));
                    calendarDayParams.setYear(Integer.parseInt(beforeYearMonthBy.substring(0, 4)));

                    beforeParams.setDayParams(calendarDayParams);
                    cellParamsList.add(0, beforeParams);
                }
            }
            CalendarDayParams calendarDayParams = new CalendarDayParams();
            calendarDayParams.setDay(i);
            calendarDayParams.setMonth(month);
            calendarDayParams.setYear(year);
            params.setDayParams(calendarDayParams);
            params.setMonthState(MonthState.NOW);
            cellParamsList.add(params);
        }

        Calendar copy = (Calendar) calendar.clone();
        copy.add(Calendar.MONTH, 2);

        int plusDayCnt = 7 * ((cellParamsList.size() / 7) + 1) - cellParamsList.size();
        for (int i = 1; i <= plusDayCnt; i ++) {
            CalendarCellParams params = new CalendarCellParams();
            params.setMonthState(MonthState.NEXT);
            CalendarDayParams calendarDayParams = new CalendarDayParams();
            calendarDayParams.setDay(i);
            calendarDayParams.setMonth(copy.get(Calendar.MONTH));
            calendarDayParams.setYear(copy.get(Calendar.YEAR));
            params.setDayParams(calendarDayParams);
            cellParamsList.add(params);
        }

        copy.clear();
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
