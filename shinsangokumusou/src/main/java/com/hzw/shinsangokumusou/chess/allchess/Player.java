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
            path.moveTo(10f, 60);
            path.lineTo(30f, 0);
            path.lineTo(50f, 60);
            path.close();
        }else if (rad == 90){
            path.moveTo(0, 10f);
            path.lineTo(60, 30f);
            path.lineTo(0, 50f);
            path.close();
        }else if (rad == 180){
            path.moveTo(10f, 0);
            path.lineTo(30f, 60);
            path.lineTo(50f, 0);
            path.close();
        }else if (rad == 270){
            path.moveTo(0, 30f);
            path.lineTo(60, 50f);
            path.lineTo(60, 10f);
            path.close();
        }else if (rad == 45){
            path.moveTo(0, 31.7f);
            path.lineTo(60, 0);
            path.lineTo(28.3f, 60);
            path.close();
        }else if (rad == 135){
            path.moveTo(0, 28.3f);
            path.lineTo(28.3f, 0);
            path.lineTo(60, 60  );
            path.close();
        }else if (rad == 225){
            path.moveTo(0, 60);
            path.lineTo(31.7f, 0);
            path.lineTo(60, 28.3f);
            path.close();
        }else if (rad == 315){
            path.moveTo(0, 0);
            path.lineTo(31.7f, 60);
            path.lineTo(60, 31.7f);
            path.close();
        }
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(60, 60);
    }

    public void setRad(float rad) {
        this.rad = rad;
    }

    public float getRad() {
        return rad;
    }
}
