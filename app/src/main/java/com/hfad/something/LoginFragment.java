package com.hfad.something;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.hfad.something.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Initialize animations
        Animation fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        Animation bottom_down = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_down);

        // Set the bottom down animation on top layout
        binding.topLinearLayout.setAnimation(bottom_down);

        // Create handler for other layouts
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Set fade in animation on other layouts
                binding.cardView.setAnimation(fade_in);
                binding.cardView2.setAnimation(fade_in);
                binding.cardView3.setAnimation(fade_in);
                binding.cardView4.setAnimation(fade_in);
                binding.textView.setAnimation(fade_in);
                binding.textView2.setAnimation(fade_in);
                binding.registerLayout.setAnimation(fade_in);
            }
        };

        handler.postDelayed(runnable, 1000);

        return view;
    }
}
