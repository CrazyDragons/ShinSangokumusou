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

    private MediaPlayer mediaPlayer;

    public Music(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    /**
     * 播放音乐
     * @param activity 当前activity
     * @param bgm 音乐名称
     */
    public void playBGM(Context activity, int bgm){
        mediaPlayer = MediaPlayer.create(activity, bgm);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void stopBGM(){
        mediaPlayer.stop();
    }

    public void pauseBGM(){
        mediaPlayer.pause();
    }
}