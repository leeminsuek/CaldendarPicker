package com.devs.shoki.caldendarpicker;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.devs.shoki.caldendarpicker.calendar.CalendarCellParams;
import com.devs.shoki.caldendarpicker.calendar.day.CalendarCellView;
import com.devs.shoki.caldendarpicker.calendar.week.CalendarWeekView;
import com.devs.shoki.caldendarpicker.constants.Week;
import com.devs.shoki.caldendarpicker.listener.IDayClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shoki on 2016-03-18.
 */
public class CalendarGridAdapter extends RecyclerView.Adapter<CalendarGridAdapter.CalendarViewHolder> {

    private final int HEADER_CELL_CNT = 7;

    public static final int VIEW_HEADER = 1;
    public static final int VIEW_NORMAL = 2;

    private List<Week> weekList = new ArrayList<>();
    private List<CalendarCellParams> paramsList = new ArrayList<>();

    private IDayClickListener iDayClickListener;

    public CalendarGridAdapter(List<CalendarCellParams> cellParamses) {
        for(Week week : Week.values()){
            weekList.add(week);
        }

        paramsList = cellParamses;
    }

    public void setOnDayClickListener(IDayClickListener listener) {
        iDayClickListener = listener;
    }

    public void setData(List<CalendarCellParams> cellParamses) {
        paramsList = cellParamses;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int size = (int) parent.getContext().getResources().getDimension(R.dimen.calendar_size);

        if(viewType == VIEW_HEADER) {
            CalendarWeekView cell = new CalendarWeekView(parent.getContext());
            CalendarWeekViewHolder calendarWeekViewHolder = new CalendarWeekViewHolder(cell);
            calendarWeekViewHolder.calendarWeekView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return calendarWeekViewHolder;
        }
        else {

            CalendarCellView cell = new CalendarCellView(parent.getContext());
            CalendarCellViewHolder calendarCellViewHolder = new CalendarCellViewHolder(cell);
            calendarCellViewHolder.calendarCellView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, size));
            return calendarCellViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(CalendarViewHolder holder, int position) {
        if(position >= 0 && position < HEADER_CELL_CNT) {
            CalendarWeekViewHolder calendarWeekViewHolder = (CalendarWeekViewHolder) holder;
            calendarWeekViewHolder.calendarWeekView.setData(weekList.get(position));
        }
        else {
            CalendarCellParams params = paramsList.get(position- HEADER_CELL_CNT);
            CalendarCellViewHolder calendarCellViewHolder = (CalendarCellViewHolder) holder;
            calendarCellViewHolder.calendarCellView.setParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return paramsList.size() + HEADER_CELL_CNT;
    }

    @Override
    public int getItemViewType(int position) {
        if(position >= 0 && position < HEADER_CELL_CNT) {
            return VIEW_HEADER;
        }
        return VIEW_NORMAL;
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder {

        public CalendarViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 요일표기 셀
     */
    public class CalendarWeekViewHolder extends CalendarViewHolder {

        public CalendarWeekView calendarWeekView;

        public CalendarWeekViewHolder(View itemView) {
            super(itemView);

            calendarWeekView = (CalendarWeekView) itemView;
        }
    }

    /**
     * 날짜표기 셀
     */
    public class CalendarCellViewHolder extends CalendarViewHolder {

        public CalendarCellView calendarCellView;

        public CalendarCellViewHolder(View itemView) {
            super(itemView);

            calendarCellView = (CalendarCellView) itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(iDayClickListener != null) {
                        iDayClickListener.onDayClickListener(calendarCellView.getParams().getDayParams(), getAdapterPosition());
                    }
                }
            });
        }
    }
}
