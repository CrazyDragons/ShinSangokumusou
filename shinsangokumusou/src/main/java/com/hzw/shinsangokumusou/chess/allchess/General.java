package com.hzw.shinsangokumusou.chess.allchess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.hzw.shinsangokumusou.chess.Chess;

/**
 * 将领棋的绘制
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.chess/Chess
 * Created by HZW
 * Data 2017/12/4
 * Time 0:17
 */

public class General extends Chess {

    public float rad = 0;
    public int name;

    public General(Context context, int name) {
        super(context, name);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Path path = new Path();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        if (rad == 0){
            path.moveTo(0, 20);
            path.lineTo(20, 20);
            path.lineTo(20, 0);
            path.lineTo(40, 0);
            path.lineTo(40, 20);
            path.lineTo(60, 20);
            path.lineTo(60, 40);
            path.lineTo(0, 40);
            path.close();
        }else if (rad == 90){
            path.moveTo(20, 0);
            path.lineTo(40, 0);
            path.lineTo(40, 20);
            path.lineTo(60, 20);
            path.lineTo(60, 40);
            path.lineTo(40, 40);
            path.lineTo(40, 60);
            path.lineTo(20, 60);
            path.close();
        }else if (rad == 180){
            path.moveTo(0, 20);
            path.lineTo(60, 20);
            path.lineTo(60, 40);
            path.lineTo(40, 40);
            path.lineTo(40, 60);
            path.lineTo(20, 60);
            path.lineTo(20, 40);
            path.lineTo(0, 40);
            path.close();
        }else if (rad == 270){
            path.moveTo(0, 20);
            path.lineTo(20, 20);
            path.lineTo(20, 0);
            path.lineTo(40, 0);
            path.lineTo(40, 60);
            path.lineTo(20, 60);
            path.lineTo(20, 40);
            path.lineTo(0, 40);
            path.close();
        }else if (rad == 45){
            path.moveTo(0, 14.1f);
            path.lineTo(14.1f, 0);
            path.lineTo(30, 15.9f);
            path.lineTo(45.9f, 0);
            path.lineTo(60, 14.1f);
            path.lineTo(44.1f, 30);
            path.lineTo(60, 45.9f);
            path.lineTo(45.9f, 60);
            path.close();
        }else if (rad == 135){
            path.moveTo(0, 45.9f);
            path.lineTo(45.9f, 0);
            path.lineTo(60, 14.1f);
            path.lineTo(44.1f, 30);
            path.lineTo(60, 45.9f);
            path.lineTo(45.9f, 60);
            path.lineTo(30, 44.1f);
            path.lineTo(14.1f, 60);
            path.close();
        }else if (rad == 225){
            path.moveTo(0, 45.9f);
            path.lineTo(15.9f, 30);
            path.lineTo(0, 14.1f);
            path.lineTo(14.1f, 0);
            path.lineTo(60, 45.9f);
            path.lineTo(45.9f, 60);
            path.lineTo(30, 44.1f);
            path.lineTo(14.1f, 60);
            path.close();
        }else if (rad == 315){
            path.moveTo(0, 14.1f);
            path.lineTo(14.1f, 0);
            path.lineTo(30, 15.9f);
            path.lineTo(45.9f, 0);
            path.lineTo(60, 14.1f);
            path.lineTo(14.1f, 60);
            path.lineTo(0, 45.9f);
            path.lineTo(15.9f, 30);
            path.close();
        }

        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(60, 60);
    }

    public float getRad() {
        return rad;
    }

    public void setRad(float rad) {
        this.rad = rad;
    }

}
