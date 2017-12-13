package com.hzw.shinsangokumusou.chess;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hzw.shinsangokumusou.database.DataBase;
import com.hzw.shinsangokumusou.staticvalue.MapsValue;
import com.hzw.shinsangokumusou.staticvalue.SQLiteValue;
import com.hzw.shinsangokumusou.utils.LogUtil;
import com.hzw.shinsangokumusou.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 棋子的基类
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.chess/Chess
 * Created by HZW
 * Data 2017/12/6
 * Time 21:38
 */

public abstract class Chess extends View {

    private Timer timer;
    private TimerTask timerTask;
    private boolean Moving = false;
    private boolean Complete = true;
    private boolean change = false;

    private float oldW;
    private float newW = 0;
    private float oldH;
    private float newH = 0;

    private int name;
    private SQLiteDatabase sqLiteDatabase;
    private int moving = 0;

    public Chess(Context context, int name) {
        super(context);
        this.name = name;
    }

    /**
     * 初始化玩家棋子位置
     *
     * @param X 棋盘X轴位置
     * @param Y 棋盘Y轴位置
     */
    public void SetPlayerPosition(int X, int Y, float multiple) {
        setTranslationX((X - 2) * MapsValue.Eachmap);
        setTranslationY((Y - 2) * MapsValue.Eachmap);
        setScaleX(multiple);
        setScaleY(multiple);
        setPivotX(-MapsValue.Eachmap * (X - 2));
        setPivotY(-MapsValue.Eachmap * (Y - 2));
    }

    /**
     * 初始化玩家将领位置
     *
     * @param X 棋盘X轴位置
     * @param Y 棋盘Y轴位置
     */
    public void SetGeneralPosition(int X, int Y, float multiple) {
        setTranslationX((X - 2) * MapsValue.Eachmap);
        setTranslationY((Y - 2) * MapsValue.Eachmap);
        setScaleX(multiple);
        setScaleY(multiple);
        setPivotX(-MapsValue.Eachmap * (X - 2));
        setPivotY(-MapsValue.Eachmap * (Y - 2));
    }

    // TODO: 2017/12/7 1:57 添加修改二维数组功能


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            //按下棋子开始闪烁
            case MotionEvent.ACTION_DOWN:

                sqLiteDatabase = new DataBase(getContext()).getWritableDatabase();
                Cursor cursor = sqLiteDatabase.rawQuery(SQLiteValue.Query_Chess_Name, null);
                while (cursor.moveToNext()) {
                    moving = cursor.getInt(2);
                }
                cursor.close();

                if (moving == 1) {
                    ToastUtil.args_0(getContext(), "请让棋子落地");
                } else {
                    sqLiteDatabase = new DataBase(getContext()).getWritableDatabase();
                    sqLiteDatabase.execSQL("update test_data set ChessName = " + name + " where id = 1");
                    sqLiteDatabase.execSQL("update test_data set Moving = 1 where id = 1");
                    sqLiteDatabase.close();
                    Log.d("qqq", "已更新: ");

                    if (!Moving) {

                        Complete = false;

                        if (timer != null) {
                            timer.cancel();
                        }
                        timer = new Timer();
                        timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                Message msg = new Message();
                                handler.sendMessage(msg);
                            }
                        };
                        timer.schedule(timerTask, 0, 500);
                    }

                }

                break;

            case MotionEvent.ACTION_UP:

                if (moving == 1){

                }else {
                    oldW = getX() + 45;
                    oldH = getY() + 30;

//                    LogUtil.args_4("ppp", "oldw: ", oldW, " oldh: ", oldH, "\ngetx:", getX(), " gety:", getY());
                    // TODO: 2017/12/12 21:32 测试old位置

                    Moving = true;
                    Complete = false;
                    LogUtil.args_1("xxx", "完成（chess按下）： ", Complete);
                    ToastUtil.args_1(getContext(), "您按的是： ", name);
                }

                break;

            default:
                break;
        }

        return true;
    }

    /**
     * 判断角度
     *
     * @param x
     * @param y
     * @return 旋转角度
     */
    public static float getRad(int x, int y) {
        int F;
        float Rad = 0;
        /*if (x == 0) {
            F = 1;
        }else {
            tan = y / x;

            if (tan >=2.414 || tan < -2.414){
                F = 1;
            }else if (tan >= -0.414 && tan <= 0.414){
                F = 0;
            }else {
                F = 2;
            }
        }*/

        LogUtil.args_2("rrr", ", x: ", x, ", y: ", y);

        if (x == 0 && y > 0) {
            Rad = 0;
        } else if (x > 0 && y > 0) {
            Rad = 45;
        } else if (x > 0 && y == 0) {
            Rad = 90;
        } else if (x > 0 && y < 0) {
            Rad = 135;
        } else if (x == 0 && y < 0) {
            Rad = 180;
        } else if (x < 0 && y < 0) {
            Rad = 225;
        } else if (x < 0 && y == 0) {
            Rad = 270;
        } else if (x < 0 && y > 0) {
            Rad = 315;
        }

        return Rad;
    }

    public void setMoving(boolean moving) {
        Moving = moving;
    }

    public void setComplete(boolean complete) {
        Complete = complete;
    }

    public boolean isMoving() {
        return Moving;
    }

    public boolean isComplete() {
        return Complete;
    }

    public TimerTask getTimerTask() {
        return timerTask;
    }

    public float getOldW() {
        return oldW;
    }

    public float getOldH() {
        return oldH;
    }

    public float getNewW() {
        return newW;
    }

    public void setNewW(float newW) {
        this.newW = newW;
    }

    public float getNewH() {
        return newH;
    }

    public void setNewH(float newH) {
        this.newH = newH;
    }

    public void setOldW(float oldW) {
        this.oldW = oldW;
    }

    public int getName() {
        return name;
    }

    public void setOldH(float oldH) {
        this.oldH = oldH;
    }

    public Timer getTimer() {
        return timer;
    }

    Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            if (!Complete) {
                if (change) {
                    change = false;
                    setVisibility(View.INVISIBLE);
                } else {
                    change = true;
                    setVisibility(View.VISIBLE);
                }
            }
        }
    };
}
