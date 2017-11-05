package cn.kotocean.heart.consumer;

import cn.kotocean.heart.Constants;
import com.baidu.aip.speech.AipSpeech;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import org.json.JSONObject;

public class Asr implements Consumer<String> {
    String appID = Constants.appID;
    String apiKey = Constants.apiKey;
    String secretKey = Constants.secretKey;

    @Override
    public void accept(String path) throws Exception {
        AipSpeech client = new AipSpeech(appID, apiKey, secretKey);
        //语音识别
        JSONObject res = client.asr(path, "wav", 16000, null);
        Flowable.just(res)
                .subscribe(new Command());
    }
}
