package com.rongxianren.lsn35_highui_path_test.searchViewAnimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.view.View;

/**
 * Created by wty on 2017/2/15.
 */

public class SearchAnimationView extends View {


    private Paint mPaint;
    private Path searchPath;
    private Path circlePath;
    private PathMeasure pathMeasure;
    private ValueAnimator searchAnimator;
    private ValueAnimator startAnimator;
    private ValueAnimator reverseAnimator;
    private int defaultDuration = 2000;

    private ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
    private Animator.AnimatorListener animatorListener;

    private float animateValue = 0;

    private boolean isOver = false;
    private int count = 0;

    private int mViewWidth = 0;
    private int mViewHeight = 0;

    private enum STATE {NONE, STARTING, SEARCHING, SEARCHING_1, ENDING}

    ;

    private Handler animationHandler = null;

    private STATE mCurrentState = STATE.NONE;

    public SearchAnimationView(Context context) {
        super(context);
        initPaint();
        initPath();
        initAnimatorListener();
        initHandler();
        mCurrentState = STATE.STARTING;
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);


        startAnimator.start();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
    }


    private void initPath() {
        searchPath = new Path();
        circlePath = new Path();
        pathMeasure = new PathMeasure();

        RectF rectF = new RectF(-50, -50, 50, 50);
        searchPath.addArc(rectF, 45, 359.9f);

        float[] pos = new float[2];

        RectF rectF1 = new RectF(-100, -100, 100, 100);
        circlePath.addArc(rectF1, 45, 359.9f);
        pathMeasure.setPath(circlePath, false);
        pathMeasure.getPosTan(0, pos, null);

        searchPath.lineTo(pos[0], pos[1]);
    }

    private void initHandler() {
        animationHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (mCurrentState) {
                    case STARTING:
                        isOver = false;
                        mCurrentState = STATE.SEARCHING;
                        startAnimator.removeAllListeners();
                        searchAnimator.start();
                        break;
                    case SEARCHING:
                        if (!isOver) {///循环次数
                            searchAnimator.start();
                            count++;
                            if (count > 1) {
                                isOver = true;
                            }
                        } else {
                            mCurrentState = STATE.SEARCHING_1;
                            reverseAnimator.start();
                        }
                        break;
                    case SEARCHING_1:
                        mCurrentState = STATE.ENDING;
                        reverseAnimator.start();
                        invalidate();
                        break;
                }
            }
        };
    }

    private void initAnimatorListener() {

        animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animateValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        };

        animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animationHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };

        startAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        searchAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        reverseAnimator = ValueAnimator.ofFloat(1, 0).setDuration(defaultDuration);


        searchAnimator.addUpdateListener(animatorUpdateListener);
        startAnimator.addUpdateListener(animatorUpdateListener);
        reverseAnimator.addUpdateListener(animatorUpdateListener);

        searchAnimator.addListener(animatorListener);
        startAnimator.addListener(animatorListener);
        reverseAnimator.addListener(animatorListener);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPath(canvas);
    }


    private void drawPath(Canvas canvas) {
        canvas.translate(mViewWidth / 2, mViewHeight / 2);
        mPaint.setColor(Color.WHITE);
        canvas.drawColor(Color.parseColor("#0082D7"));
        switch (mCurrentState) {
            case NONE:
                canvas.drawPath(searchPath, mPaint);
                break;
            case STARTING:
                pathMeasure.setPath(searchPath, false);
                Path dest = new Path();
                pathMeasure.getSegment(pathMeasure.getLength() * animateValue, pathMeasure.getLength(), dest, true);
                canvas.drawPath(dest, mPaint);
                break;
            case SEARCHING:
                Path dest1 = new Path();
                pathMeasure.setPath(circlePath, false);
                float stop = pathMeasure.getLength() * animateValue;
                float start = (float) (stop - (0.5 - Math.abs(animateValue - 0.5)) * 200f);
                pathMeasure.getSegment(start, stop, dest1, true);
                canvas.drawPath(dest1, mPaint);
                break;
            case SEARCHING_1:
                Path dest2 = new Path();
                pathMeasure.setPath(circlePath, false);
                pathMeasure.getSegment(pathMeasure.getLength() * animateValue, pathMeasure.getLength(), dest2, true);
                canvas.drawPath(dest2, mPaint);
                break;

            case ENDING:
                Path dest3 = new Path();
                pathMeasure.setPath(searchPath, false);
                pathMeasure.getSegment(pathMeasure.getLength() * animateValue, pathMeasure.getLength(), dest3, true);
                canvas.drawPath(dest3, mPaint);
                break;
        }
    }
}
