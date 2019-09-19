package com.blacksun.canvastuts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class CustomView extends View {
    private int mWidth;
    private int mHeight;
    private Paint mTextPaint;
    private Paint mObjectPaint;
    private Rect mRect;
    private final int OFFSET = 10;
    private int mOffset = 0;
    private int mColor;
    private int mHalfWidthRect;
    private int mHalfHeightRect;
    private Drawable drawable;
//    private float mRadius;

    public CustomView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mHeight = h;
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
        while (mOffset < mHalfWidthRect) {
//            canvas.drawCircle(0,0, mRadius, mObjectPaint);
            switch (i % 4) {
                case 0:
                    canvas.drawText(Integer.toString(i), mOffset, mOffset, mTextPaint);
                    break;
                case 1:
                    canvas.drawText(Integer.toString(i), mWidth - mOffset, mOffset, mTextPaint);
                    break;
                case 2:
                    canvas.drawText(Integer.toString(i), mWidth - mOffset, mHeight - mOffset,
                            mTextPaint);
                    break;
                case 3:
                    canvas.drawText(Integer.toString(i), mOffset - 50, mHeight - mOffset, mTextPaint);
                    break;
            }
//            canvas.drawText(Integer.toString(i), mOffset, mOffset, mTextPaint);
            mObjectPaint.setColor(mColor - 100 * mOffset);
            canvas.drawRect(mRect, mObjectPaint);
            mRect.set(
                    mOffset, mOffset, mWidth - mOffset, mHeight - mOffset);
            mOffset += OFFSET;
            mHalfWidthRect = mHalfWidthRect - OFFSET;

            drawable.setBounds(mRect);
            drawable.draw(canvas);
//            mRadius -= OFFSET;
            i++;
        }
//        canvas.drawBitmap(bitmap, mOffset, mHeight/2, mObjectPaint);
        Log.d("test canvas", "?" + i);
    }


}
