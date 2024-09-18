package com.hfad.something;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView splashImage = findViewById(R.id.splashImage);
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        splashImage.startAnimation(rotate);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, StartScreen.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}