package com.hzw.shinsangokumusou.chess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.chess/Chess
 * Created by HZW
 * Data 2017/12/4
 * Time 0:17
 */

public class General extends Chess {

    public General(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Path path = new Path();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        path.moveTo(0, 15);
        path.lineTo(15, 15);
        path.lineTo(15, 0);
        path.lineTo(30, 0);
        path.lineTo(30, 15);
        path.lineTo(45, 15);
        path.lineTo(45, 30);
        path.lineTo(0, 30);
        path.lineTo(0, 30);
        path.close();
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(45, 30);
    }
}
