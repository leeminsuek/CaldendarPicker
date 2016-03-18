package com.devs.shoki.caldendarpicker.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by shoki on 2016-03-18.
 */
public class DateUtil {

    // 해당 달의 첫 요일을 구해서 돌려줌.
    public static int getStartDay(int year, int month) {
        int monthSum = 0;
        int leapYear = 0;
        int daySum = 1;

        for (int i = 1; i < year; i++) {
            monthSum += 365;
            if (isLeapYear(i) == true) {
                leapYear += 1;
            }
        }

        for (int j = 1; j < month; j++) {
            daySum += getNumberOfDaysInMonth(year, j);
        }
        return (monthSum + leapYear + daySum) % 7;
    }

    // 해당 년도가 윤년인지 판별
    public static boolean isLeapYear(int year) {
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }

    // 달의 마지막 일을 구함
    public static int getNumberOfDaysInMonth(int year, int month) {

        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else if (month == 2 && isLeapYear(year) == true) {
            return 29;
        } else if (month == 2 && isLeapYear(year) == false) {
            return 28;
        } else {
            return 31;
        }
    }

    //파라미터의 해당하는 년월의 전달을 구한다.
    public static String getBeforeYearMonthByYM(int year, int month, int minVal){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        Calendar cal = Calendar.getInstance();

        cal.set(year, month-minVal, 0);

        String beforeYear = dateFormat.format(cal.getTime()).substring(0,4);
        String beforeMonth = dateFormat.format(cal.getTime()).substring(4,6);
        String retStr = beforeYear + beforeMonth;

        return retStr;
    }

    //해당년월의 마지막 날짜를 구한다.
    public static String getLastDayOfMonth(String yearMonth){
        String year = yearMonth.substring(0, 4);
        String month = yearMonth.substring(4, 6);

        int _year = Integer.parseInt(year);
        int _month = Integer.parseInt(month);

        Calendar calendar = Calendar.getInstance();
        calendar.set(_year, (_month-1), 1); //월은 0부터 시작
        String lastDay = String.valueOf(calendar.getActualMaximum(Calendar.DATE));

        return lastDay;
    }

    //전날 가져오기
    public static String getBeforeDay(String yearMonthDay, int minVal) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
        java.util.Date date = new java.util.Date();
        try {
            date = sdf.parse(yearMonthDay);

            long lCurTime = date.getTime();
            date = new java.util.Date(lCurTime+(1000*60*60*24*-minVal));

            return sdf.format(date).substring(6,8);
        } catch (ParseException e) {
            e.printStackTrace();
            return "0";
        }
    }
}
