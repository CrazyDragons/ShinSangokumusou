package com.hzw.shinsangokumusou.chess.allchess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.hzw.shinsangokumusou.chess.Chess;
import com.hzw.shinsangokumusou.staticvalue.MapsValue;

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
            path.moveTo(0, MapsValue.Eachmap);
            path.lineTo(MapsValue.Eachmap, MapsValue.Eachmap);
            path.lineTo(MapsValue.Eachmap, 0);
            path.lineTo(2 * MapsValue.Eachmap, 0);
            path.lineTo(2 * MapsValue.Eachmap, MapsValue.Eachmap);
            path.lineTo(3 * MapsValue.Eachmap, MapsValue.Eachmap);
            path.lineTo(3 * MapsValue.Eachmap, 2 * MapsValue.Eachmap);
            path.lineTo(0, 2 * MapsValue.Eachmap);
            path.close();
        }else if (rad == 90){
            path.moveTo(MapsValue.Eachmap, 0);
            path.lineTo(2 * MapsValue.Eachmap, 0);
            path.lineTo(2 * MapsValue.Eachmap, MapsValue.Eachmap);
            path.lineTo(3 * MapsValue.Eachmap, MapsValue.Eachmap);
            path.lineTo(3 * MapsValue.Eachmap, 2 * MapsValue.Eachmap);
            path.lineTo(2 * MapsValue.Eachmap, 2 * MapsValue.Eachmap);
            path.lineTo(2 * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.lineTo(MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.close();
        }else if (rad == 180){
            path.moveTo(0, MapsValue.Eachmap);
            path.lineTo(3 * MapsValue.Eachmap, MapsValue.Eachmap);
            path.lineTo(3 * MapsValue.Eachmap, 2 * MapsValue.Eachmap);
            path.lineTo(2 * MapsValue.Eachmap, 2 * MapsValue.Eachmap);
            path.lineTo(2 * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.lineTo(MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.lineTo(MapsValue.Eachmap, 2 * MapsValue.Eachmap);
            path.lineTo(0, 2 * MapsValue.Eachmap);
            path.close();
        }else if (rad == 270){
            path.moveTo(0, MapsValue.Eachmap);
            path.lineTo(MapsValue.Eachmap, MapsValue.Eachmap);
            path.lineTo(MapsValue.Eachmap, 0);
            path.lineTo(2 * MapsValue.Eachmap, 0);
            path.lineTo(2 * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.lineTo(MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.lineTo(MapsValue.Eachmap, 2 * MapsValue.Eachmap);
            path.lineTo(0, 2 * MapsValue.Eachmap);
            path.close();
        }else if (rad == 45){
            path.moveTo(0, 0.705f * MapsValue.Eachmap);
            path.lineTo(0.705f * MapsValue.Eachmap, 0);
            path.lineTo(1.5f * MapsValue.Eachmap, 0.795f * MapsValue.Eachmap);
            path.lineTo(2.295f * MapsValue.Eachmap, 0);
            path.lineTo(3 * MapsValue.Eachmap, 0.705f * MapsValue.Eachmap);
            path.lineTo(2.205f * MapsValue.Eachmap, 1.5f * MapsValue.Eachmap);
            path.lineTo(3 * MapsValue.Eachmap, 2.295f * MapsValue.Eachmap);
            path.lineTo(2.295f * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.close();
        }else if (rad == 135){
            path.moveTo(0, 2.295f * MapsValue.Eachmap);
            path.lineTo(2.295f * MapsValue.Eachmap, 0);
            path.lineTo(3 * MapsValue.Eachmap, 0.705f * MapsValue.Eachmap);
            path.lineTo(2.205f * MapsValue.Eachmap, 1.5f * MapsValue.Eachmap);
            path.lineTo(3 * MapsValue.Eachmap, 2.295f * MapsValue.Eachmap);
            path.lineTo(2.295f * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.lineTo(1.5f * MapsValue.Eachmap, 2.205f * MapsValue.Eachmap);
            path.lineTo(0.705f * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.close();
        }else if (rad == 225){
            path.moveTo(0, 2.295f * MapsValue.Eachmap);
            path.lineTo(0.795f * MapsValue.Eachmap, 1.5f * MapsValue.Eachmap);
            path.lineTo(0, 0.705f * MapsValue.Eachmap);
            path.lineTo(0.705f * MapsValue.Eachmap, 0);
            path.lineTo(3 * MapsValue.Eachmap, 2.295f * MapsValue.Eachmap);
            path.lineTo(2.295f * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.lineTo(1.5f * MapsValue.Eachmap, 2.205f * MapsValue.Eachmap);
            path.lineTo(0.705f * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.close();
        }else if (rad == 315){
            path.moveTo(0, 0.705f * MapsValue.Eachmap);
            path.lineTo(0.705f * MapsValue.Eachmap, 0);
            path.lineTo(1.5f * MapsValue.Eachmap, 0.795f * MapsValue.Eachmap);
            path.lineTo(2.295f * MapsValue.Eachmap, 0);
            path.lineTo(3 * MapsValue.Eachmap, 0.705f * MapsValue.Eachmap);
            path.lineTo(0.705f * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.lineTo(0, 2.295f * MapsValue.Eachmap);
            path.lineTo(0.795f * MapsValue.Eachmap, 1.5f * MapsValue.Eachmap);
            path.close();
        }

        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(3 * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
    }

    public float getRad() {
        return rad;
    }

    public void setRad(float rad) {
        this.rad = rad;
    }

}
