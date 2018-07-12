package org.danielsoares.pickupapp.Models;

import java.util.HashMap;
import java.util.Map;

public class Time implements Comparable<Time> {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public Time(int thisYear, int thisMonth, int thisDay, int thisHour, int thisMinute) {
        year = thisYear;
        month = thisMonth;
        day = thisDay;
        hour = thisHour;
        minute = thisMinute;
    }

    public Map<String, Integer> toMap() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("year", year);
        map.put("month", month);
        map.put("day", day);
        map.put("hour", hour);
        map.put("minute", minute);
        return map;
    }

    @Override
    public int compareTo(Time that) {
        // that = (Time) that;
        if (year < that.toMap().get("year"))
            return -1;
        if (year > that.toMap().get("year"))
            return 1;
        else {
            if (month < that.toMap().get("month"))
                return -1;
            if (month > that.toMap().get("month"))
                return 1;
            else {
                if (day < that.toMap().get("day"))
                    return -1;
                if (day > that.toMap().get("day"))
                    return 1;
                else {
                    if (hour < that.toMap().get("hour"))
                        return -1;
                    if (hour > that.toMap().get("hour"))
                        return 1;
                    else {
                        if (minute < that.toMap().get("minute"))
                            return -1;
                        if (minute > that.toMap().get("minute"))
                            return 1;
                        else return 0;
                    }
                }
            }
        }
    }
}
