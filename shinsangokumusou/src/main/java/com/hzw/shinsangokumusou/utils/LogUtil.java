package com.hzw.shinsangokumusou.utils;

import android.util.Log;

/**
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.utils/LogUtil
 * Created by HZW
 * Data 2017/12/7
 * Time 0:49
 */

public class LogUtil {

    public static void args_0(String tag, String s1){
        Log.d(tag, s1);
    }

    public static void args_1(String tag, String s1, Object o1){
        Log.d(tag, s1+o1);
    }

    public static void args_2(String tag, String s1, Object o1, String s2, Object o2){
        Log.d(tag, s1+o1+s2+o2);
    }

    public static void args_3(String tag, String s1, Object o1, String s2, Object o2, String s3, Object o3){
        Log.d(tag, s1+o1+s2+o2+s3+o3);
    }

    public static void args_4(String tag, String s1, Object o1, String s2, Object o2, String s3, Object o3, String s4, Object o4){
        Log.d(tag, s1+o1+s2+o2+s3+o3+s4+o4);
    }

    public static void args_5(String tag, String s1, Object o1, String s2, Object o2, String s3, Object o3, String s4, Object o4, String s5, Object o5){
        Log.d(tag, s1+o1+s2+o2+s3+o3+s4+o4+s5+o5);
    }

    /*
    args_Y位置模板
     */
    public static String X_Y(String content, Object x, Object y){
        return content+"位置： ( "+x+" ，"+y+" )";
    }
}
