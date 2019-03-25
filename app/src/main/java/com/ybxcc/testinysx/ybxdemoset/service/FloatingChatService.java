package com.ybxcc.testinysx.ybxdemoset.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;



import com.ybxcc.testinysx.ybxdemoset.R;
import com.ybxcc.testinysx.ybxdemoset.view.FloatLinearLayout;


public class FloatingChatService extends Service {
    public static boolean isStarted = false;

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;

    private FloatLinearLayout displayView;
    private ImageView chat_button;

    @Override
    public void onCreate() {
        super.onCreate();
        isStarted = true;
        windowManager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
        layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.x = 0;
        layoutParams.y = 0;


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showFloatingWindow();
        return super.onStartCommand(intent, flags, startId);
    }

    private void showFloatingWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                LayoutInflater layoutInflater = LayoutInflater.from(this);
                displayView = (FloatLinearLayout) layoutInflater.inflate(R.layout.service_float_chat, null);
                displayView.setOnTouchListener(new FloatingOnTouchListener());
                displayView.setOnUpdateViewEvent(new FloatLinearLayout.onUpdateViewEvent() {
                    @Override
                    public void updateView(int movedX, int movedY) {
                        layoutParams.x = layoutParams.x + movedX;
                        layoutParams.y = layoutParams.y + movedY;
                        windowManager.updateViewLayout(displayView, layoutParams);
                    }
                });
                chat_button = displayView.findViewById(R.id.chat_button);
//                chat_button.setOnClickListener(v -> {
//                    ToastUtils.show("点击");
//                });
                windowManager.addView(displayView, layoutParams);
            }
        }
    }


    private long mDownTime, mUpTime;

    private class FloatingOnTouchListener implements View.OnTouchListener {
        private int x;
        private int y;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = (int) event.getRawX();
                    y = (int) event.getRawY();
                    mDownTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int nowX = (int) event.getRawX();
                    int nowY = (int) event.getRawY();
                    int movedX = nowX - x;
                    int movedY = nowY - y;
                    x = nowX;
                    y = nowY;
                    layoutParams.x = layoutParams.x + movedX;
                    layoutParams.y = layoutParams.y + movedY;
                    windowManager.updateViewLayout(view, layoutParams);
                    break;
                case MotionEvent.ACTION_UP:
                    mUpTime = System.currentTimeMillis();
                    boolean isClick = mUpTime - mDownTime > 200;
                    return isClick;
            }
            return false;
        }
    }
}
