package com.hzw.shinsangokumusou.chess;

import android.content.Context;
import android.view.View;

import com.hzw.shinsangokumusou.staticvalue.MapsValue;

/**
 * 棋子的基类
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.chess/Chess
 * Created by HZW
 * Data 2017/12/6
 * Time 21:38
 */

public  class Chess extends View {

    public Chess(Context context) {
        super(context);
    }

    /**
     * 初始化玩家棋子位置
     * @param X 棋盘X轴位置
     * @param Y 棋盘Y轴位置
     */
    public void SetPlayerPosition(int X, int Y, float multiple){
        setTranslationX((X - 2) * MapsValue.Eachmap);
        setTranslationY((Y - 3) * MapsValue.Eachmap);
        setScaleX(multiple);
        setScaleY(multiple);
        setPivotX(-MapsValue.Eachmap * (X - 2));
        setPivotY(-MapsValue.Eachmap * (Y - 3));
    }

    /**
     * 初始化玩家将领位置
     * @param X 棋盘X轴位置
     * @param Y 棋盘Y轴位置
     */
    public void SetGeneralPosition(int X, int Y, float multiple){
        setTranslationX((X - 1) * MapsValue.Eachmap);
        setTranslationY((Y - 1) * MapsValue.Eachmap);
        setScaleX(multiple);
        setScaleY(multiple);
        setPivotX(-MapsValue.Eachmap * (X - 1));
        setPivotY(-MapsValue.Eachmap * (Y - 1));
    }
}
