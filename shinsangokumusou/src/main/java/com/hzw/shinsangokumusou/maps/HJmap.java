package com.hzw.shinsangokumusou.maps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hzw.shinsangokumusou.staticvalue.MapsValue;
import com.hzw.shinsangokumusou.utils.MapsUtils;

/**
 * 黄巾之乱地图
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.maps/MapsUtils.DrawRect
 * Created by HZW
 * Data 2017/12/3
 * Time 21:26
 */

public class HJmap extends View {

    private  int[][] Maps = new int[MapsValue.MAX_X][MapsValue.MAX_Y];

    public HJmap(Context context) {
        super(context);
    }

    public HJmap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HJmap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        init(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(MapsValue.getMap_width(), MapsValue.getMap_height());
    }

    public void init(Canvas canvas){
        //初始化全图
        MapsUtils.initMap(Maps);
        //画网格
        MapsUtils.DrawGrid(canvas);
        //画地图
        DrawMap(canvas);
    }

    public int[][] getMaps() {
        return Maps;
    }

    public void DrawMap(Canvas canvas){
        //画草地部分
        MapsUtils.DrawRect(Maps, canvas, 1,1, 60,3, MapsValue.Glass, "Glass");
        MapsUtils.DrawRect(Maps, canvas, 1,3, 3,20, MapsValue.Glass, "Glass");
        MapsUtils.DrawRect(Maps, canvas, 1,25, 3,40, MapsValue.Glass, "Glass");
        MapsUtils.DrawRect(Maps, canvas, 1,45, 3,88, MapsValue.Glass, "Glass");
        MapsUtils.DrawRect(Maps, canvas, 7,85, 55,90, MapsValue.Glass, "Glass");
        MapsUtils.DrawRect(Maps, canvas, 57,85, 72,90, MapsValue.Glass, "Glass");
        MapsUtils.DrawRect(Maps, canvas, 55,15, 72,70, MapsValue.Glass, "Glass");
        MapsUtils.DrawRect(Maps, canvas, 70,72, 72,85, MapsValue.Glass, "Glass");

        //画河流部分
        MapsUtils.DrawRect(Maps, canvas, 62,1, 72,3, MapsValue.Water, "Water");
        MapsUtils.DrawRect(Maps, canvas, 1,24, 10,25, MapsValue.Water, "Water");
        MapsUtils.DrawRect(Maps, canvas, 12,24, 36,25, MapsValue.Water, "Water");
        MapsUtils.DrawRect(Maps, canvas, 15,11, 52,12, MapsValue.Water, "Water");
        MapsUtils.DrawRect(Maps, canvas, 49,9, 66,10, MapsValue.Water, "Water");
        MapsUtils.DrawRect(Maps, canvas, 63,6, 66,8, MapsValue.Water, "Water");
        MapsUtils.DrawRect(Maps, canvas, 15,11, 16,23, MapsValue.Water, "Water");
        MapsUtils.DrawRect(Maps, canvas, 35,13, 36,23, MapsValue.Water, "Water");

        //画城墙部分
        MapsUtils.DrawRect(Maps, canvas, 20,20, 50,20, MapsValue.Wall, "Wall");
        MapsUtils.DrawRect(Maps, canvas, 20,65, 50,65, MapsValue.Wall, "Wall");
        MapsUtils.DrawRect(Maps, canvas, 20,20, 20,65, MapsValue.Wall, "Wall");
        MapsUtils.DrawRect(Maps, canvas, 50,20, 50,65, MapsValue.Wall, "Wall");

        //中心点
        MapsUtils.DrawRect(Maps, canvas, 36,45, 37,46, Color.RED, "Wall");

        //填充平地
        MapsUtils.DrawRect(Maps, canvas, 57,20, 65,36, MapsValue.Ground, "Ground");
        MapsUtils.DrawRect(Maps, canvas, 66,35, 72,37, MapsValue.Ground, "Ground");
        MapsUtils.DrawRect(Maps, canvas, 68,38, 68,45, MapsValue.Ground, "Ground");
        MapsUtils.DrawRect(Maps, canvas, 55,45, 67,45, MapsValue.Ground, "Ground");
        MapsUtils.DrawRect(Maps, canvas, 55,46, 60,70, MapsValue.Ground, "Ground");
    }


}
