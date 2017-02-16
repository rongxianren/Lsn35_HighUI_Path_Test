package com.rongxianren.lsn35_highui_path_test.waveView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by wty on 2017/2/11.
 */

public class WaveView extends View {

    private Paint paint;
    private Path path;
    private float mOrignalY = 0;
    private final float mWaveLength = 300;
    private float dx = 0;
    private float dy = 0;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mOrignalY = getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        paint.setStyle(Paint.Style.STROKE);
        if (dy < mOrignalY + 150) {
            dy = dy + 10;
        }

        path.moveTo(-mWaveLength + dx, mOrignalY - dy);
        for (int lenght = -(int) mWaveLength; lenght < getWidth() + mWaveLength; lenght += mWaveLength) {
            path.rQuadTo(mWaveLength / 4, -150, mWaveLength / 2, 0);
            path.rQuadTo(mWaveLength / 4, 150, mWaveLength / 2, 0);
            canvas.drawPath(path, paint);
        }
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, paint);

    }

    public void startAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mWaveLength);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }
}
