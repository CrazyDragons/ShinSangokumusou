package com.hzw.shinsangokumusou.chess.allchess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.hzw.shinsangokumusou.R;
import com.hzw.shinsangokumusou.chess.Chess;
import com.hzw.shinsangokumusou.staticvalue.MapsValue;

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
            path.moveTo(0.5f * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.lineTo(1.5f * MapsValue.Eachmap, 0);
            path.lineTo(2.5f * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.close();
        }else if (rad == 90){
            path.moveTo(0, 0.5f * MapsValue.Eachmap);
            path.lineTo(3 * MapsValue.Eachmap, 1.5f * MapsValue.Eachmap);
            path.lineTo(0, 2.5f * MapsValue.Eachmap);
            path.close();
        }else if (rad == 180){
            path.moveTo(0.5f * MapsValue.Eachmap, 0);
            path.lineTo(1.5f * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.lineTo(2.5f * MapsValue.Eachmap, 0);
            path.close();
        }else if (rad == 270){
            path.moveTo(0, 1.5f * MapsValue.Eachmap);
            path.lineTo(3 * MapsValue.Eachmap, 2.5f * MapsValue.Eachmap);
            path.lineTo(3 * MapsValue.Eachmap, 0.5f * MapsValue.Eachmap);
            path.close();
        }else if (rad == 45){
            path.moveTo(0, 1.585f * MapsValue.Eachmap);
            path.lineTo(3 * MapsValue.Eachmap, 0);
            path.lineTo(1.415f * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.close();
        }else if (rad == 135){
            path.moveTo(0, 1.415f * MapsValue.Eachmap);
            path.lineTo(1.415f * MapsValue.Eachmap, 0);
            path.lineTo(3 * MapsValue.Eachmap, 3 * MapsValue.Eachmap  );
            path.close();
        }else if (rad == 225){
            path.moveTo(0, 3 * MapsValue.Eachmap);
            path.lineTo(1.585f * MapsValue.Eachmap, 0);
            path.lineTo(3 * MapsValue.Eachmap, 1.415f * MapsValue.Eachmap);
            path.close();
        }else if (rad == 315){
            path.moveTo(0, 0);
            path.lineTo(1.585f * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
            path.lineTo(3 * MapsValue.Eachmap, 1.585f * MapsValue.Eachmap);
            path.close();
        }
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(3 * MapsValue.Eachmap, 3 * MapsValue.Eachmap);
    }

    public void setRad(float rad) {
        this.rad = rad;
    }

    public float getRad() {
        return rad;
    }
}
