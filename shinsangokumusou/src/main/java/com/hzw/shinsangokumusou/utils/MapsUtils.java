package com.hzw.shinsangokumusou.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.hzw.shinsangokumusou.staticvalue.MapsValue;

/**
 * 地图工具
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.utils/MapsUtils
 * Created by HZW
 * Data 2017/12/8
 * Time 15:14
 */

public class MapsUtils {

    /**
     * 初始化全图二维数组置0
     * @param Maps 地图二维数组
     */
    public static void initMap(int[][] Maps){

        for (int i = 0; i < MapsValue.MAX_X; i++) {
            for (int j = 0; j < MapsValue.MAX_Y; j++) {
                Maps[i][j] = 0;
            }
        }
    }

    /**
     * 画网格
     * @param canvas canvas
     */
    public static void DrawGrid(Canvas canvas){
        Paint p = new Paint();
        p.setColor(Color.BLACK);

        //当倍率大于3才显示网格
        if ((MapsValue.getMap_height()/1350) >= 3){
            p.setStrokeWidth(1);
            for (int i = 0; i <= 100 ; i++) {
                canvas.drawLine(0, MapsValue.Eachmap*i, MapsValue.Map_width, MapsValue.Eachmap*i, p);
            }
            for (int i = 0; i <= 100; i++) {
                canvas.drawLine(MapsValue.Eachmap*i, 0, MapsValue.Eachmap*i, MapsValue.Map_height, p);
            }
        }
    }

    /**
     * 画矩形地图
     * @param ints 二维数组
     * @param canvas canvas
     * @param start_X 左上
     * @param start_Y 右上
     * @param stop_X 左下
     * @param stop_Y 右下
     * @param color 地图颜色
     * @param str_color 地图颜色（文字）
     */
    public static void DrawRect(int[][] ints, Canvas canvas, int start_X, int start_Y, int stop_X, int stop_Y, int color, String str_color){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect((float)(start_X - 1)* MapsValue.Eachmap, (float)(start_Y - 1)* MapsValue.Eachmap, (float)stop_X* MapsValue.Eachmap, (float)stop_Y* MapsValue.Eachmap, paint);

        //平地部分置0
        Set_Ground_For_1(str_color, start_X, stop_X, start_Y, stop_Y, ints);

    }

    /**
     * 平地置为1
     * @param str_color 地图颜色
     * @param start_X 左上点
     * @param stop_X 右上点
     * @param start_Y 左下点
     * @param stop_Y 右下点
     * @param ints 二维数组
     */
    private static void Set_Ground_For_1(String str_color, int start_X, int stop_X, int start_Y, int stop_Y, int[][] ints){
        if (!str_color.equals("Ground")){
            for (int i = start_X - 1; i < stop_X ; i++) {
                for (int j = start_Y - 1; j < stop_Y ; j++) {
                    ints[i][j] = 1;
                }
            }
        }else {
            for (int i = start_X - 1; i < stop_X ; i++) {
                for (int j = start_Y - 1; j < stop_Y ; j++) {
                    ints[i][j] = 0;
                }
            }
        }
    }

    /**
     * 获取在棋盘上位置
     * @param position 实际在屏幕上位置
     * @return 棋盘位置（单轴）
     */
    public static int GetPosition(float position) {
        return (int) Math.ceil(position / 15);
    }

    /**
     * 计算两点距离
     * @param event 点击事件
     * @return 距离
     */
     public static float Spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

}
