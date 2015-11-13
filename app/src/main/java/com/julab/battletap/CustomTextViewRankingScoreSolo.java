package com.julab.battletap;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by user on 13-11-15.
 */
public class CustomTextViewRankingScoreSolo extends TextView
{
    public CustomTextViewRankingScoreSolo(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Timoteo.ttf"));
    }
}
