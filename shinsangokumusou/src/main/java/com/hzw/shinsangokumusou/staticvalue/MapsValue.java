package com.hzw.shinsangokumusou.staticvalue;

import android.graphics.Color;

import static com.hzw.shinsangokumusou.R.drawable.maps_cb;
import static com.hzw.shinsangokumusou.R.drawable.maps_cbp;
import static com.hzw.shinsangokumusou.R.drawable.maps_cd;
import static com.hzw.shinsangokumusou.R.drawable.maps_djs;
import static com.hzw.shinsangokumusou.R.drawable.maps_fc;
import static com.hzw.shinsangokumusou.R.drawable.maps_gd;
import static com.hzw.shinsangokumusou.R.drawable.maps_gy;
import static com.hzw.shinsangokumusou.R.drawable.maps_hf;
import static com.hzw.shinsangokumusou.R.drawable.maps_hfxc;
import static com.hzw.shinsangokumusou.R.drawable.maps_hj;
import static com.hzw.shinsangokumusou.R.drawable.maps_hlg;
import static com.hzw.shinsangokumusou.R.drawable.maps_jt;
import static com.hzw.shinsangokumusou.R.drawable.maps_lb;
import static com.hzw.shinsangokumusou.R.drawable.maps_nm;
import static com.hzw.shinsangokumusou.R.drawable.maps_st;
import static com.hzw.shinsangokumusou.R.drawable.maps_tg;
import static com.hzw.shinsangokumusou.R.drawable.maps_wc;
import static com.hzw.shinsangokumusou.R.drawable.maps_wj;
import static com.hzw.shinsangokumusou.R.drawable.maps_wzy;
import static com.hzw.shinsangokumusou.R.drawable.maps_yl;

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
    //最大宽度格数（X轴）
    public static int MAX_X = 72;
    //最大宽度格数（Y轴）
    public static int MAX_Y = 90;

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

    /**
     *地图数据信息
     */

    //地图名
    public static String[] Maps_Name = {
            "黄巾之乱", "虎牢关之战", "刘表奇袭战", "宛城之战",
            "吴郡攻略战", "官渡之战", "关羽千里行", "长板之战",
            "赤壁之战", "成都压制战", "潼关之战", "合肥之战",
            "樊城之战", "定军山之战", "夷陵之战", "南蛮平定战",
            "街亭之战", "石亭谋略战", "合肥新城包围战", "五丈原之战"
    };

    //地图id
    public static int[] Maps_Images = {
            maps_hj, maps_hlg, maps_lb,maps_wc,
            maps_wj, maps_gd, maps_gy, maps_cbp,
            maps_cb, maps_cd, maps_tg, maps_hf,
            maps_fc, maps_djs, maps_yl, maps_nm,
            maps_jt, maps_st, maps_hfxc, maps_wzy
    };

    //地图年份
    public static String[] Maps_Yaer = {
            "公元184年", "公元191年", "公元192年", "公元197年",
            "公元197年", "公元200年", "公元201年", "公元208年",
            "公元208年", "公元214年", "公元211年", "公元215年",
            "公元219年", "公元219年", "公元222年", "公元225年",
            "公元228年", "公元228年", "公元234年", "公元234年"
    };

    //友军
    public static String[] Maps_Select_A = {
            "联合军", "联合军", "孙坚军", "曹操军",
            "孙策军", "曹操军", "关羽军", "刘备军",
            "联合军", "刘备军", "联合军", "曹操军",
            "关羽军", "刘备军", "刘备军", "诸葛亮军",
            "诸葛亮军", "孙权军", "孙权军", "诸葛亮军"
    };

    //敌军
    public static String[] Maps_Select_B = {
            "黄巾军", "董卓军", "刘表军", "张绣军",
            "联合军", "袁绍军", "曹操军", "曹操军",
            "曹操军", "刘璋军", "曹操军", "孙权军",
            "孙权军", "曹操军", "孙权军", "南蛮军",
            "司马懿军", "司马懿军", "司马懿军", "司马懿军"
    };

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
