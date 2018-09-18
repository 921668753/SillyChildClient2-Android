package com.sillykid.app.adapter.homepage.privatecustom.cityselect.fragment;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.fragment.CityClassificationBean.DataBean;


import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 国家分类
 */
public class CountryClassificationViewAdapter extends BGAAdapterViewAdapter<DataBean> {


    public CountryClassificationViewAdapter(Context context) {
        super(context, R.layout.item_country);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {

        /**
         * 背景色
         */
        if (model.getIsSelected() == 0) {
            helper.setBackgroundRes(R.id.tv_country, R.color.whiteColors);
            helper.setTextColorRes(R.id.tv_country, R.color.tabColors);
            helper.setImageResource(R.id.img_arrow, R.mipmap.arrow);
        } else {
            helper.setBackgroundRes(R.id.tv_country, R.drawable.shape_followed1);
            helper.setTextColorRes(R.id.tv_country, R.color.whiteColors);
            helper.setImageResource(R.id.img_arrow, R.mipmap.arrow1);
        }

        /**
         * 名字
         */
        helper.setText(R.id.tv_country, model.getName());
    }
}