package com.manishkpr.exercise.util;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

public class Format {

    public static String getDate(long time, String format) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format(format, cal).toString();
        return date;
    }

}
