package cn.kotocean.heart.service;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

public class MyRecord {
    AudioFormat audioFormat;
    TargetDataLine targetDataLine;

    public void capture(){
        //开始录音
        captureAudio();
    }

    public void stop(){
        //从麦克风。
        targetDataLine.stop();
        targetDataLine.close();

    }
    //此方法捕获从一个音频输入
    //麦克风，并将其保存在一个音频文件。
    private void captureAudio() {
        try {
            //得到的东西设定为捕捉
            audioFormat = getAudioFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);

            //创建一个线程来捕捉麦克风
            //数据转换为音频文件并启动
            //线程运行。它会一直运行，直到
            //停止按钮被点击。该方法
            //将启动线程后返回。
            new CaptureThread().start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }// end catch
    }// end captureAudio method

    //此方法创建并返回一个
    //对于一组给定的格式AudioFormat对象
    //参数。如果这些参数不工作
    //很适合你，尝试一些其他的
    //允许的参数值，这显示
    //以下的声明的评论。
    private AudioFormat getAudioFormat() {
        float sampleRate = 16000.0F;
        // 8000,11025,16000,22050,44100
        int sampleSizeInBits = 16;
        // 8,16
        int channels = 1;
        // 1,2
        boolean signed = true;
        // true,false
        boolean bigEndian = false;
        // true,false
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,
                bigEndian);
    }// end getAudioFormat

    // =============================================//

    //内部类从麦克风中捕获数据
    //并将其写入到输出的音频文件。
    class CaptureThread extends Thread {
        public void run() {
            AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;;
            File audioFile = new File("wav/cache/record.wav");

            try {
                targetDataLine.open(audioFormat);
                targetDataLine.start();
                AudioSystem.write(new AudioInputStream(targetDataLine),fileType, audioFile);
            } catch (Exception e) {
                e.printStackTrace();
            }// end catch

        }// end run
    }
}