package com.ybxcc.testinysx.ybxdemoset.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class EnclosureShapeView extends View {
    private Paint textPaint;
    private Paint enclosurePaint;
    /* 田字框的内边距 */
    private float leftPadding = 10;
    private float rightPadding = 10;
    private float topPadding = 10;

    private float bottomPadding = 10;
    /* 田字框的内边距 */

    private String mText;
    // 默认文本大小为50
    private float mTextSize = 50f;
    private Paint dashPaint;

    public EnclosureShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initTextPaint();
        initEnclosurePaint();
    }


    private void initEnclosurePaint() {
        enclosurePaint = new Paint();
        enclosurePaint.setColor(Color.BLUE);
        enclosurePaint.setAntiAlias(true);
        enclosurePaint.setStrokeWidth(5);
        enclosurePaint.setStyle(Paint.Style.STROKE);

        dashPaint = new Paint();
        dashPaint.setColor(Color.BLUE);
        dashPaint.setAntiAlias(true);
        dashPaint.setStrokeWidth(5);
        dashPaint.setStyle(Paint.Style.STROKE);
        dashPaint.setPathEffect(new DashPathEffect(new float[]{15, 20}, 0));
    }

    private void initTextPaint() {
        textPaint = new Paint();
        textPaint.setTextSize(mTextSize);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        EnclosureMetrics em = getEnclosureSize();
        for (int i = 0; i < em.rows; i++) {
            canvas.translate(0, i == 0 ? 20 : em.height + 20);
            canvas.save();
            canvas.translate(20, 0);
            for (int j = 0; j < em.columns; j++) {
                Log.e("fff", "///"+i);
                String text;
                try {
                    int index = j + i * em.columns;
                    text = mText.substring(index, index + 1);
                } catch (Exception e) {
                    text = "";
                }
                // 如果已经没有文字可画了，可以停止画田字格
                if (TextUtils.isEmpty(text))
                    break;
                drawEnclosure(canvas, em);
                drawText(text, canvas, em);
                canvas.translate(em.width, 0);
            }
            canvas.restore();
        }
    }

    private void drawText(String text, Canvas canvas, EnclosureMetrics em) {
        Paint.FontMetrics fm = textPaint.getFontMetrics();
        canvas.drawText(text, em.width / 2, em.height / 2 + (fm.bottom - fm.top) / 2 - fm.bottom, textPaint);
    }

    private void drawEnclosure(Canvas canvas, EnclosureMetrics em) {
        canvas.drawRect(0, 0, em.width, em.height, enclosurePaint);


        Path path = new Path();
//        path.lineTo(em.width, em.height);
//        canvas.drawPath(path, dashPaint);
//        path.reset();
//        path.moveTo(0, em.height);
//        path.lineTo(em.width, 0);
//        canvas.drawPath(path, dashPaint);
//        path.reset();
//        path.moveTo(0, em.height / 2);
//        path.lineTo(em.width, em.height / 2);
//        canvas.drawPath(path, dashPaint);
//        path.reset();
        path.moveTo(em.width / 2, 0);
        path.lineTo(em.width / 2, em.height);
        canvas.drawPath(path, dashPaint);
    }

    public void setText(String text) {
        mText = text;
        invalidate();
    }

    public void setTextSize(float textSize) {
        mTextSize = textSize;
        initTextPaint();
        invalidate();
    }

    public EnclosureMetrics getEnclosureSize() {
        EnclosureMetrics em = new EnclosureMetrics();
        Paint.FontMetrics fm = textPaint.getFontMetrics();
        em.height = fm.bottom - fm.top + topPadding + bottomPadding;
        em.width = textPaint.measureText(mText.substring(0, 1)) + leftPadding + rightPadding;
        em.columns = (int) (getMeasuredWidth() / em.width);
        em.rows = (int) Math.ceil((float) mText.length() / em.columns);
        // 如果view的宽度能显示的列数超过了文字的个数，将列数置为文字的个数，行数置为1
        if (em.columns >= mText.length()) {
            em.columns = mText.length();
            em.rows = 1;
        }
        return em;
    }

    public static class EnclosureMetrics {
        public float width;
        public float height;
        public int rows;
        public int columns;
    }

}
