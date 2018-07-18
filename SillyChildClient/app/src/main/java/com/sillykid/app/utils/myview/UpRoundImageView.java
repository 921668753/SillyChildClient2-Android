package com.sillykid.app.utils.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/9/25.
 */

public class UpRoundImageView extends ImageView {

    /*圆角的半径，依次为左上角xy半径，右上角，右下角，左下角*/
    private float[] rids = {5.0f,5.0f,5.0f,5.0f,0.0f,0.0f,0.0f,0.0f};

    public UpRoundImageView(Context context) {
        super(context);
    }

    public UpRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UpRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 画图
     * @param canvas
     */
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        /*向路径中添加圆角矩形。radii数组定义圆角矩形的四个圆角的x,y半径。radii长度必须为8*/
        path.addRoundRect(new RectF(0,0,w,h),rids,Path.Direction.CCW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}