package com.dorma.dragdrop;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

public class TargetView extends View {
    private Paint mainPaint;

    public TargetView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
//        mainPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        mainPaint.setStyle(Paint.Style.STROKE);
        mainPaint.setStrokeWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics()));
        mainPaint.setColor(context.getResources().getColor(android.R.color.holo_orange_dark));
    }

    public TargetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TargetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TargetView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getMeasuredWidth() / 2, (getMeasuredHeight()) / 2, Math.max(getMeasuredWidth(), getMeasuredHeight()) / 2 - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics()), mainPaint);
    }
}
