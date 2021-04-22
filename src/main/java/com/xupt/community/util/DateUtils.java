package com.xupt.community.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String convertToTimeStr(Long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        String timeStr = simpleDateFormat.format(date);
        return timeStr;
    }
}
