package com.hzw.shinsangokumusou.maps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hzw.shinsangokumusou.staticvalue.MapsValue;

/**
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.maps/DrawMap
 * Created by HZW
 * Data 2017/12/3
 * Time 21:26
 */

public class HJmap extends View {


    private double nLenStart = 0.0;
    private float oldDist;
    private  int[][] Maps = new int[72][90];

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

        for (int i = 0; i < 72; i++) {
            for (int j = 0; j < 90; j++) {
                Maps[i][j] = 0;
            }
        }

        DrawMap(Maps, canvas, 1,1, 60,3, MapsValue.Glass, "Glass");
        DrawMap(Maps, canvas, 1,3, 3,20, MapsValue.Glass, "Glass");
        DrawMap(Maps, canvas, 1,25, 3,40, MapsValue.Glass, "Glass");
        DrawMap(Maps, canvas, 1,45, 3,88, MapsValue.Glass, "Glass");
        DrawMap(Maps, canvas, 7,85, 55,90, MapsValue.Glass, "Glass");
        DrawMap(Maps, canvas, 57,85, 72,90, MapsValue.Glass, "Glass");
        DrawMap(Maps, canvas, 55,15, 72,70, MapsValue.Glass, "Glass");
        DrawMap(Maps, canvas, 70,72, 72,85, MapsValue.Glass, "Glass");

        DrawMap(Maps, canvas, 62,1, 72,3, MapsValue.Water, "Water");
        DrawMap(Maps, canvas, 1,24, 10,25, MapsValue.Water, "Water");
        DrawMap(Maps, canvas, 12,24, 36,25, MapsValue.Water, "Water");
        DrawMap(Maps, canvas, 15,11, 52,12, MapsValue.Water, "Water");
        DrawMap(Maps, canvas, 49,9, 66,10, MapsValue.Water, "Water");
        DrawMap(Maps, canvas, 63,6, 66,8, MapsValue.Water, "Water");
        DrawMap(Maps, canvas, 15,11, 16,23, MapsValue.Water, "Water");
        DrawMap(Maps, canvas, 35,13, 36,23, MapsValue.Water, "Water");

        DrawMap(Maps, canvas, 57,20, 65,36, MapsValue.Ground, "Ground");
        DrawMap(Maps, canvas, 66,35, 72,37, MapsValue.Ground, "Ground");
        DrawMap(Maps, canvas, 68,38, 68,45, MapsValue.Ground, "Ground");
        DrawMap(Maps, canvas, 55,45, 67,45, MapsValue.Ground, "Ground");
        DrawMap(Maps, canvas, 55,46, 60,70, MapsValue.Ground, "Ground");

        DrawMap(Maps, canvas, 20,20, 50,20, MapsValue.Wall, "Wall");
        DrawMap(Maps, canvas, 20,65, 50,65, MapsValue.Wall, "Wall");
        DrawMap(Maps, canvas, 20,20, 20,65, MapsValue.Wall, "Wall");
        DrawMap(Maps, canvas, 50,20, 50,65, MapsValue.Wall, "Wall");

        DrawMap(Maps, canvas, 36,45, 37,46, Color.RED, "Wall");

        Paint p = new Paint();
        p.setColor(Color.BLACK);
        Log.d("www", MapsValue.getMap_height()+"");
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        Log.d("ppp", "onMeasure: "+ MapsValue.getMap_width()+" , "+ MapsValue.getMap_height());
        setMeasuredDimension(MapsValue.getMap_width(), MapsValue.getMap_height());
    }

    public void DrawMap(int[][] ints, Canvas canvas, int start_X, int start_Y, int stop_X, int stop_Y, int color, String str_color){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect((float)(start_X - 1)* MapsValue.Eachmap, (float)(start_Y - 1)* MapsValue.Eachmap, (float)stop_X* MapsValue.Eachmap, (float)stop_Y* MapsValue.Eachmap, paint);

        if (!str_color.equals("Ground")){

            Log.d("zzz", "地形: "+str_color);
            Log.d("ggg", " for (int i = "+(start_X - 1)+"; i < "+stop_X+"; i++)" +
                    "                 for (int j = "+(start_Y - 1)+"; j < "+stop_Y+"; j++)");

            for (int i = start_X - 1; i < stop_X ; i++) {
                for (int j = start_Y - 1; j < stop_Y ; j++) {
                    ints[i][j] = 1;
                    if (str_color.equals("Glass")){
                        Log.d("zzz", "草地： "+"ints["+i+"]["+j+"]被置为1");
                    }else if (str_color.equals("Wall")){
                        Log.d("zzz", "城墙： "+"ints["+i+"]["+j+"]被置为1");
                    }else if (str_color.equals("Water")){
                        Log.d("zzz", "河流： "+"ints["+i+"]["+j+"]被置为1");
                    }
                }
            }
        }
        
    }

    public int[][] getMaps() {
        return Maps;
    }
}
