package com.sillykid.app.utils.myview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/10/23.
 */

public class LinearLayoutWidth extends LinearLayout {

    public LinearLayoutWidth(Context context) {
        super(context);
    }

    public LinearLayoutWidth(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutWidth(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LinearLayoutWidth(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
