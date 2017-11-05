package cn.kotocean.heart;

import cn.kotocean.heart.consumer.Asr;
import cn.kotocean.heart.service.MyRecord;
import cn.kotocean.heart.service.NowTime;
import io.reactivex.*;

import java.lang.reflect.Executable;

public class HelloWorld {
    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);
        //录音功能
        MyRecord myRecord = new MyRecord();
        while(true) {
            System.out.println("可以开始对话了~"+(new NowTime(true)).getNow());
            myRecord.capture();
            sleep(3000);
            myRecord.stop();
            //发出新的声音产生的状态改变
            System.out.println("正在解析，请稍后~"+(new NowTime(true)).getNow());
            Flowable.just("wav/cache/record.wav")
                    .subscribe(new Asr());
            sleep(5000);
            System.out.println("可以开始下一次对话了~"+(new NowTime(true)).getNow());
        }
    }
    public static void sleep(int millis){
        try {
            Thread.sleep(millis);//睡眠时间
        }catch (Exception ex){
            System.out.println("Exception:" + ex.toString());
        }
    }
}
