package com.hfad.something;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        ImageView splashImage = findViewById(R.id.splashImage);
        ObjectAnimator animator = ObjectAnimator.ofFloat(splashImage, "rotation", 0f, 360f);
        animator.setDuration(5000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}