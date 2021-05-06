package com.xupt.community.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String convertToTimeStr(Long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        String timeStr = simpleDateFormat.format(date);
        return timeStr;
    }

    public static Long convertToLong(String time) {
        String timestamp = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long longTime = sdf.parse(time).getTime();
            return longTime;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1L;
    }

}
