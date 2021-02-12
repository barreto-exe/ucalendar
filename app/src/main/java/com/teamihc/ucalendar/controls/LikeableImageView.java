package com.teamihc.ucalendar.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ToggleButton;

public class LikeableImageView extends androidx.appcompat.widget.AppCompatImageView
{
    private Context context;
    private ToggleButton toggleButton;
    private GestureListener mGestureListener;
    private GestureDetector mGestureDetector;
    
    public LikeableImageView(Context context)
    {
        super(context);
        sharedConstructing(context);
    }
    
    public LikeableImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        sharedConstructing(context);
    }
    
    public LikeableImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        sharedConstructing(context);
    }
    
    private void sharedConstructing(Context context)
    {
        super.setClickable(true);
        this.context = context;
        mGestureListener = new GestureListener();
        mGestureDetector = new GestureDetector(context, mGestureListener, null, true);
        setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                mGestureDetector.onTouchEvent(event);
                //..my other code logic
                invalidate();
                return true; // indicate event was handled
            }
            
        });
    }
    
    public ToggleButton getToggleButton()
    {
        return toggleButton;
    }
    public void setToggleButton(ToggleButton toggleButton)
    {
        this.toggleButton = toggleButton;
    }
    
    public class GestureListener extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onDoubleTap(MotionEvent e)
        {
            if(getToggleButton() != null)
            {
                getToggleButton().performClick();
            }
            return true;
        }
    }
    
}