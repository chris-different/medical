package com.medical.medical.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    public static String getToday(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd ");
        Date date = new Date();
        String currentDate =   dateFormat.format( date );
        return currentDate;
    }
    public static String getTodayWeek(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(" EEEE ");
        Date date = new Date();
        String currentDate =   dateFormat.format( date );
        return currentDate;
    }
    //获得日期后几天的日期字符串格式
    public static String returnNDay(Date date,int i){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,i); //把日期往后增加一天,整数  往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd ");
        return dateFormat.format(date);
    }
    //获得日期后几天的星期Date格式
    public static Date returnNDay_Date(Date date,int i){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,i); //把日期往后增加一天,整数  往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date;
    }
    //获得日期后几天的星期
    public static String returnNWeek(Date date,int i){
        return returnWeek(returnNDay_Date(date,i));
    }
    public static Date returnNextDay(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date;
    }
    public static String returnWeek(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(" EEEE ");
        String currentDate =   dateFormat.format( date );
        return currentDate;
    }



//获得从date+i接下来七天日期字符串格式
    public static List<String> returnNextWeekDateString(Date date,int i){
        List<String> nextWeekDate = new ArrayList<>();
        Date newDate = returnNDay_Date(date,i);
        for (int j=0;j<7;j++){
            nextWeekDate.add(returnNDay(newDate,j));
        }
        return nextWeekDate;
    }
//获得接下来七天日期的日期格式
    public static List<Date> returnNextWeekDate(Date date,int i){
        List<Date> nextWeekDate = new ArrayList<>();
        Date newDate = returnNDay_Date(date,i);
        for (int j=0;j<7;j++){
            nextWeekDate.add(returnNDay_Date(newDate,j));
        }
        return nextWeekDate;
    }
//获得接下来七天星期
    public static List<String> returnNextWeekName(Date date,int i){
        List<String> nextWeekName = new ArrayList<>();
        Date newDate = returnNDay_Date(date,i);
        for (int j=0;j<7;j++){
            nextWeekName.add(returnNWeek(newDate,j));
        }
        return nextWeekName;
    }

}
