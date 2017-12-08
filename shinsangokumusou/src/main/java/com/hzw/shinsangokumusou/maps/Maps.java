package com.hzw.shinsangokumusou.maps;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;

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

import static com.hzw.shinsangokumusou.utils.MapsUtils.GetPosition;

public class Maps extends BaseDisplay implements View.OnTouchListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener {

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
    MediaPlayer mediaPlayer;
    BGM BGM = new BGM(mediaPlayer);

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
                "\nmap宽： ", MapsValue.getMap_width() * multiple, "map高：", MapsValue.getMap_height() * multiple);

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
                /******* 注意：这里一定不要把1080， 1350替换成MapsValue.getMap_width()，MapsValue.getMap_height()*******/
                scrollView.scrollTo(0, (int) (1350 * multiple * 0.5 * (1 - 1 / multiple)));
                horizontalScrollView.scrollTo((int) (1080 * multiple * 0.5 * (1 - 1 / multiple)), 0);
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
}
