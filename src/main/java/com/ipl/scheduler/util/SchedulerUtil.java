package com.ipl.scheduler.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SchedulerUtil {

    private SchedulerUtil() {
    }

    public static boolean checkIfWeekEnd(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK) == 1 || c.get(Calendar.DAY_OF_WEEK) == 7;

    }

    public static List<Long> leftShiftByOne(List<Long> matches) {
        long shiftedElement = matches.remove(0);
        matches.add(matches.size() - 1, shiftedElement);
        return matches;
    }
}
