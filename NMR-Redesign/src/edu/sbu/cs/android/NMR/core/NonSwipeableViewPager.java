package edu.sbu.cs.android.NMR.core;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NonSwipeableViewPager extends ViewPager {

	private boolean enabled;
	
    public NonSwipeableViewPager(Context context) {
        super(context);
        this.enabled=true;
    }

    public NonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
//    	if (this.enabled) {
//            return super.onTouchEvent(event);
//        }
//        // Never allow swiping to switch between pages
//        return this.enabled;
    	 return enabled ? super.onInterceptTouchEvent(event) : false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
//        return false;
    	 return enabled ? super.onInterceptTouchEvent(event) : false;
    }
    public void setPagingEnabled(boolean lock) {
       this.enabled = lock;
    }
    
}