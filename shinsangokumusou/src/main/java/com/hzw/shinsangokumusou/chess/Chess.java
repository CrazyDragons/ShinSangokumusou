package com.hzw.shinsangokumusou.chess;

import android.content.Context;
import android.view.View;

/**
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.chess/Chess
 * Created by HZW
 * Data 2017/12/6
 * Time 21:38
 */

public abstract class Chess extends View {

    public Chess(Context context) {
        super(context);
    }

    public abstract void SetPosition(int X, int Y);
}
