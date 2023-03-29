package com.liuyetech.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class PreviewVideoView extends VideoView {

    public PreviewVideoView(Context context) {
        super(context);
    }

    public PreviewVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PreviewVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PreviewVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    //
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        int screenWidth = getResources().getDisplayMetrics().widthPixels;
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                startX = ev.getX();
//                startY = ev.getY();
//                return true;
//            case MotionEvent.ACTION_UP:
//                performClick();
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                float distanceX = ev.getX() - startX;
//                float distanceY = ev.getY() - startY;
//                if (startX > screenWidth / 2.0) {
//                    Log.e("videoview", "onTouchEvent: 增大音量" + distanceY);
//                    videoListener.setVolume(distanceY);
//                } else {
//                    Log.e("videoview", "onTouchEvent: 增大亮度" + distanceY);
//                    videoListener.setBrightness(distanceY);
//                }
//                return true;
//        }
//        return super.onTouchEvent(ev);
//    }
//
//    @Override
//    public boolean performClick() {
//        return super.performClick();
//    }
}
