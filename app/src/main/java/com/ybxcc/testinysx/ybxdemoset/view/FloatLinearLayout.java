package com.ybxcc.testinysx.ybxdemoset.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class FloatLinearLayout extends LinearLayout {
    private onUpdateViewEvent onUpdateViewEvent;

    public FloatLinearLayout(Context context) {
        super(context);

    }

    public FloatLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnUpdateViewEvent(FloatLinearLayout.onUpdateViewEvent onUpdateViewEvent) {
        this.onUpdateViewEvent = onUpdateViewEvent;
    }

    private int x;
    private int y;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int nowX = (int) event.getRawX();
                int nowY = (int) event.getRawY();
                int movedX = nowX - x;
                int movedY = nowY - y;
                x = nowX;
                y = nowY;

                if (null != onUpdateViewEvent) {
                    onUpdateViewEvent.updateView(movedX, movedY);
                }
//                layoutParams.x = layoutParams.x + movedX;
//                layoutParams.y = layoutParams.y + movedY;
//                windowManager.updateViewLayout(view, layoutParams);
                break;
        }
        return super.onTouchEvent(event);
    }

    public interface onUpdateViewEvent {
        public void updateView(int movedX, int movedY);
    }
}
