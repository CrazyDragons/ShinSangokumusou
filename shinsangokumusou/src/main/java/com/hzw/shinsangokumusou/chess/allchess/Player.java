package com.hzw.shinsangokumusou.chess.allchess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.hzw.shinsangokumusou.R;
import com.hzw.shinsangokumusou.chess.Chess;

/**
 * 玩家棋绘制
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.chess/Player
 * Created by HZW
 * Data 2017/12/6
 * Time 20:20
 */

public class Player extends Chess {

    public float rad = 0;
    public int name;

    public Player(Context context, int name) {
        super(context, name);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        Path path = new Path();
        paint.setColor(getResources().getColor(R.color.player_color));
        paint.setStyle(Paint.Style.FILL);
        if (rad == 0){
            path.moveTo(7.5f, 45);
            path.lineTo(22.5f, 0);
            path.lineTo(37.5f, 45);
            path.close();
        }else if (rad == 90){
            path.moveTo(0, 7.5f);
            path.lineTo(45, 22.5f);
            path.lineTo(0, 37.5f);
            path.close();
        }else if (rad == 180){
            path.moveTo(7.5f, 0);
            path.lineTo(22.5f, 45);
            path.lineTo(37.5f, 0);
            path.close();
        }else if (rad == 270){
            path.moveTo(0, 22.5f);
            path.lineTo(45, 37.5f);
            path.lineTo(45, 7.5f);
            path.close();
        }else if (rad == 45){
            path.moveTo(0, 23.8f);
            path.lineTo(45, 0);
            path.lineTo(21.2f, 45);
            path.close();
        }else if (rad == 135){
            path.moveTo(0, 21.2f);
            path.lineTo(21.2f, 0);
            path.lineTo(45, 45  );
            path.close();
        }else if (rad == 225){
            path.moveTo(0, 45);
            path.lineTo(23.8f, 0);
            path.lineTo(45, 21.2f);
            path.close();
        }else if (rad == 315){
            path.moveTo(0, 0);
            path.lineTo(23.8f, 45);
            path.lineTo(45, 23.8f);
            path.close();
        }
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(45, 45);
    }

    public void setRad(float rad) {
        this.rad = rad;
    }

    public float getRad() {
        return rad;
    }
}
