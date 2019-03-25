package com.ybxcc.testinysx.ybxdemoset.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;


public class ShapeTextView extends android.support.v7.widget.AppCompatTextView {

    private Paint dashPaint;

    private float tvHeight = 0f;
    private float tvWo = 0f;

    public ShapeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initPaint(float lineW) {

        dashPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dashPaint.setDither(true);
        dashPaint.setColor(Color.parseColor("#EAEAEA"));
        dashPaint.setStyle(Paint.Style.FILL);
        dashPaint.setStrokeWidth(lineW);
    }

    public void setTextStr(Context context, String string) {
        initPaint(5);
        setText(string);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        tvHeight = getHeight();
        String str = (String) getText();
        float tvW = getWidth();
        float pace = tvW / str.length();

        canvas.drawLine(0, 0, tvWo, 0, dashPaint);
        canvas.drawLine(0, tvHeight, tvWo, tvHeight, dashPaint);

        for (int i = 0; i < str.length() + 1; i++) {
            tvWo = pace * i;
            Log.e("height", "/" + i + "/" + tvWo + "/" + tvHeight);
            canvas.drawLine(tvWo, 0, tvWo, tvHeight, dashPaint);
        }
        canvas.save();
    }
}