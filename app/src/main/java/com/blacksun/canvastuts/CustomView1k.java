package com.blacksun.canvastuts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class CustomView1k extends View {
    private int mWidth;
    private int mHeight;
    private Paint mTextPaint;
    private Paint mObjectPaint;
    private Rect mRect;
    //    private Rect mRectDrawable;
    private final int OFFSET = 50;
    private int mOffset = 0;
    private int mColor;
    private int mHalfWidthRect;
    private int mHalfHeightRect;
    private Drawable drawable;
    private final int LIMIT = 30000;

    public CustomView1k(Context context) {
        super(context);
        init(context, null);
    }

    public CustomView1k(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomView1k(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mObjectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColor = Color.RED;
        mObjectPaint.setColor(mColor);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(40);
        drawable = ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground);
//        bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = OFFSET;
        mWidth = w;
        mHalfHeightRect = h;
        mHalfWidthRect = w;
//        mRadius = (float) (Math.min(mWidth, mHeight) / 2);
        mRect = new Rect(0, 0, mWidth, mHeight);
    }

    int i = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mHalfWidthRect = mWidth;
        mOffset = 0;
        i = 0;
        mRect.set(
                0, mOffset, mWidth, mHeight + mOffset);
//        Rect ractDraw = new Rect(0,0, mWidth,mHeight);


        while (i < LIMIT) {
//        while (i < 2) {
            mObjectPaint.setColor(mColor - 100 * mOffset);
            canvas.drawRect(mRect, mObjectPaint);

            canvas.drawText(Integer.toString(i), 100, mOffset +40, mTextPaint);

            //draw image
            drawable.setBounds(0, mOffset, mWidth, mHeight + mOffset);
//            if (i ==0) {
//                drawable.setBounds(ractDraw);
            drawable.draw(canvas);
//            }

            mOffset += OFFSET;
            mRect.set(
                    0, mOffset, mWidth, mHeight + mOffset);

            i++;
        }
//        canvas.drawBitmap(bitmap, mOffset, mHeight/2, mObjectPaint);
        Log.d("test canvas", "1k?" + i);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int height = OFFSET * LIMIT; // should be calculated based on the content
        int width = resolveSizeAndState(minw, widthMeasureSpec, 1);
        ; // should be calculated based on the content
        setMeasuredDimension(width, height);
    }
}
