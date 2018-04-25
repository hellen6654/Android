package com.gameusingdynamicfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainFragment extends Fragment {

    public enum GameResultType {
        TYPE_1, TYPE_2, HIDE
    }

    // 所屬的 Activity 必須實作以下介面中的callback方法
    public interface CallbackInterface {
        public void updateGameResult(int iCountSet,
                                     int iCountPlayerWin,
                                     int iCountComWin,
                                     int iCountDraw);
        public void enableGameResult(GameResultType type);
    };

    private CallbackInterface mCallback;
    private ImageButton mImgBtnDie;
    private TextView mTxtResult;
/*
    public EditText mEdtCountSet,
                    mEdtCountPlayerWin,
                    mEdtCountComWin,
                    mEdtCountDraw;
*/

    // 新增統計遊戲局數和輸贏的變數
    private int miCountSet = 0,
                miCountPlayerWin = 0,
                miCountComWin = 0,
                miCountDraw = 0;

    private Button mBtnShowResult1,
                    mBtnShowResult2,
                    mBtnHiddenResult;

    private boolean mbShowResult = false;

//    private final static String TAG = "Result";
//    private int mTagCount = 0;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (CallbackInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    "must implement GameFragment.CallbackInterface.");
        }
    }

    private static class StaticHandler extends Handler {

        private final WeakReference<MainFragment> mActivity;

        public StaticHandler(MainFragment activity){
            mActivity= new WeakReference<MainFragment>(activity);
        }
        @Override
        public void handleMessage(Message msg){
            MainFragment activity=mActivity.get();
            if (activity==null) return;

            int iDieNum=(int)(Math.random()*6+1);

            if (iDieNum==1) activity.mImgBtnDie.setImageResource(R.drawable.dice01);
            else if (iDieNum==2) activity.mImgBtnDie.setImageResource(R.drawable.dice02);
            else if (iDieNum==3) activity.mImgBtnDie.setImageResource(R.drawable.dice03);
            else if (iDieNum==4) activity.mImgBtnDie.setImageResource(R.drawable.dice04);
            else if (iDieNum==5) activity.mImgBtnDie.setImageResource(R.drawable.dice05);
            else if (iDieNum==6) activity.mImgBtnDie.setImageResource(R.drawable.dice06);

            if (iDieNum > 4) {
                //電腦贏

                activity.mTxtResult.setText(activity.getString(R.string.result) +
                        activity.getString(R.string.player_lose));
                activity.miCountComWin++;
//                mEdtCountDraw.setText(String.valueOf(miCountDraw));
            }
            else if (iDieNum == 3 || iDieNum == 4) {
                //平手

                activity.mTxtResult.setText(activity.getString(R.string.result) +
                        activity.getString(R.string.player_draw));
                activity.miCountDraw++;
//                mEdtCountComWin.setText(String.valueOf(miCountComWin));
            }
            else if (iDieNum < 3){

                activity.mTxtResult.setText(activity.getString(R.string.result) +
                        activity.getString(R.string.player_win));
                activity.miCountPlayerWin++;
//                mEdtCountPlayerWin.setText(String.valueOf(miCountPlayerWin));
            }
            activity.miCountSet++;
//            mEdtCountSet.setText(String.valueOf(miCountSet));


            activity.mCallback.updateGameResult(activity.miCountSet, activity.miCountPlayerWin,
                    activity.miCountComWin, activity.miCountDraw);
        }
    }
    public final StaticHandler mHandler = new StaticHandler(this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 利用inflater物件的inflate()方法取得介面佈局檔，並將最後的結果傳回給系統
       return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 必須先呼叫getView()取得程式畫面物件，然後才能呼叫它的
        // findViewById()取得介面物件
        mTxtResult = (TextView) getView().findViewById(R.id.txtResult);
        mImgBtnDie = (ImageButton) getView().findViewById(R.id.imgBtnDie);

        // 以下介面元件是在另一個Fragment中，因此必須呼叫所屬的Activity
        // 才能取得這些介面元件
/*
        mEdtCountSet = (EditText) getActivity().findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (EditText) getActivity().findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (EditText) getActivity().findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (EditText) getActivity().findViewById(R.id.edtCountDraw);
*/

        mImgBtnDie.setOnClickListener(imgBtnDieOnClick);


        mBtnShowResult1 = (Button) getView().findViewById(R.id.btnShowResult1);
        mBtnShowResult2 = (Button) getView().findViewById(R.id.btnShowResult2);
        mBtnHiddenResult = (Button) getView().findViewById(R.id.btnHiddenResult);

        mBtnShowResult1.setOnClickListener(btnShowResult1OnClick);
        mBtnShowResult2.setOnClickListener(btnShowResult2OnClick);
        mBtnHiddenResult.setOnClickListener(btnHiddenResultOnClick);
    }

    private View.OnClickListener imgBtnDieOnClick = new View.OnClickListener() {
        @SuppressLint("SetTextI18n")
        public void onClick(View v) {
            // Decide computer play.
            Resources res =getResources();
            final AnimationDrawable animDraw=(AnimationDrawable)res.getDrawable(R.drawable.anim_roll_dice);
            mImgBtnDie.setImageDrawable(animDraw);
            animDraw.start();

            new Thread(new Runnable(){
                @Override
                public void run(){
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    animDraw.stop();
                    mHandler.sendMessage(mHandler.obtainMessage());
                }
            }).start();
        }
    };



    private View.OnClickListener btnShowResult1OnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCallback.enableGameResult(GameResultType.TYPE_1);
        }
    };

    private View.OnClickListener btnShowResult2OnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCallback.enableGameResult(GameResultType.TYPE_2);
        }
    };

    private View.OnClickListener btnHiddenResultOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCallback.enableGameResult(GameResultType.HIDE);
        }
    };

}
