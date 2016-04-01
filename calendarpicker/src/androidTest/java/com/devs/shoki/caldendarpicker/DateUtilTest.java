package com.devs.shoki.caldendarpicker;

import com.devs.shoki.calendarpicker.calendar.param.CalendarDayParams;
import com.devs.shoki.calendarpicker.util.DateUtil;

import junit.framework.TestCase;

/**
 * Created by shoki on 2016-03-23.
 */
public class DateUtilTest extends TestCase {


    public void test() {
        CalendarDayParams params = new CalendarDayParams();
        params.setYear(2016);
        params.setMonth(2);
        params.setDay(1);

        CalendarDayParams params2 = new CalendarDayParams();
        params2.setYear(2016);
        params2.setMonth(1);
        params2.setDay(31);

        assertEquals(-1, DateUtil.isDifferenceOfDay(params, params2));
    }
}
