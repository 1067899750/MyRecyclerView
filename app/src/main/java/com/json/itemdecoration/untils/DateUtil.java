package com.json.itemdecoration.untils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 */
public class DateUtil {
    //long -> str
    public static String getStringDateByLong(long dateLong, int type) {
        String template = null;
        switch (type) {
            case 1:
                template = "yyyy-MM-dd HH:mm";
                break;
            case 2:
                template = "yyyy/MM/dd";
                break;
            case 3:
                template = "yyyy年MM月dd日";
                break;
            case 4:
                template = "yyyy-MM-dd";
                break;
            case 5:
                template = "yyyy-MM-dd HH:mm:ss";
                break;
            case 6:
                template = "MM月dd号";
                break;
            case 7:
                template = "yyyy/MM/dd HH:mm:ss";
                break;
            case 8:
                template = "HH:mm";
                break;
            case 9:
                template = "MM/dd";
                break;
            case 10:
                template = "yyyy/MM";
                break;
            case 11:
                template = "MM/dd HH:mm";
                break;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.CHINA);
        Date date = new Date(dateLong);//* 1000L
        String dateString = sdf.format(date);
        return dateString;
    }

    public static Date getDateByByStringDate(String dateString) {
        if (TextUtils.isEmpty(dateString)) {
            return null;
        }
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CHINA);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
        }
        return date;
    }

    //str -> long
    public static long dateToStamp(String s, int type) {
        String template = null;
        switch (type) {
            case 1:
                template = "yyyy-MM-dd HH:mm";
                break;
            case 2:
                template = "yyyy/MM/dd";
                break;
            case 3:
                template = "yyyy年MM月dd日";
                break;
            case 4:
                template = "yyyy-MM-dd";
                break;
            case 5:
                template = "yyyy-MM-dd HH:mm:ss";
                break;
            case 6:
                template = "MM月dd号";
                break;
            case 7:
                template = "yyyy/MM/dd HH:mm:ss";
                break;
            case 8:
                template = "HH:mm";
                break;
            case 9:
                template = "MM/dd";
                break;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template);
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    //日期加一天
    public static String addOneDayDate(long dateLen){
        Date today = new Date(dateLen);
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return getStringDateByLong(c.getTime().getTime(), 9);
    }

    public static void main(String[] argc) {
        System.out.println(getStringDateByLong(1543496400000l, 1));
    }

}
