package com.liuyetech.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {
    private float startX = 0;
    private float startY = 0;
    private boolean beginScroll = false;

    public CustomSwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    public CustomSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = ev.getX();
                float endY = ev.getY();
                float disX = Math.abs(endX - startX);
                float disY = Math.abs(endY - startY);
                if (disX > disY) {
                    if (!beginScroll) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                } else {
                    beginScroll = true;
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
                beginScroll = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                beginScroll = false;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
