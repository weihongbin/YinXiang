package com.example.administrator.myyinxiang.customView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

/**
 * Created by CXY on 2016/12/26.
 */
public class ProgressView extends ProgressBar {
    private final Paint paintCircle;
    private final Paint paintBg;
    private int bgColor = Color.parseColor("#BEBEBE");
    private int topColor = Color.parseColor("#1591DB");
    private int measuredWidth;
    private int circle_r;
    private float bgTop;
    private float bgBottom;
    float portion;
    private static int progress;
    private int timeMax = 5;
    private Listener listener;

    public interface Listener {
        void start();

        void end();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paintCircle = new Paint();
        paintCircle.setColor(topColor);
        paintCircle.setStrokeWidth(3);
        paintCircle.setAntiAlias(true);

        paintBg = new Paint();
        paintBg.setColor(bgColor);
        paintBg.setStrokeWidth(3);
        paintBg.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, bgTop, measuredWidth, bgBottom, paintBg);
        canvas.drawRect(0, bgTop, progress, bgBottom, paintCircle);
        canvas.drawCircle(progress, getMeasuredHeight() / 2, circle_r, paintCircle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        bgTop = (float) (getMeasuredHeight() * 0.3 / 2);
        bgBottom =(float)(getMeasuredHeight() - bgTop);
        measuredWidth = getMeasuredWidth();
        circle_r = getMeasuredHeight() / 2;
        portion = getMeasuredWidth()*1f/ (timeMax * 1000);
    }


    public void setProgress() {
        this.post(new Runnable() {
            @Override
            public void run() {
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, measuredWidth);
                valueAnimator.setDuration(timeMax * 1000);
                valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float curValueFloat = (float) valueAnimator.getAnimatedValue();
                        int i = (int) curValueFloat;
                        progress = i;
                        Log.e("whbhbw",i+"");

                        if (i == 0) {
                            if (listener != null) listener.start();
                        } else if (i == measuredWidth) {

                            if (listener != null) listener.end();
                        }
                        invalidate();
                    }
                });
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.start();
            }
        });


    }
}
