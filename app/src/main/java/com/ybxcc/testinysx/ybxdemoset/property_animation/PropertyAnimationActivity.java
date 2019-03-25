package com.ybxcc.testinysx.ybxdemoset.property_animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ybxcc.testinysx.ybxdemoset.R;

public class PropertyAnimationActivity extends AppCompatActivity {

    private float startValue = 0;
    private float endValue = 0;
    private float dampingValue = 0;
    private float velocityValue = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_property_animation);
        setContentView(R.layout.activity_second);

//        initView();

        testAnimation();
    }


    private View content;
    private int mX;
    private int mY;
    private LinearLayout revealContent;
    private LinearLayout line1;
    private TextView text1, text2, text3;
    private Button button1, button2, button3;

    private void testAnimation() {
        content = findViewById(R.id.reveal_content);
        revealContent = (LinearLayout) findViewById(R.id.reveal_content);
        line1 = (LinearLayout) findViewById(R.id.line1);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);


//        LayoutTransition transition_fullbtn = new LayoutTransition();
//        ObjectAnimator scaleX_app_fullbtn = ObjectAnimator.ofFloat(line1, "scaleX", 0.0f, 1.0f);
//        ObjectAnimator scaleY_app_fullbtn = ObjectAnimator.ofFloat(line1, "scaleY", 0.0f, 1.0f);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(scaleX_app_fullbtn, scaleY_app_fullbtn);
//        transition_fullbtn.setAnimator(LayoutTransition.APPEARING, animatorSet);
//        ObjectAnimator scaleX_disapp_fullbtn = ObjectAnimator.ofFloat(line1, "scaleX", 1.0f, 0.0f);
//        ObjectAnimator scaleY_disapp_fullbtn = ObjectAnimator.ofFloat(line1, "scaleY", 1.0f, 0.0f);
//        AnimatorSet animatorSetdis = new AnimatorSet();
//        animatorSetdis.playTogether(scaleX_disapp_fullbtn, scaleY_disapp_fullbtn);
//        transition_fullbtn.setAnimator(LayoutTransition.DISAPPEARING, animatorSetdis);
//        line1.setLayoutTransition(transition_fullbtn);



        PropertyValuesHolder holderScaleXShow = PropertyValuesHolder.ofFloat("scaleX", 0F, 1F);
        PropertyValuesHolder holderScaleYShow = PropertyValuesHolder.ofFloat("scaleY", 0F, 1F);
        ObjectAnimator animatorHolderShow = ObjectAnimator.ofPropertyValuesHolder(line1, holderScaleXShow, holderScaleYShow);

        PropertyValuesHolder holderScaleXDisappear = PropertyValuesHolder.ofFloat("scaleX", 1F, 0F);
        PropertyValuesHolder holderScaleYDisappear = PropertyValuesHolder.ofFloat("scaleY", 1F, 0F);
        ObjectAnimator animatorHolderDisappear = ObjectAnimator.ofPropertyValuesHolder(line1, holderScaleXDisappear, holderScaleYDisappear);

        LayoutTransition transition = new LayoutTransition();
        transition.setAnimator(LayoutTransition.APPEARING, animatorHolderShow);
        transition.setAnimator(LayoutTransition.DISAPPEARING, animatorHolderDisappear);
        line1.setLayoutTransition(transition);


        text1.setVisibility(View.GONE);
        text2.setVisibility(View.GONE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setVisibility(View.VISIBLE);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text2.setVisibility(View.VISIBLE);
                text3.setVisibility(View.VISIBLE);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setVisibility(View.GONE);
                text2.setVisibility(View.GONE);
                text3.setVisibility(View.GONE);
            }
        });
    }


    private void initView() {
//        ImageView img = (ImageView) findViewById(R.id.img);
//        img.animate().scaleY(1.5f).setDuration(2000).setInterpolator(new DampedMotionInterpolator());
//
//        ObjectAnimator scaleXS = ObjectAnimator.ofFloat(img, "scaleX", 0.6f, 1.0f);
//        ObjectAnimator scaleYS = ObjectAnimator.ofFloat(img, "scaleY", 0.6f, 1.0f);
//
//        final AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.setDuration(1000);
//        animatorSet.setInterpolator(new DampedMotionInterpolator());
//        animatorSet.playTogether(scaleXS, scaleYS);
//

//        ObjectAnimator translationX = ObjectAnimator.ofFloat(img, "translationX", 150, 0);
//        ObjectAnimator translationY = ObjectAnimator.ofFloat(img, "translationY", 150, 0);
//
//        ObjectAnimator scaleX = ObjectAnimator.ofFloat(img, "scaleX", 0, 0.6f);
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(img, "scaleY", 0, 0.6f);
//
//
//        AnimatorSet animatorSet1 = new AnimatorSet();
//        animatorSet1.setDuration(1000);
//        animatorSet1.setInterpolator(new LinearInterpolator());
//        animatorSet1.playTogether(translationX, translationY, scaleX, scaleY);
//        animatorSet1.start();
//        animatorSet1.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                animatorSet.start();
//            }
//        });

        Button tv_start = (Button) findViewById(R.id.tv_start);
        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playAnimation();
            }
        });


        SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        SeekBar seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        SeekBar seekBar4 = (SeekBar) findViewById(R.id.seekBar4);

        final TextView tv_seekbar1 = (TextView) findViewById(R.id.tv_seekbar1);
        final TextView tv_seekbar2 = (TextView) findViewById(R.id.tv_seekbar2);
        final TextView tv_seekbar3 = (TextView) findViewById(R.id.tv_seekbar3);
        final TextView tv_seekbar4 = (TextView) findViewById(R.id.tv_seekbar4);


        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float star = (float) progress;
                startValue = star / 10;
                tv_seekbar1.setText(startValue + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float end = (float) progress;
                endValue = end / 10;
                tv_seekbar2.setText(endValue + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dampingValue = progress;
                tv_seekbar3.setText(dampingValue + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                velocityValue = progress;
                tv_seekbar4.setText(velocityValue + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void playAnimation() {
        ImageView img = (ImageView) findViewById(R.id.img);
        img.setVisibility(View.VISIBLE);

        ObjectAnimator scaleXS = ObjectAnimator.ofFloat(img, "scaleX", startValue, endValue);
        ObjectAnimator scaleYS = ObjectAnimator.ofFloat(img, "scaleY", startValue, endValue);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new DampedMotionInterpolator());
        animatorSet.playTogether(scaleXS, scaleYS);
        animatorSet.start();

    }


    public class DampedMotionInterpolator implements TimeInterpolator {

        @Override
        public float getInterpolation(float input) {
            float x = input;

            float endValu = endValue;
            float startValu = startValue;

            //阻力
            float dampingFactor = dampingValue;

            //速度
            float velocityFactor = velocityValue;


            float diff = endValu - startValu;


            float value = endValue - (float) (diff * Math.pow(Math.E, -1 * dampingFactor * x) * Math.cos(velocityFactor * x));
            return value;
        }
    }


}
