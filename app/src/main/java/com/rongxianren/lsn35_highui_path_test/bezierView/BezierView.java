package com.rongxianren.lsn35_highui_path_test.bezierView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wty on 2017/2/11.
 */

public class BezierView extends View {

    private Path mPath;
    private Paint mPaint;

    private float mWaveLength = 400;
    private float mOriginalY = 0;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        mOriginalY = getHeight() / 2;
        mPath.moveTo(0, mOriginalY);
        mPath.quadTo(mWaveLength / 4, mOriginalY - 150, mWaveLength / 2, mOriginalY);
        mPath.quadTo(mWaveLength * 3 / 4, mOriginalY + 150, mWaveLength, mOriginalY);
        canvas.drawPath(mPath, mPaint);


        mPath.moveTo(0, mOriginalY * 3 / 4);
        mPath.rQuadTo(mWaveLength / 4, -150, mWaveLength / 2, 0);
        mPath.rQuadTo(mWaveLength / 4, 150, mWaveLength / 2, 0);
        canvas.drawPath(mPath, mPaint);
    }
}
