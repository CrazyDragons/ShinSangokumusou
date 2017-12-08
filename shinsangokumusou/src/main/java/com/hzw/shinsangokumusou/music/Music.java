package com.hzw.shinsangokumusou.music;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * 音乐
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.music/Music
 * Created by HZW
 * Data 2017/11/14
 * Time 11:34
 */

public class Music {

    private static MediaPlayer mediaPlayer;

    public Music() {}

    /**
     * 播放音乐
     * @param activity 当前activity
     * @param bgm 音乐名称
     */
    public static void playBGM(Context activity, int bgm){
        mediaPlayer = MediaPlayer.create(activity, bgm);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public static void stopBGM(){
        mediaPlayer.stop();
    }

    public static void pauseBGM(){
        mediaPlayer.pause();
    }
}