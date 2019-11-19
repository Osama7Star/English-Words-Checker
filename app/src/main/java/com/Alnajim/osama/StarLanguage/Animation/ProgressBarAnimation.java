package com.Alnajim.osama.StarLanguage.Animation;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.Alnajim.osama.StarLanguage.WordGame.WordsTest;

public class ProgressBarAnimation extends Animation
{

    private Context context ;
    private ProgressBar progressBar;
    private TextView textProgressValue;
    private float from,to ;
    public ProgressBarAnimation(Context context, ProgressBar progressBar,TextView textProgressValue, float from, float to) {
        this.context = context;
        this.progressBar = progressBar;
        this.textProgressValue = textProgressValue;
        this.from = from;
        this.to = to;
    }


    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        float value = from +(to - from )*interpolatedTime;
        progressBar.setProgress((int)value);
//        textProgressValue.setText((int)value+"");

        if(value == to )
            context.startActivity(new Intent(context, WordsTest.class));
    }
}
