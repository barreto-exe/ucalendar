package com.teamihc.ucalendar.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AnimatedToggleButton extends androidx.appcompat.widget.AppCompatToggleButton
{
    public AnimatedToggleButton(@NonNull Context context)
    {
        super(context);
        setAnimacion();
    }
    
    public AnimatedToggleButton(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        setAnimacion();
    }
    
    public AnimatedToggleButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setAnimacion();
    }
    
    private void setAnimacion()
    {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);
        scaleAnimation.setDuration(500);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);
        this.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                buttonView.startAnimation(scaleAnimation);
            }
        });
    }
}
