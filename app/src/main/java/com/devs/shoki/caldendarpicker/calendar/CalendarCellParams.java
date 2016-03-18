package com.devs.shoki.caldendarpicker.calendar;

public class CalendarCellParams {

    public CalendarCellParams() {
    }

    private int day;
    private int monthState;


    public int getMonthState() {
        return monthState;
    }

    public void setMonthState(int monthState) {
        this.monthState = monthState;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}