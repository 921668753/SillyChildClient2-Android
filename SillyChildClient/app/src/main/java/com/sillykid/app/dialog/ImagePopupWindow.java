package com.sillykid.app.dialog;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sillykid.app.R;

/**
 * Created by Administrator on 2016/5/9.
 */
public class ImagePopupWindow extends PopupWindow {


    private RelativeLayout rl_btn;
    private ImageView image;

    private View myView;

    private Activity context;
    private Window window;
    private WindowManager.LayoutParams lp;

    private String url;

    public ImagePopupWindow(Activity context, Window window, String url) {
        super(context);
        this.context = context;
        this.window = window;
        this.url = url;
        initView();
    }

    private void initView() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myView = layoutInflater.inflate(R.layout.popup_layout_image, null);

        image = (ImageView) myView.findViewById(R.id.image);
        rl_btn = (RelativeLayout) myView.findViewById(R.id.rl_btn);

        //缺少头像字段
        Glide.with(context)
                .load(url)
                .fitCenter()
                .error(R.mipmap.placeholderfigure1)
//                .placeholder(R.mipmap.ic_stub)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(image);

        rl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        //开始设置popwindow的各种属性
        //设置view
        this.setContentView(myView);

        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //这边需要注意
        this.setOutsideTouchable(true);

        ColorDrawable cd = new ColorDrawable(0x000000);
        setBackgroundDrawable(cd);


//       this.setAnimationStyle(R.style.popwindow_anim_style);

        lp = window.getAttributes();

        //显示时背景变暗
//        changeLight2Show();

        //设置关闭的监听事件，关闭后恢复背景
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
//                changeLight2close();
            }
        });
    }

    /**
     * 恢复背景
     */
    public void changeLight2close() {
        final ValueAnimator animation = ValueAnimator.ofFloat(0.4f, 1.0f);
        animation.setDuration(300);
        animation.start();

        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lp.alpha = (float) valueAnimator.getAnimatedValue();
                window.setAttributes(lp);
            }
        });
    }


    /**
     * 背景变暗
     */
    public void changeLight2Show() {
        final ValueAnimator animation = ValueAnimator.ofFloat(1.0f, 0.4f);
        animation.setDuration(300);
        animation.start();

        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lp.alpha = (float) valueAnimator.getAnimatedValue();
                window.setAttributes(lp);
            }
        });
    }

}
