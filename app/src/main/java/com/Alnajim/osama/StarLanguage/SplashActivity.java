package com.Alnajim.osama.StarLanguage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.Alnajim.osama.StarLanguage.Animation.ProgressBarAnimation;
import com.Alnajim.osama.StarLanguage.R;

public class SplashActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textProgressValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        saveValueAtSharedPrefrences();
        progressBar = findViewById(R.id.progressBarSplsh);
       // textProgressValue = findViewById(R.id.textProgressValue);
        progressBar.setMax(100);
        progressBar.setScaleX(3f);
        PBAnimation();

    }


    public void PBAnimation ()
    {
        ProgressBarAnimation animation = new ProgressBarAnimation(this,progressBar,textProgressValue,0f,100f);
        animation.setDuration(4000);
        progressBar.setAnimation(animation);
    }


    ////////////////
    ///
    void saveValueAtSharedPrefrences()
    {
        SharedPreferences.Editor editor = getSharedPreferences("ShowTheMessage", MODE_PRIVATE).edit();
        editor.putBoolean("Status",true);
        editor.apply();
    }
}
