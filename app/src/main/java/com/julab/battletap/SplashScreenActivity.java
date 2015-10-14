package com.julab.battletap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SplashScreenActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View v = findViewById(R.id.framelayout_SplashScreen);

        Animation a = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        a.setDuration(2000);
        a.setRepeatCount(1);
        a.setRepeatMode(Animation.REVERSE);
        a.setFillAfter(true);
        a.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                return;
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {

                Intent intent = new Intent(SplashScreenActivity.this, ConnectionActivity.class);
                startActivity(intent);
                SplashScreenActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
        v.startAnimation(a);
    }

    @Override
    public void onBackPressed()
    {
        return;
    }
}
