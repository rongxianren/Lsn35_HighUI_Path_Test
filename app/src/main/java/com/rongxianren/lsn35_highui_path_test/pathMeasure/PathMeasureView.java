package com.rongxianren.lsn35_highui_path_test.pathMeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wty on 2017/2/13.
 */

public class PathMeasureView extends View {


    private Paint mPaint;
    private Path mPath;
    private PathMeasure mPathMeasure;

    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();

        mPath = new Path();
        mPathMeasure = new PathMeasure();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);

        mPath.moveTo(100, 100);
        mPath.lineTo(700, 100);
        mPath.lineTo(700, 700);
        mPath.lineTo(100, 700);
        mPath.close();

        RectF rectF = new RectF(105, 105, 695, 695);
        mPath.addOval(rectF, Path.Direction.CW);

        mPathMeasure.setPath(mPath, true);
        canvas.drawPath(mPath, mPaint);

        System.out.println("------mPathMeasure-------1 = " + mPathMeasure.getLength());

        mPathMeasure.nextContour();

        System.out.println("------mPathMeasure-------2 = " + mPathMeasure.getLength());

        float totalLength = mPathMeasure.getLength();
        float[] pos = new float[2];
        float[] tan = new float[2];
        mPathMeasure.getPosTan(totalLength / 2, pos, tan);

        System.out.println("------pos------- = " + pos[0] + " --- " + pos[1]);

        System.out.println("------tan------- = " + tan[0] + " --- " + tan[1]);


        mPathMeasure.getPosTan(0, pos, tan);

        System.out.println("------pos------- = " + pos[0] + " --- " + pos[1]);

        System.out.println("------tan------- = " + tan[0] + " --- " + tan[1]);
    }
}
