package com.hzw.shinsangokumusou.diaplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.hzw.shinsangokumusou.R;
import com.hzw.shinsangokumusou.video.CG;

import java.util.Timer;
import java.util.TimerTask;

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

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        //隐藏标题
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //定时跳转
        final Intent intent = new Intent(this, CG.class);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        };
        //设置2000毫秒后跳转
        timer.schedule(timerTask, 2000);
    }
}
