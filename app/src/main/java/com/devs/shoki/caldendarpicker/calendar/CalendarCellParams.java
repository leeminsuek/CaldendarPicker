package com.devs.shoki.caldendarpicker.calendar;

public class CalendarCellParams {


    public CalendarCellParams() {
    }

    private int monthState;
    private CalendarDayParams dayParams;
    private boolean selected = false;
    private int selectedState;

    public int getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(int selectedState) {
        this.selectedState = selectedState;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public CalendarDayParams getDayParams() {
        return dayParams;
    }

    public void setDayParams(CalendarDayParams dayParams) {
        this.dayParams = dayParams;
    }

    public int getMonthState() {
        return monthState;
    }

    public void setMonthState(int monthState) {
        this.monthState = monthState;
    }
}