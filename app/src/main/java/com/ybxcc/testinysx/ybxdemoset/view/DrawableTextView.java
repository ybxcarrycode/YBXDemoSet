package com.ybxcc.testinysx.ybxdemoset.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class DrawableTextView extends TextView {
    public DrawableTextView(Context context) {
        super(context);
    }

    public DrawableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable[] = getCompoundDrawables();
        Drawable leftDrawable = drawable[2];
        if (leftDrawable != null) {
            int leftDraW = leftDrawable.getIntrinsicWidth();
            int drawablePace = getCompoundDrawablePadding();
            int textW = (int) getPaint().measureText(getText().toString().trim());
            int bodyW = leftDraW + drawablePace + textW;
            canvas.save();
//            canvas.translate(-(getWidth() - bodyW) / 2, 0);
            setPadding( ((getWidth() - bodyW) / 2), 0, ((getWidth() - bodyW) / 2), 0);
        }
        super.onDraw(canvas);
    }
}
