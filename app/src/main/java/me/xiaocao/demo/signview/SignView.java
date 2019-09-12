package me.xiaocao.demo.signview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by ls
 * on 2015/7/29
 * Description
 */
public class SignView extends View {

    private Paint paint = new Paint();
    private LinkedList<TimedPoint> points;
    private Bitmap drawBitmap = null;

    /**
     * invalida刷新整个view效率太低,只刷新改变的rect来提升效率
     */
    private final RectF refreshRect = new RectF();


    private static final float mMaxWidth = 18.0f;
    private static final float mMinWidth = 5.0f;
    private static final float initVelocity = 0.5f;
    /**
     * 0-1,数值越大对速度的变化越敏感
     */
    private static final float mVelocityFilterWeight = 0.2f;

    public SignView(Context context) {
        super(context);
        init();
    }

    public SignView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SignView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(mMinWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drawBitmap != null) {
            canvas.drawBitmap(drawBitmap, 0, 0, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                points = new LinkedList<>();
                addPoint(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                /**历史数据,当滑动过快时,一部分touchEvent没有被分发出来而是存起来作为历史数据*/
                /**画bezier曲线的话忽略这些点,过多的点导致曲线不够圆滑,效果不好*/
//                int historySize = event.getHistorySize();
//                for (int i = 0; i < historySize; i++) {
//                    float historicalX = event.getHistoricalX(i);
//                    float historicalY = event.getHistoricalY(i);
//                    updateRefreshRect(historicalX, historicalY);
//                    path.lineTo(historicalX, historicalY);
//
//                    bezierTo(historicalX, historicalY);
//                    lastDrawX = historicalX;
//                    lastDrawY = historicalY;
//                }
                /**处理完历史数据再处理当前的数据*/
                //path.lineTo(eventX, eventY);
                addPoint(eventX, eventY);
                break;
        }
        /**刷新需要刷新的rect部分*/
        invalidateRect();
        return super.onTouchEvent(event);
    }

    private void invalidateRect() {
        invalidate((int) refreshRect.left, (int) refreshRect.top,
                (int) refreshRect.right, (int) refreshRect.bottom);
    }

    private void setRefreshRect(float startX, float startY, float endX, float endY, float width) {
        /**重置要刷新的区域*/
        refreshRect.left = Math.min(startX, endX) - width;
        refreshRect.right = Math.max(startX, endX) + width;
        refreshRect.top = Math.min(startY, endY) - width;
        refreshRect.bottom = Math.max(startY, endY) + width;
    }

    private TimedPoint getHalfPoint(TimedPoint start, TimedPoint end) {
        /**取两点的中点*/
        return new TimedPoint(start.x + (end.x - start.x) / 2, start.y + (end.y - start.y) / 2);
    }

    private float strokeWidth(float velocity) {
        return Math.max(mMaxWidth / (velocity + 1), mMinWidth);
    }

    private void addPoint(float x, float y) {
        TimedPoint startPoint;
        TimedPoint endPoint;
        TimedPoint controlPoint;

        TimedPoint last;

        TimedPoint point = new TimedPoint(x, y);
        int count = points.size();
        if (count == 0) {
            points.add(point);
            /**新的线条*/
            return;
        } else if (count == 1) {
            last = points.getLast();
            startPoint = last;
            endPoint = getHalfPoint(startPoint, point);
            controlPoint = startPoint;
            points.add(point);
        } else {
            last = points.getLast();
            startPoint = getHalfPoint(points.get(count - 2), last);
            endPoint = getHalfPoint(points.getLast(), point);
            controlPoint = points.getLast();
            points.add(point);
        }
        if (last != null) {
            float v = point.velocityFrom(last);
            if (v > 0) {
                v = mVelocityFilterWeight * v + (1 - mVelocityFilterWeight) * initVelocity;
                drawBezier(startPoint, endPoint, controlPoint, strokeWidth(v));
            }
        }
    }

    private void drawBezier(TimedPoint startPoint, TimedPoint endPoint, TimedPoint controlPoint, float width) {
        if (drawBitmap == null) {
            drawBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas bitmapCanvas = new Canvas(drawBitmap);
        Path path = new Path();
        path.moveTo(startPoint.x, startPoint.y);
        path.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y);

        paint.setStrokeWidth(width);
        bitmapCanvas.drawPath(path, paint);
        setRefreshRect(startPoint.x, startPoint.y, endPoint.x, endPoint.y, width);
    }

    public void clear() {
        /**清除所有图案*/
        drawBitmap.recycle();
        drawBitmap = null;
        invalidate();
    }
}