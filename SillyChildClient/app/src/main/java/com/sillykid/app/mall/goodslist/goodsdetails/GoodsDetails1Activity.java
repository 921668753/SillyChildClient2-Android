package com.sillykid.app.mall.goodslist.goodsdetails;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;

/**
 * 商品详情
 * Created by Admin on 2017/8/24.
 */
public class GoodsDetails1Activity extends BaseActivity {


    /**
     * 收藏
     */
    @BindView(id = R.id.tv_goodName)
    private TextView tv_goodName;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_goodsdetails1);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();

    }


    /**
     * 初始化控件
     */
    @Override
    public void initWidget() {
        super.initWidget();
        Drawable drawable = getResources().getDrawable(R.mipmap.android_template);
/// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_goodName.setCompoundDrawables(drawable, null, null, null);

        // 第三种方式
        ImageSpan imgSpan = new ImageSpan(this, R.mipmap.android_template);
        SpannableString spannableString = new SpannableString("012345");
        spannableString.setSpan(imgSpan, 1, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_goodName.setText(spannableString);
    }


}
