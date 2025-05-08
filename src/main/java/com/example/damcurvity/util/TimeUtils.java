package com.example.damcurvity.util;

import com.example.damcurvity.common.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static String getNowTime()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }
    public static String LongTOTime(long time)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date(time));
    }
    public static String getNextDay(Integer days)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date d=new Date();
        String predate = df.format(new Date(d.getTime() + (long)24 * 60 * 60 * 1000*days));
        return predate;
    }
    public static String byTimeNextDay(String time,Integer days)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date d = new Date();
        try {
            d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String predate = df.format(new Date(d.getTime() + (long)24 * 60 * 60 * 1000*days));
        return predate;
    }

    public static String intToTime(int year,int month,int day, int h,int m)
    {
        return year+"-"+month+"-"+day+" "+h+":"+m;
    }
    public static double getHourDifferentTime(String strT1,String strT2)
    {
        Date t1 = null,t2=null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            t1 = df.parse(strT1);
            t2 = df.parse(strT2);
        } catch (ParseException e) {
            Constant.logger.error("错误:",e);
        }
        Long time1 = t1.getTime();
        Long time2 = t2.getTime();
        Double hour = (double)(time2-time1)/(1000*60*60);
        return hour;

    }

    public static String standTime(String s) {
        String pat1 = "yyyyMMddHH";
        String pat2 = "yyyy-MM-dd";
        SimpleDateFormat sdf1 = new SimpleDateFormat(pat1) ;        // 实例化模板对象
        SimpleDateFormat sdf2 = new SimpleDateFormat(pat2) ;
        Date d = null ;
        try{
            d = sdf1.parse(s) ;   // 将给定的字符串中的日期提取出来
        }catch(Exception e){            // 如果提供的字符串格式有错误，则进行异常处理
            Constant.logger.error("错误:",e) ;       // 打印异常信息
        }
        return sdf2.format(d);
    }
}
