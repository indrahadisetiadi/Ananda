package com.example.ging.starter_project.util;


import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ging-windows on 2/28/2018.
 */

public class CustomTime {
    String formatString;
    public static String getCurrentDate (String format) {
        long date = System.currentTimeMillis();
        SimpleDateFormat dt = new SimpleDateFormat(format);
        String currentDate = dt.format(date);
        return currentDate;
    }

    public static Integer getMonthdiff (Date d1, Date d2) {

            // HH converts hour in 24 hours format (0-23), day calculation
            // must match with your date format
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            long diffMonth = 0;
            try {

                //in milliseconds
                long diff = d2.getTime() - d1.getTime();

                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);
                diffMonth = diffDays / 30;

            } catch (Exception e) {
                e.printStackTrace();
            }

        return Integer.parseInt(Long.toString(diffMonth));
    }

}
