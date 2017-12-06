package com.hzw.shinsangokumusou.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.utils/ToastUtil
 * Created by HZW
 * Data 2017/12/7
 * Time 1:10
 */

public class ToastUtil {

    /*
    参数0-5的Toast
     */

    public static void X0(Context context, String s1){
        Toast.makeText(context, s1, Toast.LENGTH_SHORT).show();
    }

    public static void X1(Context context, String s1, Object o1){
        Toast.makeText(context, s1+o1, Toast.LENGTH_SHORT).show();
    }

    public static void X2(Context context, String s1, Object o1, String s2, Object o2){
        Toast.makeText(context, s1+o1+("\n"+s2+o2), Toast.LENGTH_SHORT).show();
    }

    public static void X3(Context context, String s1, Object o1, String s2, Object o2, String s3, Object o3){
        Toast.makeText(context, s1+o1+("\n"+s2+o2)+("\n"+s3+o3), Toast.LENGTH_SHORT).show();
    }

    public static void X4(Context context, String s1, Object o1, String s2, Object o2, String s3, Object o3, String s4, Object o4){
        Toast.makeText(context, s1+o1+("\n"+s2+o2)+("\n"+s3+o3)+("\n"+s4+o4), Toast.LENGTH_SHORT).show();
    }

    public static void X5(Context context, String s1, Object o1, String s2, Object o2, String s3, Object o3, String s4, Object o4, String s5, Object o5){
        Toast.makeText(context, s1+o1+("\n"+s2+o2)+("\n"+s3+o3)+("\n"+s4+o4)+("\n"+s5+o5), Toast.LENGTH_SHORT).show();
    }

    /*
    XY位置模板
     */
    public static String X_Y(String content, Object x, Object y){
        return content+"位置： ( "+x+" ，"+y+" )";
    }
}
