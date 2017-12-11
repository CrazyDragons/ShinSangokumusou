package com.hzw.shinsangokumusou.maps;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.hzw.shinsangokumusou.R;
import com.hzw.shinsangokumusou.chess.Chess;
import com.hzw.shinsangokumusou.chess.allchess.General;
import com.hzw.shinsangokumusou.chess.allchess.Player;
import com.hzw.shinsangokumusou.diaplay.BaseDisplay;
import com.hzw.shinsangokumusou.maps.allmap.HJmap;
import com.hzw.shinsangokumusou.music.BGM;
import com.hzw.shinsangokumusou.staticvalue.MapsValue;
import com.hzw.shinsangokumusou.utils.LogUtil;
import com.hzw.shinsangokumusou.utils.MapsUtils;
import com.hzw.shinsangokumusou.utils.ToastUtil;

import java.util.Timer;

import static com.hzw.shinsangokumusou.chess.Chess.getRad;
import static com.hzw.shinsangokumusou.utils.MapsUtils.GetPosition;

public class Maps extends BaseDisplay implements View.OnTouchListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private RelativeLayout mapRL;
    private HJmap mapview;
    private ScrollView scrollView;
    private HorizontalScrollView horizontalScrollView;
    private RelativeLayout relativeLayout;
    private RelativeLayout listView;
    private General chessView;
    private Player playerview;
    private SeekBar seekBar;
    private float oldDist;
    private int TouthCnt;
    private float multiple = 1;
    private Handler handler;
    private int[][] maps;
    MediaPlayer mediaPlayer;
    BGM BGM = new BGM(mediaPlayer);

    private float oldW, oldH, newW = 0, newH = 0;
    private Timer timer;
    boolean change = false;
    private float rad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        init();
    }

    public void init() {

        scrollView = (ScrollView) findViewById(R.id.sv);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv);
        mapRL = (RelativeLayout) findViewById(R.id.mapview);
        relativeLayout = (RelativeLayout) findViewById(R.id.messageRL);
        listView = (RelativeLayout) findViewById(R.id.messageLV);
        seekBar = (SeekBar) findViewById(R.id.seekbar);

        mapview = new HJmap(this);
        chessView = new General(this);
        LogUtil.args_1("xxx", "完成(在map初始化)： ", chessView.isComplete());
        playerview = new Player(this);

        mapview.setId(R.id.HJ_Map);

        maps = mapview.getMaps();

        mapRL.addView(mapview);
        mapRL.addView(chessView);

        mapview.setBackgroundColor(MapsValue.Ground);
        chessView.SetPlayerPosition(5, 6, 0, multiple);

        mapview.setOnTouchListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        relativeLayout.setOnClickListener(this);
        listView.setOnClickListener(this);

        BGM.playBGM(Maps.this, R.raw.music_hj_map);

    }

    /**
     * 设置缩放
     *
     * @param mapview  地图
     * @param chess    棋
     * @param multiple 倍率
     */
    public void SetZoom(HJmap mapview, Chess chess, float multiple) {

        /******* 注意：这里一定不要把1080， 1350替换成MapsValue.getMap_width()，MapsValue.getMap_height()*******/
        MapsValue.setMap_width((int) (1080 * multiple));
        MapsValue.setMap_height((int) (1350 * multiple));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                (int) (MapsValue.getMap_width()),
                (int) (MapsValue.getMap_height()));
        mapview.setLayoutParams(params);

        LogUtil.args_4("test", "srcoll宽： ", scrollView.getWidth(), " scroll高： ", scrollView.getHeight(),
                "\nmap宽： ", MapsValue.getMap_width(), "map高：", MapsValue.getMap_height());

        mapview.setScaleX(multiple);
        mapview.setScaleY(multiple);
        mapview.setPivotX(0);
        mapview.setPivotY(0);
        mapview.invalidate();

        if (newW ==0 && newH == 0){
            chess.SetGeneralPosition(5, 6, 0, multiple);
        }else {
            chess.SetGeneralPosition(GetPosition(newW), GetPosition(newH), 0, multiple);
        }
        LogUtil.args_2("ggg", "X： ", chess.getX(), " ，Y：", chess.getY());

        chess.invalidate();

        handler = new Handler();
        handler.postDelayed(runnable, 200);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.messageRL:
                SetZoom(mapview, playerview, 1f);
                multiple = 1;
                break;
            case R.id.messageLV:
                SetZoom(mapview, playerview, 8f);
                multiple = 8;
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()){

            case R.id.HJ_Map:
                int nCnt = motionEvent.getPointerCount();

                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {


                    case MotionEvent.ACTION_UP:

                        ToastUtil.args_4(Maps.this,
                                "", ToastUtil.X_Y("点击", motionEvent.getX(), motionEvent.getY()),
                                "", ToastUtil.X_Y("数组", GetPosition(motionEvent.getX() - 1), GetPosition(motionEvent.getY())),
                                "倍数:   ", multiple,
                                "该位置参数:   ", maps[GetPosition(motionEvent.getX()) - 1][GetPosition(motionEvent.getY()) - 1]);

                        if (!chessView.isMoving() && !chessView.isComplete()) {
                            newW = motionEvent.getX();
                            newH = motionEvent.getY();
                            chessView.setTranslationX(newW * multiple);
                            chessView.setTranslationY(newH * multiple);
                            chessView.setTranslationY(motionEvent.getY());
                            rad = getRad((newW - oldW), (newH - oldH));
                            chessView.setRotation(0);
                            chessView.SetGeneralPosition(GetPosition(motionEvent.getX() - 1), GetPosition(motionEvent.getY()), 0, multiple);
                            chessView.invalidate();
                            /*Log.d(TAG, "确认位置 :（"+newW+" , "+newH+"）, 旋转了 "+rad+" 度");
                            Log.d(TAG, "位置增量 :（"+(newW - oldW)+" , "+(newH - oldH)+"）");
                            timer.cancel();*/
                            chessView.setVisibility(View.VISIBLE);
                            chessView.setComplete(true);

                        }else {
                            Toast.makeText(Maps.this, "请点击旗子", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case MotionEvent.ACTION_DOWN:
                        chessView.setMoving(false);
                        break;

                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = MapsUtils.Spacing(motionEvent);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        /*if (nCnt == 2){
                            float newDist = Spacing(motionEvent);
                            if (newDist > oldDist + 1) {
                                oldDist = newDist;
                                multiple *= 2;
                                if (multiple >= 1 && multiple <=8){
                                    LogUtil.d("ppp", "倍数： "+multiple);
                                    SET(mapview, chessView, multiple);
                                }else {
                                    LogUtil.d("xxx", "onTouch: ");
                                }
                            }
                            if (newDist < oldDist - 1) {
                                oldDist = newDist;
                                multiple /= 2;
                                if (multiple >= 1 && multiple <=8){
                                    LogUtil.d("ppp", "倍数： "+multiple);
                                    SET(mapview, chessView, multiple);
                                }else {
                                    LogUtil.d("xxx", "onTouch: ");
                                }
                            }
                        }*/
                        break;
                    default:
                        break;
                }
                break;
        }
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.seekbar:
                //滑动条放大地图1-8倍
                multiple = 1 + 0.07f * i;
                SetZoom(mapview, chessView, multiple);
                break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        BGM.pauseBGM();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        BGM.playBGM(Maps.this, R.raw.music_hj_map);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BGM.stopBGM();
    }

    @Override
    protected void onStop() {
        super.onStop();
        BGM.stopBGM();
    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            //每次缩放视图置于屏幕中心
            if (multiple != 1) {
                /******* 注意：这里一定不要把1080， 1350替换成MapsValue.getMap_width()，MapsValue.getMap_height()*******/
                scrollView.scrollTo(0, (int) (1350 * multiple * 0.5 * (1 - 1 / multiple)));
                horizontalScrollView.scrollTo((int) (1080 * multiple * 0.5 * (1 - 1 / multiple)), 0);
            }
        }
    };
}
