package com.hzw.shinsangokumusou.staticvalue;

import android.graphics.Color;

/**
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.staticvalue/MapsValue
 * Created by HZW
 * Data 2017/12/4
 * Time 2:30
 */

public class MapsValue {

    /**
     * 地图总信息
     */
    //地图宽度
    public static int Map_width = 1080;
    //地图高度
    public static int Map_height = 1350;
    //方格大小
    public static int Eachmap = 15;

    /**
     * 地图颜色
     */
    //平地
    public static int Ground = Color.rgb(238, 232, 170);
    //草地
    public static int Glass = Color.rgb(124 ,205 ,124);
    //河流
    public static int Water = Color.rgb(0, 206, 209);
    //城墙
    public static int Wall = Color.rgb(244, 164, 96);

    public static void setMap_width(int map_width) {
        Map_width = map_width;
    }

    public static void setMap_height(int map_height) {
        Map_height = map_height;
    }

    public static int getMap_width() {
        return Map_width;
    }

    public static int getMap_height() {
        return Map_height;
    }
}
