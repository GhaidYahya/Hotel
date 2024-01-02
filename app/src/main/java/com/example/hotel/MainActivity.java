package com.example.hotel;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

public class MainActivity extends AppCompatActivity {

    private TextView logoText;
    private ImageView roselogo;
    private ImageView whitelogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MotionLayout motionLayout = findViewById(R.id.motionLayout);
        logoText = findViewById(R.id.logoText);
        roselogo = findViewById(R.id.roselogo);
        whitelogo = findViewById(R.id.whitelogo);
        logoText.setAlpha(0f);

        roselogo.setImageResource(R.drawable.girll);
        whitelogo.setImageResource(R.drawable.girll);

        ViewTreeObserver viewTreeObserver = motionLayout.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                motionLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
                    @Override
                    public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                        if (currentId == R.id.end) {
                            // after the animation is completed fade in the logoText
                            fadeInLogoText();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startLoginActivity();
                                }
                            }, 3000);
                        }
                    }

                    @Override
                    public void onTransitionChange(
                            MotionLayout motionLayout,
                            int startId,
                            int endId,
                            float progress) {}

                    @Override
                    public void onTransitionStarted(
                            MotionLayout motionLayout,
                            int startId,
                            int endId) {}

                    @Override
                    public void onTransitionTrigger(
                            MotionLayout motionLayout,
                            int triggerId,
                            boolean positive,
                            float progress) {}
                });
            }
        });
    }

    private void fadeInLogoText() {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(logoText, View.ALPHA, 0f, 1f);
        alphaAnimator.setDuration(5000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(alphaAnimator);
        animatorSet.start();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }
}
