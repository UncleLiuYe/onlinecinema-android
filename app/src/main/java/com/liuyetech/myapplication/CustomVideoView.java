package com.liuyetech.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.VideoView;

public class CustomVideoView extends VideoView {
    private float startX = 0;
    private float startY = 0;
    private VideoListener videoListener;

    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setVideoListener(VideoListener videoListener) {
        this.videoListener = videoListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                return true;
            case MotionEvent.ACTION_UP:
                performClick();
                return true;
            case MotionEvent.ACTION_MOVE:
                float distanceX = ev.getX() - startX;
                float distanceY = ev.getY() - startY;
                if (startX > screenWidth / 2.0) {
                    Log.e("videoview", "onTouchEvent: 增大音量" + distanceY);
                    videoListener.setVolume(distanceY);
                } else {
                    Log.e("videoview", "onTouchEvent: 增大亮度" + distanceY);
                    videoListener.setBrightness(distanceY);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
