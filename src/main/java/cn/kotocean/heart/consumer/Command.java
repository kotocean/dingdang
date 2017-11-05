package cn.kotocean.heart.consumer;

import cn.kotocean.heart.service.NowTime;
import cn.kotocean.heart.service.Weather;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Command implements Consumer<JSONObject> {
    @Override
    public void accept(JSONObject jsonObject) throws Exception {
        int err_no = jsonObject.getInt("err_no");
        if(0==err_no){
            JSONArray result = jsonObject.getJSONArray("result");
            String command = result.get(0).toString();
            System.out.println("result=" + command);

            if(command.contains("几点") || command.contains("时间")) {
                Flowable.just("现在," + new NowTime().getNow())
                        .subscribe(new Synthesis());
            }else if(command.contains("今天") && command.contains("天气")){
                Flowable.just((new Weather()).get() )
                        .subscribe(new Synthesis());
            }else{
                Flowable.just("听不懂你说什么？该功能可能还不提供？" )
                        .subscribe(new Synthesis());
            }
        }else{
            System.out.println("语音识别错误！err_msg="+jsonObject.getString("err_msg"));
            Flowable.just("听不清楚你说什么？能大声重复一下嘛？" )
                    .subscribe(new Synthesis());
        }
    }
}
