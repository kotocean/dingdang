package cn.kotocean.heart.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NowTime {
    boolean onlyTime = true;
    boolean second = false;

    public NowTime(){}

    public NowTime(boolean onlyTime, boolean second){
        this.onlyTime = onlyTime;
        this.second = second;
    }
    public NowTime(boolean second){
        this.second = second;
    }

    //查询当前时间
    public String getNow(){
        String pattern = "HH:mm";
        if(!onlyTime){
            pattern = "yyyy-MM-dd HH:mm";
        }else if(second){
            pattern = "HH:mm:ss";
        }
        DateFormat format=new SimpleDateFormat(pattern);
        String time=format.format(new Date());
        return time;
    }

}
