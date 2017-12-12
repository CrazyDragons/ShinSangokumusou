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
            path.moveTo(0, 15);
            path.lineTo(15, 15);
            path.lineTo(15, 0);
            path.lineTo(30, 0);
            path.lineTo(30, 15);
            path.lineTo(45, 15);
            path.lineTo(45, 30);
            path.lineTo(0, 30);
            path.close();
        }else if (rad == 90){
            path.moveTo(15, 0);
            path.lineTo(30, 0);
            path.lineTo(30, 15);
            path.lineTo(45, 15);
            path.lineTo(45, 30);
            path.lineTo(30, 30);
            path.lineTo(30, 45);
            path.lineTo(15, 45);
            path.close();
        }else if (rad == 180){
            path.moveTo(0, 15);
            path.lineTo(45, 15);
            path.lineTo(45, 30);
            path.lineTo(30, 30);
            path.lineTo(30, 45);
            path.lineTo(15, 45);
            path.lineTo(15, 30);
            path.lineTo(0, 30);
            path.close();
        }else if (rad == 270){
            path.moveTo(0, 15);
            path.lineTo(15, 15);
            path.lineTo(15, 0);
            path.lineTo(30, 0);
            path.lineTo(30, 45);
            path.lineTo(15, 45);
            path.lineTo(15, 30);
            path.lineTo(0, 30);
            path.close();
        }else if (rad == 45){
            path.moveTo(0, 10.6f);
            path.lineTo(10.6f, 0);
            path.lineTo(22.5f, 11.9f);
            path.lineTo(34.4f, 0);
            path.lineTo(45, 10.6f);
            path.lineTo(33.1f, 22.5f);
            path.lineTo(45, 34.4f);
            path.lineTo(34.4f, 45);
            path.close();
        }else if (rad == 135){
            path.moveTo(0, 34.4f);
            path.lineTo(34.4f, 0);
            path.lineTo(45, 10.6f);
            path.lineTo(33.1f, 22.5f);
            path.lineTo(45, 34.4f);
            path.lineTo(34.4f, 45);
            path.lineTo(22.5f, 33.1f);
            path.lineTo(10.6f, 45);
            path.close();
        }else if (rad == 225){
            path.moveTo(0, 34.4f);
            path.lineTo(11.9f, 22.5f);
            path.lineTo(0, 10.6f);
            path.lineTo(10.6f, 0);
            path.lineTo(45, 34.4f);
            path.lineTo(34.4f, 45);
            path.lineTo(22.5f, 33.1f);
            path.lineTo(10.6f, 45);
            path.close();
        }else if (rad == 315){
            path.moveTo(0, 10.6f);
            path.lineTo(10.6f, 0);
            path.lineTo(22.5f, 11.9f);
            path.lineTo(34.4f, 0);
            path.lineTo(45, 10.6f);
            path.lineTo(10.6f, 45);
            path.lineTo(0, 34.4f);
            path.lineTo(11.9f, 22.5f);
            path.close();
        }

        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(45, 45);
    }

    public float getRad() {
        return rad;
    }

    public void setRad(float rad) {
        this.rad = rad;
    }

}
