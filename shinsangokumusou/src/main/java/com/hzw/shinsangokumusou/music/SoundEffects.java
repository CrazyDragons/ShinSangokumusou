package com.hzw.shinsangokumusou.music;

import android.content.Context;
import android.media.SoundPool;

/**
 * 音效
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.music/SoundEffects
 * Created by HZW
 * Data 2017/12/9
 * Time 1:51
 */

public  class SoundEffects {

    public Context context;
    public SoundPool soundPool;

    public SoundEffects(Context context, SoundPool soundPool) {
        this.context = context;
        this.soundPool = soundPool;
    }

    public void playSoundEffects(int soundeffects){
        final int SoundID = soundPool.load(context, soundeffects,  1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                soundPool.play(SoundID, 1,  1, 1, 0, 1);
            }
        });
    }
}
