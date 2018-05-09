package com.example.a105590040.hw7;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private ImageView mimgDie;
    private Button mbtnPlay;
    private Button mbtnResule;
    private static int comwin=0;
    private static int playerwin=0;
    private static int draw=0;
    private static int total=0;
    private static Context ct ;
    private  static class StaticHandler extends Handler{
        private final WeakReference<MainActivity> mActivity;
        public StaticHandler(MainActivity activity){
            mActivity = new WeakReference<MainActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg){
            MainActivity activity = mActivity.get();
            if (activity ==null) return;
            int iRand =(int)(Math.random()*6+1);
            total++;
            int win=-999;
            switch (iRand){
                case 1:
                    playerwin++;
                    activity.mimgDie.setImageResource(R.drawable.dice01);
                    win=1;
                    break;
                case 2:
                    playerwin++;
                    activity.mimgDie.setImageResource(R.drawable.dice02);
                    win=1;
                    break;
                case 3:
                    activity.mimgDie.setImageResource(R.drawable.dice03);
                    draw++;
                    win=0;
                    break;
                case 4:
                    activity.mimgDie.setImageResource(R.drawable.dice04);
                    draw++;
                    win=0;
                    break;
                case 5:
                    comwin++;
                    activity.mimgDie.setImageResource(R.drawable.dice05);
                    win=-1;
                    break;
                case 6:
                    comwin++;
                    activity.mimgDie.setImageResource(R.drawable.dice06);
                    win=-1;
                    break;
            }
            if(win==1)
                Toast.makeText(activity,R.string.player_win_set,Toast.LENGTH_LONG).show();
            else if (win==-1)
                Toast.makeText(activity,R.string.comupter_win_set,Toast.LENGTH_LONG).show();
            else if (win==0)
                Toast.makeText(activity,R.string.draw,Toast.LENGTH_LONG).show();

        }
    }
    public final StaticHandler mHandler = new StaticHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mimgDie =(ImageView)findViewById(R.id.imgDie);
        mbtnPlay=(Button)findViewById(R.id.btnPlay);
        mbtnResule=(Button)findViewById(R.id.btnResult);
        ct = MainActivity.this;
        mbtnPlay.setOnClickListener(btnDieOnClick);
        mbtnResule.setOnClickListener(btnResultOnClick);
    }
    private View.OnClickListener btnDieOnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            Resources res =getResources();
            final AnimationDrawable animDraw=(AnimationDrawable)res.getDrawable(R.drawable.anim_roll_dice);
            mimgDie.setImageDrawable(animDraw);
            animDraw.start();
            new Thread(new Runnable(){
                @Override
                public void run(){
                    try{
                        Thread.sleep(2000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    animDraw.stop();

                    mHandler.sendMessage(mHandler.obtainMessage());
                }
            }).start();

        }
    };
    private  View.OnClickListener btnResultOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it =new Intent();
            it.setClass(MainActivity.this,ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("COMWIN",comwin);
            bundle.putInt("PLAYERWIN",playerwin);
            bundle.putInt("DRAW",draw);
            bundle.putInt("TOTAL",total);
            it.putExtras(bundle);
            startActivity(it);
        }
    };
}
