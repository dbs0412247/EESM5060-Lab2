package ece.course.eesm5060_lab2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.SurfaceView;

public class DisplayView extends SurfaceView {

    private float mCenterX = 0;
    private float mCenterY = 0;
    private float mRadius = 0;
    private Paint mPaintBlack = new Paint();
    private Paint mPaintLtGray = new Paint();
    private Paint mPaintRed = new Paint();
    private Paint mPaintGreen = new Paint();
    private Paint mPaintBlue = new Paint();
    private Paint mPaintWhite = new Paint();

    public DisplayView(Context context) {
        super(context);
        mPaintBlack.setColor(Color.BLACK);
        mPaintLtGray.setColor(Color.LTGRAY);
        mPaintRed.setColor(Color.RED);
        mPaintBlue.setColor(Color.BLUE);
        mPaintGreen.setColor(Color.GREEN);
        mPaintWhite.setColor(Color.WHITE);
        setWillNotDraw(false);
    }

    public void onDraw(Canvas canvas) {
        if (canvas == null)
            return;

        canvas.drawColor(Color.BLACK);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaintBlack);
        canvas.drawCircle(40f, 40f, 10f, mPaintRed);
        canvas.drawRect(70f, 30f, 90f, 50f, mPaintGreen);
        Path path = new Path();
        path.moveTo(40f, 70f);
        path.moveTo(30f, 80f);
        path.moveTo(40f, 90f);
        path.moveTo(50f, 80f);
        path.close();
        canvas.drawPath(path, mPaintGreen);
        canvas.drawArc(
            new RectF(70f, 70f, 90f, 90f),
            -45f, -90f, true, mPaintWhite);
    }

    public void onSizeChanged(int newW, int newH, int oldW, int oldH) {
        mCenterX = newW / 2.0f;
        mCenterY = newH / 2.0f;
        mRadius = ((newW < newH)? newW : newH) * 3.0f / 8.0f;
        invalidate();
    }
}
