package com.hfad.something;

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
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create and add StatsFragment
        StatsFragment statsFragment = new StatsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main, statsFragment).commit();
        // Create Adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        // Create and set ViewPager
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        // Create and set TabLayout
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}