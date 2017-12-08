package com.hzw.shinsangokumusou.maps;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;

import com.hzw.shinsangokumusou.R;
import com.hzw.shinsangokumusou.chess.Chess;
import com.hzw.shinsangokumusou.chess.General;
import com.hzw.shinsangokumusou.chess.Player;
import com.hzw.shinsangokumusou.staticvalue.MapsValue;
import com.hzw.shinsangokumusou.utils.LogUtil;
import com.hzw.shinsangokumusou.utils.MapsUtils;
import com.hzw.shinsangokumusou.utils.ToastUtil;

import static com.hzw.shinsangokumusou.utils.MapsUtils.GetPosition;

public class Maps extends AppCompatActivity implements View.OnTouchListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private RelativeLayout mapRL;
    private HJmap mapview;
    private ScrollView scrollView;
    private HorizontalScrollView horizontalScrollView;
    private RelativeLayout relativeLayout;
    private RelativeLayout listView;
    private General chessview;
    private Player playerview;
    private SeekBar seekBar;
    private float oldDist;
    private int TouthCnt;
    private float multiple = 1;
    private Handler handler;
    private int[][] maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        init();

    }

    public void init() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        scrollView = (ScrollView) findViewById(R.id.sv);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv);
        mapRL = (RelativeLayout) findViewById(R.id.mapview);
        relativeLayout = (RelativeLayout) findViewById(R.id.messageRL);
        listView = (RelativeLayout) findViewById(R.id.messageLV);
        seekBar = (SeekBar) findViewById(R.id.seekbar);

        mapview = new HJmap(this);
        chessview = new General(this);
        playerview = new Player(this);

        mapview.setId(R.id.HJ_Map);

        maps = mapview.getMaps();

        mapRL.addView(mapview);
        mapRL.addView(playerview);

        mapview.setBackgroundColor(MapsValue.Ground);
        SetZoom(mapview, playerview, multiple);

        mapview.setOnTouchListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        relativeLayout.setOnClickListener(this);
        listView.setOnClickListener(this);
    }

    /**
     * 设置缩放
     *
     * @param mapview  地图
     * @param chess    棋
     * @param multiple 倍率
     */
    public void SetZoom(HJmap mapview, Chess chess, float multiple) {
        MapsValue.setMap_width((int) (MapsValue.getMap_width() * multiple));
        MapsValue.setMap_height((int) (MapsValue.getMap_height() * multiple));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(MapsValue.getMap_width(), MapsValue.getMap_height());
        mapview.setLayoutParams(params);

        mapview.setScaleX(multiple);
        mapview.setScaleY(multiple);
        mapview.setPivotX(0);
        mapview.setPivotY(0);
        mapview.invalidate();

        chessview.SetGeneralPosition(2, 2, multiple);
        chess.SetPlayerPosition(5, 6, multiple);

        chess.invalidate();

        handler = new Handler();
        handler.postDelayed(runnable, 200);
    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            //每次缩放视图置于屏幕中心
            if (multiple != 1) {
                scrollView.scrollTo(0, (int) (MapsValue.getMap_height() * multiple * 0.5 * (1 - 1 / multiple)));
                horizontalScrollView.scrollTo((int) (MapsValue.getMap_width() * multiple * 0.5 * (1 - 1 / multiple)), 0);
            }
        }
    };

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
                        break;

                    case MotionEvent.ACTION_DOWN:
                        mapview.performClick();
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
                                    SET(mapview, chessview, multiple);
                                }else {
                                    LogUtil.d("xxx", "onTouch: ");
                                }
                            }
                            if (newDist < oldDist - 1) {
                                oldDist = newDist;
                                multiple /= 2;
                                if (multiple >= 1 && multiple <=8){
                                    LogUtil.d("ppp", "倍数： "+multiple);
                                    SET(mapview, chessview, multiple);
                                }else {
                                    LogUtil.d("xxx", "onTouch: ");
                                }
                            }
                        }
                        break;*/
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
                SetZoom(mapview, playerview, multiple);
                break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
