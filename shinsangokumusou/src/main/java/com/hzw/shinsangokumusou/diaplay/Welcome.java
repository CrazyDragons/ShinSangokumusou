package com.hzw.shinsangokumusou.diaplay;

import android.media.SoundPool;
import android.os.Bundle;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.hzw.shinsangokumusou.R;
import com.hzw.shinsangokumusou.music.SoundEffects;
import com.hzw.shinsangokumusou.utils.ActivityUtils;

import okhttp3.OkHttpClient;

/*
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.activity/Welcome
 * Created by HZW
 * Data 2017/11/14
 * Time 10:10
 */

/**
 * 欢迎界面
 */

public class Welcome extends BaseDisplay {

    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();

    }

    public void init(){
        //在App入口设置sqlite在线查看插件
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        new SoundEffects(Welcome.this, new SoundPool.Builder().build()).playSoundEffects(R.raw.soundeffects_opening);


        //定时跳转
        new ActivityUtils().DelayJunm(Welcome.this, Home.class, 4000);
    }
}
