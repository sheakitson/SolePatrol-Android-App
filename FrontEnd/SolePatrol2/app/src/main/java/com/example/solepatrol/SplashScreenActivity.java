package com.example.solepatrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.solepatrol.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    Timer timer;

    ImageView splashScreen;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashScreen = findViewById(R.id.splashScreen);
        lottieAnimationView = findViewById(R.id.lottie);

        splashScreen.animate().translationY(2500).setDuration(1000).setStartDelay(3000);
        lottieAnimationView.animate().translationY(2500).setDuration(1000).setStartDelay(3000);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, OnboardingScreenActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3200);
    }
}