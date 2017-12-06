package com.hzw.shinsangokumusou.maps;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.hzw.shinsangokumusou.R;
import com.hzw.shinsangokumusou.chess.Chess;
import com.hzw.shinsangokumusou.chess.General;
import com.hzw.shinsangokumusou.chess.Player;
import com.hzw.shinsangokumusou.staticvalue.MapsValue;

public class Maps extends AppCompatActivity {

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

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        scrollView = (ScrollView) findViewById(R.id.sv);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv);

        mapview = new HJmap(this);

        maps = mapview.getMaps();

        mapview.setBackgroundColor(MapsValue.Ground);
        chessview = new General(this);
        playerview = new Player(this);
        SET(mapview, playerview, multiple);
        mapRL = (RelativeLayout) findViewById(R.id.mapview);
        mapRL.addView(mapview);
//        mapRL.addView(chessview);
        mapRL.addView(playerview);
        mapview.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int nCnt = motionEvent.getPointerCount();

                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_UP:
                        mapview.performClick();
                        Toast.makeText(Maps.this,
                                "点击位置: X:" + motionEvent.getX() + ", Y: " + motionEvent.getY() +
                                        "\n数组位置： (" + GetPosition(motionEvent.getX() - 1) + " , " + GetPosition(motionEvent.getY() - 1) + ")" +
                                        "\n倍数： " + multiple +
                                        "\n该位置参数： " + maps[GetPosition(motionEvent.getX()) - 1][GetPosition(motionEvent.getY()) - 1], Toast.LENGTH_SHORT)
                                .show();
                        break;


                    case MotionEvent.ACTION_DOWN:
                        mapview.performClick();
                        break;

                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(motionEvent);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        /*if (nCnt == 2){
                            float newDist = spacing(motionEvent);
                            if (newDist > oldDist + 1) {
                                oldDist = newDist;
                                multiple *= 2;
                                if (multiple >= 1 && multiple <=8){
                                    Log.d("ppp", "倍数： "+multiple);
                                    SET(mapview, chessview, multiple);
                                }else {
                                    Log.d("xxx", "onTouch: ");
                                }
                            }
                            if (newDist < oldDist - 1) {
                                oldDist = newDist;
                                multiple /= 2;
                                if (multiple >= 1 && multiple <=8){
                                    Log.d("ppp", "倍数： "+multiple);
                                    SET(mapview, chessview, multiple);
                                }else {
                                    Log.d("xxx", "onTouch: ");
                                }
                            }
                        }
                        break;*/
                    default:
                        break;
                }

                return true;
            }
        });

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                multiple = 1 + 0.07f * i;
                SET(mapview, playerview, multiple);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        relativeLayout = (RelativeLayout) findViewById(R.id.messageRL);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SET(mapview, playerview, 1f);
                multiple = 1;
            }
        });

        listView = (RelativeLayout) findViewById(R.id.messageLV);
        listView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SET(mapview, playerview, 8f);
                multiple = 8;
            }
        });

    }

    public void SET(HJmap mapview, Chess chess, float multiple) {
        MapsValue.setMap_width((int) (1080 * multiple));
        MapsValue.setMap_height((int) (1350 * multiple));
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

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    public int GetPosition(float a) {
        return (int) Math.ceil(a / 15);
    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            if (multiple != 1) {
                Log.d("ooo", "此时倍数： " + multiple + "移动百分数： " + 0.5 * (1 - 1 / multiple));
                scrollView.scrollTo(0, (int) (1350 * multiple * 0.5 * (1 - 1 / multiple)));
                horizontalScrollView.scrollTo((int) (1080 * multiple * 0.5 * (1 - 1 / multiple)), 0);
            }
        }
    };
}
