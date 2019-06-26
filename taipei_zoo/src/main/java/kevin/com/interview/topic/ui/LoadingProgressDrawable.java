/*****************************************************************
 * Copyright (C) Newegg Corporation. All rights reserved.
 *
 * Author: Kevin Lin (Kevin.k.lin@newegg.com)
 * Create Date: 2015/3/17
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 *****************************************************************/

package kevin.com.interview.topic.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;

import kevin.com.interview.topic.R;

public class LoadingProgressDrawable extends Drawable implements Runnable, Animatable {

    private static final String TAG = LoadingProgressDrawable.class.getSimpleName();

    private final AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
    private final long REFRESH_DURATION_MILLISECOND = 33;
    private final long REFRESH_DRAWABLE_MILLISECOND = 84;
    private final long DEFAULT_PROGRESS_CYCLE_TIME = 2000;
    private final long HELF_DEFAULT_PROGRESS_CYCLE_TIME = DEFAULT_PROGRESS_CYCLE_TIME / 2;
    private float startTime;
    private float progressCycleTotalTime;
    private float progressCycleCurrentTime;
    private boolean running;

    private Paint paint = new Paint();
    private RectF arcOval = new RectF();
    private int[] colors;
    private int colorIndex;
    private float strokeWidth;
    private float rotation = -90;
    private float startTrim;
    private float endTrim;
    private float startWidth;

    private RectF mask = new RectF();
    private int res[] = {
            R.drawable.loading_1, R.drawable.loading_2, R.drawable.loading_3, R.drawable.loading_4, R.drawable.loading_5, R.drawable.loading_6,
            R.drawable.loading_7, R.drawable.loading_8, R.drawable.loading_9, R.drawable.loading_10, R.drawable.loading_11, R.drawable.loading_12
    };
    private Drawable[] drawables;
    private Drawable currentDrawable;
    private boolean showDrawable;

    public LoadingProgressDrawable(Context context) {
        colors = context.getResources().getIntArray(R.array.colors);
        strokeWidth = context.getResources().getDimension(R.dimen.dimen_6dp);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setColor(colors[0]);
        drawables = new Drawable[res.length];
        for (int index = 0; index < res.length; index++) {
            Drawable d = context.getResources().getDrawable(res[index]);
            d.setVisible(false, true);
            drawables[index] = d;
        }
    }

    @Override
    public void start() {
        if (!isRunning()) {
            startTime = SystemClock.uptimeMillis();
            run();
        }
    }

    @Override
    public void stop() {
        if (isRunning()) {
            unscheduleSelf(this);
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        final float nowTime = SystemClock.uptimeMillis();
        final float pastTime = nowTime - startTime;
        if (pastTime < 4000) {
            // setup default value
            showDrawable = false;
            progressCycleTotalTime = HELF_DEFAULT_PROGRESS_CYCLE_TIME;
            setPadding(startWidth);
        } else if (pastTime > 4000) {
            showDrawable = true;
            if (pastTime < 4500) {
                // zoom out
                float rate = (pastTime - 4000) / 500;
                setPadding(interpolator.getInterpolation(1f - rate) * startWidth);
                float currentProgressCycleTotalTime = HELF_DEFAULT_PROGRESS_CYCLE_TIME * (1f + interpolator.getInterpolation(rate));
                progressCycleCurrentTime += (currentProgressCycleTotalTime - progressCycleTotalTime);
                progressCycleTotalTime = currentProgressCycleTotalTime;
                currentDrawable = drawables[0];
            } else {
                setPadding(0);
                progressCycleTotalTime = DEFAULT_PROGRESS_CYCLE_TIME;
                // update drawable
                int index = (int) ((pastTime - 4500) / REFRESH_DRAWABLE_MILLISECOND % drawables.length);
                Drawable d = drawables[index];
                if (currentDrawable != d) {
                    if (currentDrawable != null) {
                        currentDrawable.setVisible(false, true);
                    }
                    currentDrawable = d;
                    currentDrawable.setVisible(true, true);
                }
            }
        }
        // update progress cycle
        final float percentage = progressCycleCurrentTime / progressCycleTotalTime;
        this.startTrim = interpolator.getInterpolation(percentage) * 360f;
        if (percentage > .5f) {
            float fraction = interpolator.getInterpolation((progressCycleCurrentTime - progressCycleTotalTime * .5f) / (progressCycleTotalTime * .5f));
            this.endTrim = fraction * 360f;
        } else {
            this.endTrim = 0;
        }
        progressCycleCurrentTime += REFRESH_DURATION_MILLISECOND;
        if (progressCycleCurrentTime > progressCycleTotalTime) {
            progressCycleCurrentTime = 0;
            colorIndex = (colorIndex + 1) % colors.length;
            paint.setColor(colors[colorIndex]);
        }
        scheduleSelf(this, (long) (nowTime + REFRESH_DURATION_MILLISECOND));
        invalidateSelf();
    }

    @Override
    public void scheduleSelf(Runnable what, long when) {
        running = true;
        super.scheduleSelf(what, when);
    }

    @Override
    public void unscheduleSelf(Runnable what) {
        running = false;
        super.unscheduleSelf(what);
    }

    @Override
    public void draw(Canvas canvas) {
        if (showDrawable) {

            BitmapDrawable drawable = (BitmapDrawable) currentDrawable;
            canvas.saveLayer(arcOval, drawable.getPaint());
            canvas.drawCircle(mask.centerX(), mask.centerY(), mask.width() / 2, drawable.getPaint());
            drawable.getPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            drawable.setBounds(getBounds());
            drawable.draw(canvas);
            drawable.getPaint().setXfermode(null);
            canvas.restore();
        }
        float startAngle = rotation + startTrim;
        float sweepAngle = endTrim - startTrim;
        canvas.drawArc(arcOval, startAngle, sweepAngle, false, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.startWidth = bounds.width() / 3;
    }

    void setPadding(float padding) {
        setMask(getBounds().left + padding, getBounds().top + padding, getBounds().right - padding, getBounds().bottom - padding);
        setArcOval(getBounds().left + padding, getBounds().top + padding, getBounds().right - padding, getBounds().bottom - padding);
    }

    void setMask(float left, float top, float right, float bottom) {
        mask.set(left + strokeWidth, top + strokeWidth, right - strokeWidth, bottom - strokeWidth);
    }

    void setArcOval(float left, float top, float right, float bottom) {
        arcOval.set(left + strokeWidth / 2f + .5f, top + strokeWidth / 2f + .5f, right - strokeWidth / 2f + .5f, bottom - strokeWidth / 2f + .5f);
    }
}
