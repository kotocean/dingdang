package cn.kotocean.heart.consumer;

import cn.kotocean.heart.Constants;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import io.reactivex.functions.Consumer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;
import java.util.HashMap;

public class Synthesis implements Consumer<String> {
    String appID = Constants.appID;
    String apiKey = Constants.apiKey;
    String secretKey = Constants.secretKey;

    @Override
    public void accept(String text) throws Exception {
        AipSpeech client = new AipSpeech(appID, apiKey, secretKey);

        String per = "0"; //说话人0，1 ，3，4
        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", "5");
        options.put("pit", "5");
        options.put("per", per);

        TtsResponse res = client.synthesis(text, "zh", 1, options);
        int errorCode = res.getErrorCode();
        //语音合成成功为0
        System.out.println("errorCode:" + errorCode);
        byte[] data = res.getData();
        playWav(data);
    }

     public void playWav(byte[] byteWav) throws FileNotFoundException,JavaLayerException {
        String path ="wav/cache/temp.wav";
         File file = new File(path);
         try {
             //建立输出字节流
             FileOutputStream fos = new FileOutputStream(file);
             fos.write(byteWav);
             //为了节省IO流的开销，需要关闭
             fos.close();
         }catch (Exception ex){
             System.out.println("write faild.");
             return;
         }
         System.out.println("write success.");
         //播放音频文件
         new Player(new BufferedInputStream(new FileInputStream(new File(path)))).play();
     }
}
