package com.galleryusingviewanimation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    private GridView mGridView;
    private ImageSwitcher mImgSwitcher;
    Animation[] animationList;
    private Integer[] miImgArr = {
            R.drawable.img01, R.drawable.img02, R.drawable.img03,
            R.drawable.img04, R.drawable.img05, R.drawable.img06,
            R.drawable.img07, R.drawable.img08,R.drawable.samll1a,
            R.drawable.samll2a,R.drawable.samll3a,R.drawable.samll4a,
            R.drawable.samll5a,R.drawable.samll6a,R.drawable.vra};

    private Integer[] miThumbImgArr = {
            R.drawable.img01th, R.drawable.img02th, R.drawable.img03th,
            R.drawable.img04th, R.drawable.img05th, R.drawable.img06th,
            R.drawable.img07th, R.drawable.img08th,R.drawable.samll1,
            R.drawable.samll2,R.drawable.samll3,R.drawable.samll4,
            R.drawable.samll5,R.drawable.samll6,R.drawable.vr,};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgSwitcher = (ImageSwitcher) findViewById(R.id.imgSwitcher);

        mImgSwitcher.setFactory(this);	// 主程式類別必須implements ViewSwitcher.ViewFactory
        mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        ImageAdapter imgAdap = new ImageAdapter(this, miThumbImgArr);

        mGridView = (GridView)findViewById(R.id.gridView);
        mGridView.setAdapter(imgAdap);
        mGridView.setOnItemClickListener(gridViewOnItemClick);
        animationinti();
    }
    protected void animationinti()
    {
        ScaleAnimation scale;
        TranslateAnimation trans;
        RotateAnimation rotate;

        AlphaAnimation alphaIn = new AlphaAnimation(0.0f,0.5f);
        alphaIn.setInterpolator(new LinearInterpolator());
        alphaIn.setStartOffset(0);
        alphaIn.setDuration(3000);

        AlphaAnimation alphaOut = new AlphaAnimation(0.5f,0.0f);
        alphaOut.setInterpolator(new LinearInterpolator());
        alphaOut.setStartOffset(0);
        alphaOut.setDuration(3000);

        AnimationSet transIn = new AnimationSet(false);
        trans = new TranslateAnimation(0,0,-600,0);
        trans.setInterpolator(new LinearInterpolator());
        trans.setDuration(3000);
        transIn.addAnimation(trans);

        AnimationSet transOut = new AnimationSet(false);
        trans = new TranslateAnimation(0,0,0,600);
        trans.setInterpolator(new LinearInterpolator());
        trans.setDuration(3000);
        transOut.addAnimation(trans);

        AnimationSet scaleRotateIn = new AnimationSet(false);
        scale = new ScaleAnimation(0f,0.7f,0f,0.7f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setInterpolator(new LinearInterpolator());
        scale.setStartOffset(0);
        scale.setDuration(3000);
        rotate = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setStartOffset(0);
        rotate.setDuration(3000);
        scaleRotateIn.addAnimation(scale);
        scaleRotateIn.addAnimation(rotate);

        AnimationSet scaleRotateOut = new AnimationSet(false);
        scale = new ScaleAnimation(0.5f,0f,0.5f,0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setInterpolator(new LinearInterpolator());
        scale.setDuration(3000);
        rotate = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setInterpolator(new  AccelerateDecelerateInterpolator());
        rotate.setDuration(3000);
        scaleRotateOut.addAnimation(scale);
        scaleRotateOut.addAnimation(rotate);

        AnimationSet scaleRotateTranIn = new AnimationSet(false);
        scale = new ScaleAnimation(0f,0.7f,0f,0.7f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setInterpolator(new LinearInterpolator());
        scale.setStartOffset(0);
        scale.setDuration(3000);
        rotate = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setStartOffset(0);
        rotate.setDuration(3000);
        trans = new TranslateAnimation(0f,0f,-600,0);
        trans.setInterpolator(new LinearInterpolator());
        trans.setStartOffset(0);
        trans.setDuration(3000);
        scaleRotateTranIn.addAnimation(scale);
        scaleRotateTranIn.addAnimation(rotate);
        scaleRotateTranIn.addAnimation(trans);

        AnimationSet scaleRotateTransOut = new AnimationSet(false);
        scale = new ScaleAnimation(0.5f,0f,0.5f,0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setInterpolator(new LinearInterpolator());
        scale.setDuration(3000);
        rotate = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setInterpolator(new  AccelerateDecelerateInterpolator());
        rotate.setDuration(3000);
        trans = new TranslateAnimation(0f,0f,0f,600f);
        trans.setInterpolator(new LinearInterpolator());
        trans.setDuration(3000);
        scaleRotateTransOut.addAnimation(scale);
        scaleRotateTransOut.addAnimation(rotate);
        scaleRotateTranIn.addAnimation(trans);

        animationList = new Animation[]{
                alphaIn,alphaOut,scaleRotateIn,scaleRotateOut,
                scaleRotateTranIn,scaleRotateTransOut,transIn,transOut
        };
    }
    @Override
    public View makeView() {
        ImageView v = new ImageView(this);
        v.setBackgroundColor(0xFF000000);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        v.setBackgroundColor(Color.WHITE);
        return v;
    }


    private AdapterView.OnItemClickListener gridViewOnItemClick = new
            AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View v,
                                        int position,
                                        long id) {
                    int s= (int)Math.floor(Math.random()*4);
                    mImgSwitcher.setInAnimation(animationList[s*2]);
                    mImgSwitcher.setOutAnimation(animationList[s*2+1]);
                    mImgSwitcher.setImageResource(miImgArr[position]);
                }
            };
}
