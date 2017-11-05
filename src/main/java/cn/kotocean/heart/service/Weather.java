package cn.kotocean.heart.service;

import javazoom.jl.decoder.JavaLayerException;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class Weather {
    public static void main(String[] args){
        try {
            (new Weather()).get();
        }catch(Exception ex){}
    }
    public String get()throws Exception{
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://api.seniverse.com/v3/weather/now.json?key=kx7j5xpr8ou5k4bp&location=beijing&language=zh-Hans&unit=c");
        // 调用HttpClient对象的execute方法获得响应
        HttpResponse response = client.execute(get);

        // 调用HttpResponse对象的getEntity方法得到响应实体
        HttpEntity httpEntity = response.getEntity();

        // 使用EntityUtils工具类得到响应的字符串表示
        String respond = EntityUtils.toString(httpEntity,"utf-8");
        JSONObject jsonObject = new JSONObject(respond);
        //System.out.println(result);
        JSONObject results = (JSONObject)jsonObject.getJSONArray("results").get(0);
        JSONObject nowInfo = results.getJSONObject("now");
        String result = "现在户外，天气" + nowInfo.getString("text")+"，温度"+nowInfo.getString("temperature")+"℃";
        System.out.println(result);
        return result;
    }


}
