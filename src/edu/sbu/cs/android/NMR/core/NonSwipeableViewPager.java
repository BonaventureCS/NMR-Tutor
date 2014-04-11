package edu.sbu.cs.android.NMR.core;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NonSwipeableViewPager extends ViewPager {
	
	private boolean enabled;

    public NonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onTouchEvent(event);
        }
  
        return false;
    }


    private boolean blockSwipe = false;
    public void setBlockSwipe(boolean blockSwipe) {
        this.blockSwipe = blockSwipe;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (blockSwipe)
            return false;
        else 
            return super.onInterceptTouchEvent(arg0);
    }

}