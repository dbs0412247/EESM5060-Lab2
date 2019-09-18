package ece.course.eesm5060_lab2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class DisplayView extends SurfaceView {

    public final static int TYPE_BALL = 0;
    public final static int TYPE_SQUARE = 1;
    public final static int TYPE_DIAMOND = 2;
    public final static int TYPE_ARC = 3;

    private float mCenterX = 0f;
    private float mCenterY = 0f;
    private float mRadius = 0f;

    private float mPtrCenterX = 100f;
    private float mPtrCenterY = 100f;
    private float mPtrRadius = 10f;

    private int mPtrType = TYPE_BALL;
    private int mPtrColor = Color.RED;

    private Paint mDrawPaint = new Paint();
    private RectF mArcRectF = new RectF();
    private Path mDiamondPath = new Path();

    public DisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public void setPtr(float posX, float posY)
    {
        mPtrCenterX = posX * mRadius * 0.9f + mCenterX;
        mPtrCenterY = posY * mRadius * 0.9f + mCenterY;
        invalidate();
    }

    public void setPtrColor(int color) {
        mPtrColor = color;
        invalidate();
    }

    public void setPtrType(int type) {
        mPtrType = type;
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        if (canvas == null)
            return;

        canvas.drawColor(Color.BLACK);
        mDrawPaint.setColor(Color.LTGRAY);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mDrawPaint);

        mDrawPaint.setColor(mPtrColor);
        switch (mPtrType) {
            case TYPE_BALL:
                canvas.drawCircle(mPtrCenterX, mPtrCenterY, mPtrRadius, mDrawPaint);
                break;
            case TYPE_SQUARE:
                canvas.drawRect(
                    mPtrCenterX- mPtrRadius,
                    mPtrCenterY - mPtrRadius,
                    mPtrCenterX + mPtrRadius,
                    mPtrCenterY + mPtrRadius, mDrawPaint);
                break;
            case TYPE_DIAMOND:
                mDiamondPath.reset();
                mDiamondPath.moveTo(mPtrCenterX, mPtrCenterY - mPtrRadius);
                mDiamondPath.lineTo(mPtrCenterX - mPtrRadius, mPtrCenterY);
                mDiamondPath.lineTo(mPtrCenterX, mPtrCenterY + mPtrRadius);
                mDiamondPath.lineTo(mPtrCenterX + mPtrRadius, mPtrCenterY);
                mDiamondPath.close();
                canvas.drawPath(mDiamondPath, mDrawPaint);
                break;
            case TYPE_ARC:
                mArcRectF.set(
                        mPtrCenterX - mPtrRadius,
                        mPtrCenterY - mPtrRadius,
                        mPtrCenterX + mPtrRadius,
                        mPtrCenterY + mPtrRadius);
                canvas.drawArc(
                    mArcRectF,
                    -45f, -90f, true, mDrawPaint);
                break;
        }
    }

    public void onSizeChanged(int newW, int newH, int oldW, int oldH) {
        mCenterX = newW / 2.0f;
        mCenterY = newH / 2.0f;
        mRadius = ((newW < newH)? newW : newH) * 3.0f / 8.0f;

        mPtrCenterX = mCenterX;
        mPtrCenterY = mCenterY;
        mPtrRadius = mRadius / 10f;

        invalidate();
    }
}
