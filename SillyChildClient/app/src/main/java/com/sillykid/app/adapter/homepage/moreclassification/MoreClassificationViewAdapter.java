package com.sillykid.app.adapter.homepage.moreclassification;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.moreclassification.MoreClassificationBean.DataBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页 更多分类 适配器
 * Created by Admin on 2017/8/15.
 */

public class MoreClassificationViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public MoreClassificationViewAdapter(Context context) {
        super(context, R.layout.item_moreclassification);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {
        Log.d("position", position + "");

        /**
         * 背景色
         */
        if (listBean.getIsSelected() == 0) {
            viewHolderHelper.setVisibility(R.id.tv_tab, View.INVISIBLE);
            viewHolderHelper.setBackgroundColorRes(R.id.ll_moreClassification, R.color.whiteColors);
            viewHolderHelper.setTextColorRes(R.id.tv_moreClassification, R.color.textColor);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_tab, View.VISIBLE);
            viewHolderHelper.setBackgroundColorRes(R.id.ll_moreClassification, R.color.f1F4F6Colors);
            viewHolderHelper.setTextColorRes(R.id.tv_moreClassification, R.color.greenColors);
        }

        /**
         * 名字
         */
        viewHolderHelper.setText(R.id.tv_moreClassification, listBean.getName());

    }

}