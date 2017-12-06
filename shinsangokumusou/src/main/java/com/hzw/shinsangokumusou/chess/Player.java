package com.hzw.shinsangokumusou.chess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.hzw.shinsangokumusou.R;

/**
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.chess/Player
 * Created by HZW
 * Data 2017/12/6
 * Time 20:20
 */

public class Player extends Chess {


    public Player(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        Path path = new Path();
        paint.setColor(getResources().getColor(R.color.player_color));
        paint.setStyle(Paint.Style.FILL);
        path.moveTo(0, 45);
        path.lineTo(22.5f, 0);
        path.lineTo(45, 45);
        path.close();
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(45, 45);
    }
}
