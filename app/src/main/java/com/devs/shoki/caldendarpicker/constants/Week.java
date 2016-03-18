package com.devs.shoki.caldendarpicker.constants;

/**
 * Created by shoki on 2016-03-18.
 */
public enum Week {

    SUN("SUN", 0),
    MON("MON", 1),
    WEN("WEN", 2),
    TUE("TUE", 3),
    WED("WED", 4),
    THU("THU", 5),
    FRI("FIR", 6),
    SAT("SAT", 7)
    ;

    public String week;
    public int index;
    Week(String week, int index) {
        this.week = week;
        this.index = index;
    }
}
