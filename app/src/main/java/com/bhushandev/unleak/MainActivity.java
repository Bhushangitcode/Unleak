package com.bhushandev.unleak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhushandev.unleak.comman.OnBoardingActivity;
import com.bhushandev.unleak.user.DashboardActivity;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;
    Animation topAnimation, bottomAnimation;
    ImageView unleakLogo;
    TextView slogan;

    SharedPreferences OnBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        unleakLogo = findViewById(R.id.imageView);
        slogan = findViewById(R.id.textView);

        unleakLogo.setAnimation(topAnimation);
        slogan.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                OnBoardingScreen = getSharedPreferences("OnBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = OnBoardingScreen.getBoolean("fisrtTime", true);
                if (isFirstTime) {
                    SharedPreferences.Editor editor = OnBoardingScreen.edit();
                    editor.putBoolean("fisrtTime", false);
                    editor.commit();

                    Intent view = new Intent(MainActivity.this, OnBoardingActivity.class);
                    startActivity(view);
                    finish();
                } else {
                    Intent view = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(view);
                    finish();
                }

            }
        }, SPLASH_TIMER);


    }
}