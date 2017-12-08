package com.hzw.shinsangokumusou.maps;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.maps/BaseMap
 * Created by HZW
 * Data 2017/12/8
 * Time 17:54
 */

public abstract class BaseMap extends View{

    public BaseMap(Context context) {
        super(context);
    }

    public BaseMap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseMap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void init(Canvas canvas);

    public abstract int[][] getMaps();
}
