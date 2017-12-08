package com.hzw.shinsangokumusou.diaplay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.diaplay/BaseDisplay
 * Created by HZW
 * Data 2017/12/8
 * Time 23:51
 */

public abstract class BaseDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏标题
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    protected void init(){}
}
