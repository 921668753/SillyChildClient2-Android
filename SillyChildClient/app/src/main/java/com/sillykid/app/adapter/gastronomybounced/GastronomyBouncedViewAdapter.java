package com.sillykid.app.adapter.gastronomybounced;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.IndexCityBean.ResultBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 美食弹框--美食  适配器
 * Created by Admin on 2017/9/8.
 */

public class GastronomyBouncedViewAdapter extends BGAAdapterViewAdapter<ResultBean> {


    public GastronomyBouncedViewAdapter(Context context) {
        super(context, R.layout.item_gastronomydialog);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ResultBean model) {

        /**
         * 类别
         */
        helper.setText(R.id.tv_addressName, model.getName());
        if (model.getStatus() == 0) {
            helper.setTextColorRes(R.id.tv_addressName, R.color.greenColors);
            helper.setBackgroundRes(R.id.tv_addressName1, R.color.greenColors);
        } else {
            helper.setTextColorRes(R.id.tv_addressName, R.color.textColor);
            helper.setBackgroundRes(R.id.tv_addressName1, R.color.titledivider);
        }
    }
}
